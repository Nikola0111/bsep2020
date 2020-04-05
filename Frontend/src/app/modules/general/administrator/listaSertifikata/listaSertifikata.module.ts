import {NgModule} from '@angular/core';
import {AppRoutingModule} from '../../../../app-routing.module';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ListaSertifikataComponent, PreviewSertifikataDialogComponent} from './listaSertifikata.component';
import {Router, RouterModule} from '@angular/router';

@NgModule({
  declarations: [
    ListaSertifikataComponent,
    PreviewSertifikataDialogComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  exports: [RouterModule],
  entryComponents: [PreviewSertifikataDialogComponent]
})
export class ListaSertifikataModule {
  constructor(private router: Router) {

  }
}
