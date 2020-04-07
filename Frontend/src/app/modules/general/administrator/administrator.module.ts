import {NgModule} from '@angular/core';
import {AppRoutingModule} from '../../../app-routing.module';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {AdministratorComponent} from './administrator.component';
import {RouterModule} from '@angular/router';

@NgModule({
  declarations: [
   AdministratorComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule
  ],
  exports: [RouterModule],
  providers: []
})

export class AdministratorModule {
  constructor() {

  }
}
