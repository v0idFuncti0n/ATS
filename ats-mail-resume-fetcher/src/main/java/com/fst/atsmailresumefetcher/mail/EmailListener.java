package com.fst.atsmailresumefetcher.mail;

import com.fst.atsmailresumefetcher.service.ATSMasterdataService;
import com.fst.atsmailresumefetcher.service.ATSResumeParserService;
import jakarta.mail.*;
import jakarta.mail.event.MessageCountAdapter;
import jakarta.mail.event.MessageCountEvent;
import jakarta.mail.internet.MimeBodyPart;
import org.eclipse.angus.mail.imap.IMAPFolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import java.io.File;
import java.util.UUID;

@Component
public class EmailListener extends MessageCountAdapter {
    private static final int NUMBER_OF_PDF_TO_PARSE = 1;
    private static final String UPLOAD_PATH = "src\\main\\resources\\static";
    private static final String PDF_MIME_TYPE = "application/pdf";
    private static final String PDF_EXTENSION = ".pdf";
    private final Session session;

    @Value("${email.username}")
    private String emailUsername;
    @Value("${email.password}")
    private String emailPassword;

    @Autowired
    private ATSResumeParserService atsResumeParserService;

    @Autowired
    private ATSMasterdataService atsMasterdataService;

    public EmailListener(Session session) {
        this.session = session;
    }

    public void startListening() throws MessagingException {
        Store store = session.getStore("imaps");
        store.connect(emailUsername, emailPassword);

        IMAPFolder inbox = (IMAPFolder) store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);

        // Create a new thread to keep the connection alive
        Thread keepAliveThread = new Thread(new KeepAliveRunnable(inbox), "IdleConnectionKeepAlive");
        keepAliveThread.start();

        inbox.addMessageCountListener(new MessageCountAdapter() {

            @Override
            public void messagesAdded(MessageCountEvent event) {
                // Process the newly added messages
                try {
                    Message[] messages = event.getMessages();
                    for (Message message : messages) {
                        Multipart multiPart = (Multipart) message.getContent();
                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(NUMBER_OF_PDF_TO_PARSE);
                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                            if (part.isMimeType(PDF_MIME_TYPE)) {

                                File pdf = new File(UPLOAD_PATH);
                                if (!pdf.exists()) {
                                    pdf.mkdirs();
                                }

                                String downloadPDFLocation = pdf.getAbsolutePath() + File.separator + UUID.randomUUID() + PDF_EXTENSION;

                                part.saveFile(downloadPDFLocation);

                                File resumePDF = new File(downloadPDFLocation);

                                ResponseEntity<String> resumeUploadedResponse = atsResumeParserService.sendResume(resumePDF);

                                if (resumeUploadedResponse.getStatusCode() == HttpStatusCode.valueOf(200)){
                                    ResponseEntity<Object> resumeResponse = atsResumeParserService.resumeToJSON(resumePDF.getName());
                                    // resumeResponse.getBody() get the JSON data
                                    if(resumeResponse.getStatusCode() == HttpStatusCode.valueOf(200)) {
                                        resumePDF.delete();
                                        System.out.println(resumeResponse.getBody());
                                        atsMasterdataService.createCandidate(resumeResponse.getBody());
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // Start the IDLE Loop
        while (!Thread.interrupted()) {
            try {
                inbox.idle();
            } catch (MessagingException e) {
                System.out.println("Messaging exception during IDLE");
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        // Interrupt and shutdown the keep-alive thread
        if (keepAliveThread.isAlive()) {
            keepAliveThread.interrupt();
        }
    }
}