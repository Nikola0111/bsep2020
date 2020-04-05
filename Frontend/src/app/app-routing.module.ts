import {Routes, RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';
import {AdministratorComponent} from './modules/general/administrator/administrator.component';
import {FormaComponent} from './modules/general/administrator/forma/forma.component';
import {ListaSertifikataComponent} from './modules/general/administrator/listaSertifikata/listaSertifikata.component';


const routes: Routes = [
  { path: '', component: AdministratorComponent},
  { path: 'forma', component: FormaComponent},
  { path: 'lista-sertifikata', component: ListaSertifikataComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  declarations: []
})

export class AppRoutingModule {}
