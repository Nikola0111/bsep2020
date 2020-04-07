import {NgModule} from '@angular/core';
import {FormaComponent} from './forma.component';
import {AppRoutingModule} from '../../../../app-routing.module';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Router, RouterModule} from '@angular/router';

@NgModule({
  declarations: [
    FormaComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  exports: [RouterModule],
})
export class FormaModule {
  constructor(private router: Router) {

  }
}
