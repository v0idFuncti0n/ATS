import {Component, OnInit, ViewChild} from '@angular/core';
import {Config} from "datatables.net";
import {Candidate} from "../../../models/candidate/Candidate";
import {CandidateService} from "../../../services/CandidateService";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ModalComponent, ModalConfig} from "../modal/modal.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-candidate',
  templateUrl: './candidate.component.html',
  styleUrls: ['./candidate.component.css']
})
export class CandidateComponent implements OnInit {
  isLoading = true;
  candidates: Candidate[] = [];
  dtOptions: Config = {};

  candidateForm: FormGroup;

  pdfToDisplayURL: string = "";
  currentCandidateToVerify: Candidate | undefined;
  candidateModalConfig: ModalConfig = {
    modalTitle: "Verify Candidate",
    closeButtonLabel: "Verify",
    dismissButtonLabel: "Close",
    onClose: this.verifyCandidate.bind(this),
    onDismiss: this.dismissVerifyCandidate.bind(this)
  }
  @ViewChild('candidateModal') private candidateModalComponent!: ModalComponent

  constructor(private candidateService: CandidateService, private form: FormBuilder, private router: Router) {
    this.candidateForm = this.form.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      location: ['', Validators.required],
      birthDate: ['', Validators.required],
      skills: this.form.array([]),
      workExperiences: this.form.array([]),
      languages: this.form.array([]),
      educations: this.form.array([])
    })
  }

  get candidateSkills() {
    return this.candidateForm.get("skills") as FormArray;
  }

  get candidateWorkExperiences() {
    return this.candidateForm.get("workExperiences") as FormArray;
  }

  get candidateLanguages() {
    return this.candidateForm.get("languages") as FormArray;
  }

  get candidateEducations() {
    return this.candidateForm.get("educations") as FormArray;
  }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      order: [[5, "asc"]],
      columns: [
        {},
        {},
        {},
        {},
        {},
        {},
        {visible: false},
        {},
        {}
      ]
    };

    this.candidateService.getAllCandidates().subscribe((candidates) => {
      this.candidates = candidates;
      this.isLoading = false;
    });
  }

  async openModalVerifyCandidate(candidate: Candidate) {
    this.currentCandidateToVerify = candidate;

    this.candidateForm.patchValue(this.currentCandidateToVerify);

    this.currentCandidateToVerify.skills.forEach(skill => {
      const skillForm = this.form.group({
        skill: [skill.skill,Validators.required]
      });
      this.candidateSkills.push(skillForm);
    })

    this.currentCandidateToVerify.languages.forEach(language => {
      const languageForm = this.form.group({
        language: [language.language,Validators.required],
        level: [language.level,Validators.required]
      });
      this.candidateLanguages.push(languageForm);
    })

    this.currentCandidateToVerify.workExperiences.forEach(workExperience => {
      const workExperienceForm = this.form.group({
        jobTitle: [workExperience.jobTitle,Validators.required],
        company: [workExperience.company,Validators.required],
        location: [workExperience.location,Validators.required],
        duration: [workExperience.duration,Validators.required],
        jobSummary: [workExperience.jobSummary,Validators.required]
      });
      this.candidateWorkExperiences.push(workExperienceForm);
    })

    this.currentCandidateToVerify.educations.forEach(education => {
      const educationForm = this.form.group({
        education: [education.education,Validators.required],
        institute: [education.institute,Validators.required],
        duration: [education.duration,Validators.required],
        location: [education.location,Validators.required]
      });
      this.candidateEducations.push(educationForm);
    })

    this.candidateService.getCandidateResumeFile(candidate.id!).subscribe(data => {
      let blob = new Blob([data], {type: 'application/pdf'});
      this.pdfToDisplayURL = window.URL.createObjectURL(blob);
    });
    return await this.candidateModalComponent.open({centered: true, size: "xl", fullscreen: true});
  }

  private verifyCandidate() {
    let candidateToVerify: Candidate = this.candidateForm.value;
    this.candidateService.updateCandidate(candidateToVerify, this.currentCandidateToVerify!.id!).subscribe(candidate => {
      this.currentCandidateToVerify = undefined;
      this.candidateSkills.clear();
      this.candidateEducations.clear();
      this.candidateLanguages.clear();
      this.candidateWorkExperiences.clear();
      this.reloadCurrentRoute();
    }, () => {
      this.reloadCurrentRoute();
    });
    return true;
  }

  private dismissVerifyCandidate() {
    this.currentCandidateToVerify = undefined;
    this.candidateSkills.clear();
    this.candidateLanguages.clear();
    this.candidateEducations.clear();
    this.candidateWorkExperiences.clear();
    this.reloadCurrentRoute();
    return true;
  }

  addSkillFormControl() {
    const skillForm = this.form.group({
      skill: ['',Validators.required]
    });
    this.candidateSkills.push(skillForm);
  }

  deleteSkillFormControl(index: number) {
    this.candidateSkills.removeAt(index);
  }

  addWorkExperienceFormControl() {
    const workExperienceForm = this.form.group({
      jobTitle: ['',Validators.required],
      company: ['',Validators.required],
      location: ['',Validators.required],
      duration: ['',Validators.required],
      jobSummary: ['',Validators.required],
    });
    this.candidateWorkExperiences.push(workExperienceForm);
  }

  deleteWorkExperienceFormControl(index: number) {
    this.candidateWorkExperiences.removeAt(index);
  }

  addLanguageFormControl() {
    const languageForm = this.form.group({
      language: ['',Validators.required],
      level: ['',Validators.required]
    });
    this.candidateLanguages.push(languageForm);
  }

  deleteLanguageFormControl(index: number) {
    this.candidateLanguages.removeAt(index);
  }

  addEducationFormControl() {
    const educationForm = this.form.group({
      education: ['',Validators.required],
      institute: ['',Validators.required],
      duration: ['',Validators.required],
      location: ['',Validators.required]
    });
    this.candidateEducations.push(educationForm);
  }

  deleteEducationFormControl(index: number) {
    this.candidateEducations.removeAt(index);
  }

  reloadCurrentRoute() {
    this.isLoading = true;
    this.candidateService.getAllCandidates().subscribe((candidates) => {
      this.candidates = candidates;
      this.isLoading = false;
    });
  }

  deleteCandidate(candidateId: number) {
    this.candidateService.deleteCandidate(candidateId).subscribe(() => {
      this.reloadCurrentRoute();
    })
  }
}

