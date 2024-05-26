import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {LayoutComponent} from "./components/layout/layout.component";
import {BootcampComponent} from "./components/layout/bootcamp/bootcamp.component";
import {AuthGuardService} from "./guard/AuthGuardService";
import {CandidateComponent} from "./components/layout/candidate/candidate.component";
import {TestComponent} from "./components/layout/test/test.component";
import {TestInfoComponent} from "./components/layout/test-info/test-info.component";
import {PageNotFoundComponent} from "./components/page-not-found/page-not-found.component";

const routes: Routes = [
  {path: '', redirectTo: 'dashboard/candidate', pathMatch: "full"},
  {path: 'login', component: LoginComponent},
  {path: 'dashboard', redirectTo: 'dashboard/candidate', pathMatch: "full"},
  {
    path: 'dashboard', component: LayoutComponent, canActivate: [AuthGuardService], children: [
      {path: 'bootcamp', component: BootcampComponent},
      {path: 'candidate', component: CandidateComponent},
      {path: 'test', component: TestComponent},
      {path: 'testInfo/:testId', component: TestInfoComponent}
    ]
  },
  { path: '**', pathMatch: 'full', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
