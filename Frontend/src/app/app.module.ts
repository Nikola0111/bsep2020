import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {AdministratorModule} from './modules/general/administrator/administrator.module';
import {HttpClientModule} from '@angular/common/http';
import {FormaModule} from './modules/general/administrator/forma/forma.module';
import {ListaSertifikataModule} from './modules/general/administrator/listaSertifikata/listaSertifikata.module';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AdministratorModule,
    HttpClientModule,
    FormaModule,
    ListaSertifikataModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
