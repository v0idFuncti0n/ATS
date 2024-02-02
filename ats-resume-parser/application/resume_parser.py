import pdftotext
import openai
import re
import logging
import json
from tokenizer import num_tokens_from_string


class ResumeParser():
    def __init__(self, OPENAI_API_KEY):
        # set GPT-3 API key from the environment variable
        openai.api_key = OPENAI_API_KEY
        # GPT-3 completion questions
        self.prompt_questions = \
            """Summarize the text below into a JSON with exactly the following structure {basic_info: {first_name, 
            last_name, email, phone_number, location, birth_date}, work_experience: [{job_title, company, location, 
            duration, job_summary}], educations:[{education}], project_experience:[{project_name, 
            project_description}], skills:[{skill}], languages:[{language}]} """
        # set up this parser's logger
        logging.basicConfig(filename='../parser.log', level=logging.DEBUG)
        self.logger = logging.getLogger()

    def pdf2string(self: object, pdf_path: str) -> str:
        """
        Extract the content of a pdf file to string.
        :param pdf_path: Path to the PDF file.
        :return: PDF content string.
        """
        with open(pdf_path, "rb") as f:
            pdf = pdftotext.PDF(f)
        pdf_str = "\n\n".join(pdf)
        pdf_str = re.sub('\s[,.]', ',', pdf_str)
        pdf_str = re.sub('[\n]+', '\n', pdf_str)
        pdf_str = re.sub('[\s]+', ' ', pdf_str)
        pdf_str = re.sub('http[s]?(://)?', '', pdf_str)
        return pdf_str

    def query_completion(self: object,
                         prompt: str,
                         engine: str = 'gpt-3.5-turbo-instruct',
                         temperature: float = 0.0,
                         max_tokens: int = 100,
                         top_p: int = 1,
                         frequency_penalty: int = 0,
                         presence_penalty: int = 0) -> object:

        self.logger.info(f'query_completion: using {engine}')

        estimated_prompt_tokens = num_tokens_from_string(prompt, engine)
        estimated_answer_tokens = (max_tokens - estimated_prompt_tokens)
        self.logger.info(f'Tokens: {estimated_prompt_tokens} + {estimated_answer_tokens} = {max_tokens}')

        response = openai.Completion.create(
            engine=engine,
            prompt=prompt,
            temperature=temperature,
            max_tokens=estimated_answer_tokens,
            top_p=top_p,
            frequency_penalty=frequency_penalty,
            presence_penalty=presence_penalty
        )
        return response

    def query_resume(self: object, pdf_path: str) -> dict:
        """
        Query GPT-3 for the work experience and / or basic information from the resume at the PDF file path.
        :param pdf_path: Path to the PDF file.
        :return dictionary of resume with keys (basic_info, work_experience).
        """
        resume = {}
        pdf_str = self.pdf2string(pdf_path)
        print(pdf_str)
        prompt = self.prompt_questions + '\n' + pdf_str

        # Reference: https://platform.openai.com/docs/models/gpt-3-5
        engine = 'gpt-3.5-turbo-instruct'
        max_tokens = 1500

        response = self.query_completion(prompt, engine=engine, max_tokens=max_tokens)
        response_text = response['choices'][0]['text'].strip()
        print(response_text)
        resume = json.loads(response_text)
        return resume
