import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './components/login/login.component';
import {ReactiveFormsModule} from "@angular/forms";
import {LayoutComponent} from './components/layout/layout.component';
import {BootcampComponent} from './components/layout/bootcamp/bootcamp.component';
import {AuthService} from "./services/AuthService";
import {JwtModule} from "@auth0/angular-jwt";
import {HttpClientModule} from "@angular/common/http";
import {AuthGuardService} from "./guard/AuthGuardService";
import {DataTablesModule} from "angular-datatables";
import { ModalComponent } from './components/layout/modal/modal.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LayoutComponent,
    BootcampComponent,
    ModalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    DataTablesModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: AuthService.tokenGetter
      },
    })
  ],
  providers: [AuthService, AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
