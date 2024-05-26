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
import {ModalComponent} from './components/layout/modal/modal.component';
import {CandidateComponent} from './components/layout/candidate/candidate.component';
import {TestComponent} from './components/layout/test/test.component';
import {TestInfoComponent} from './components/layout/test-info/test-info.component';
import {PdfViewerModule} from "ng2-pdf-viewer";

import {CommonModule} from '@angular/common';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {ToastrModule} from 'ngx-toastr';

import {SweetAlert2Module} from '@sweetalert2/ngx-sweetalert2';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LayoutComponent,
    BootcampComponent,
    ModalComponent,
    CandidateComponent,
    TestComponent,
    TestInfoComponent
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
    }),
    PdfViewerModule,
    CommonModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    SweetAlert2Module.forRoot()
  ],
  providers: [AuthService, AuthGuardService],
  bootstrap: [AppComponent],
})
export class AppModule {
}
