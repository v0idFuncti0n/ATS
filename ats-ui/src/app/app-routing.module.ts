import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {LayoutComponent} from "./components/layout/layout.component";
import {BootcampComponent} from "./components/layout/bootcamp/bootcamp.component";
import {AuthGuardService} from "./guard/AuthGuardService";

const routes: Routes = [
  {path: '', redirectTo: 'layout', pathMatch: "full"},
  {path: 'login', component: LoginComponent},
  {
    path: 'layout', component: LayoutComponent, canActivate: [AuthGuardService], children: [
      {path: 'bootcamp', component: BootcampComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
