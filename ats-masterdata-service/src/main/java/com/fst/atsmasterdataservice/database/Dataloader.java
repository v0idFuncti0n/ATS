package com.fst.atsmasterdataservice.database;

import com.fst.atsmasterdataservice.entity.candidate.CandidateEntity;
import com.fst.atsmasterdataservice.entity.candidate.LanguageEntity;
import com.fst.atsmasterdataservice.entity.candidate.SkillEntity;
import com.fst.atsmasterdataservice.enums.CandidateStatus;
import com.fst.atsmasterdataservice.repository.BootcampRepository;
import com.fst.atsmasterdataservice.repository.candidate.*;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Dataloader implements ApplicationRunner {

    public static final int CANDIDATE_NUMBER_TO_GENERATE = 50;

    private CandidateRepository candidateRepository;
    private EducationRepository educationRepository;
    private LanguageRepository languageRepository;
    private SkillRepository skillRepository;
    private WorkExperienceRepository workExperienceRepository;

    private BootcampRepository bootcampRepository;

    private Faker faker;

    @Autowired
    public Dataloader(CandidateRepository candidateRepository, EducationRepository educationRepository, LanguageRepository languageRepository, SkillRepository skillRepository, WorkExperienceRepository workExperienceRepository, BootcampRepository bootcampRepository) {
        this.candidateRepository = candidateRepository;
        this.educationRepository = educationRepository;
        this.languageRepository = languageRepository;
        this.skillRepository = skillRepository;
        this.workExperienceRepository = workExperienceRepository;
        this.bootcampRepository = bootcampRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        this.faker = new Faker();

        for (int i = 0; i < CANDIDATE_NUMBER_TO_GENERATE; i++) {
            List<SkillEntity> skillEntityList = new ArrayList<>();

            SkillEntity javaSkillEntity = new SkillEntity();
            javaSkillEntity.setSkill("java");
            skillEntityList.add(javaSkillEntity);

            SkillEntity gitSkillEntity = new SkillEntity();
            gitSkillEntity.setSkill("git");
            skillEntityList.add(gitSkillEntity);

            SkillEntity mysqlSkillEntity = new SkillEntity();
            mysqlSkillEntity.setSkill("mysql");
            skillEntityList.add(mysqlSkillEntity);

            SkillEntity githubSkillEntity = new SkillEntity();
            githubSkillEntity.setSkill("github");
            skillEntityList.add(githubSkillEntity);

            SkillEntity springBootSkillEntity = new SkillEntity();
            springBootSkillEntity.setSkill("spring boot");
            skillEntityList.add(springBootSkillEntity);

            SkillEntity angularSkillEntity = new SkillEntity();
            angularSkillEntity.setSkill("angular");
            skillEntityList.add(angularSkillEntity);

            SkillEntity cSharpSkillsEntity = new SkillEntity();
            cSharpSkillsEntity.setSkill("C#");
            skillEntityList.add(cSharpSkillsEntity);

            SkillEntity aspSkillEntity = new SkillEntity();
            aspSkillEntity.setSkill("ASP.net");
            skillEntityList.add(aspSkillEntity);

            SkillEntity reactSkillEntity = new SkillEntity();
            reactSkillEntity.setSkill("react");
            skillEntityList.add(reactSkillEntity);

            CandidateEntity candidateEntity = new CandidateEntity();

            candidateEntity.setFirstName(this.faker.name().firstName());
            candidateEntity.setLastName(this.faker.name().lastName());
            candidateEntity.setStatus(CandidateStatus.IN_POOL);
            candidateEntity.setVerified(false);
            candidateEntity.setBirthDate(this.faker.regexify("(0[1-9]|[12][0-9]|3[01])\\/(0[1-9]|1[1-2])\\/(199|200)\\d{1}"));
            candidateEntity.setEmail(candidateEntity.getFirstName() + "." + candidateEntity.getLastName() + "@gmail.com");
            candidateEntity.setLocation(this.faker.address().streetAddress());
            candidateEntity.setPhoneNumber(this.faker.bothify("+2126########"));
            candidateEntity.setResumeFilename("a2cc1abc-2c9f-443a-9387-b5cdde0765dc.pdf");

            List<SkillEntity> candidateSkills = new ArrayList<>();
            List<SkillEntity> skillEntityListCopy = new ArrayList<>(skillEntityList);

            for (int j = 0; j < 5; j++) {
                int randomSkillIndex = this.faker.random().nextInt(0, skillEntityListCopy.size() - 1);

                SkillEntity skillEntity = skillEntityListCopy.get(randomSkillIndex);
                candidateSkills.add(skillEntity);

                skillEntityListCopy.remove(randomSkillIndex);
            }

            List<LanguageEntity> candidateLanguages = new ArrayList<>();
            List<LanguageEntity> candidateLanguagesCopy = getLanguagesList();
            for (int k = 0; k < 3 ;k++){

                int randomLanguageIndex = this.faker.random().nextInt(0, candidateLanguagesCopy.size() - 1);

                LanguageEntity languageEntity = candidateLanguagesCopy.get(randomLanguageIndex);
                candidateLanguages.add(languageEntity);

                candidateLanguagesCopy = deleteLanguageFromList(candidateLanguagesCopy,languageEntity.getLanguage());
            }

            CandidateEntity savedCandidate = candidateRepository.save(candidateEntity);
            candidateSkills.forEach(skillEntity -> {
                skillEntity.setCandidate(savedCandidate);
            });

            candidateLanguages.forEach(languageEntity -> {
                languageEntity.setCandidate(savedCandidate);
            });

            savedCandidate.setSkills(candidateSkills);
            savedCandidate.setLanguages(candidateLanguages);
            candidateRepository.save(savedCandidate);


        }
    }

    public List<LanguageEntity> getLanguagesList(){
        List<String> languageList = new ArrayList<>();
        languageList.add("English");
        languageList.add("Spanish");
        languageList.add("French");
        languageList.add("Arabic");

        List<String> levelList = new ArrayList<>();
        levelList.add("A1");
        levelList.add("A2");
        levelList.add("B1");
        levelList.add("B2");
        levelList.add("C1");
        levelList.add("C2");

        List<LanguageEntity> languages = new ArrayList<>();
        languageList.forEach(language->{
            levelList.forEach(level->{
                LanguageEntity languageEntity = new LanguageEntity();
                languageEntity.setLanguage(language);
                languageEntity.setLevel(level);
                languages.add(languageEntity);
            });
        });
        return languages;
    }

    public List<LanguageEntity> deleteLanguageFromList(List<LanguageEntity> languages, String language){
        return languages.stream().filter(languageEntity -> !languageEntity.getLanguage().equals(language)).toList();
    }
}
