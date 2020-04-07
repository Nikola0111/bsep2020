import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';

const httpOptions = {
  headers: new HttpHeaders( {'Content-Type' : 'application/json'})
}

@Component({
  selector: 'app-administrator',
  templateUrl: './administrator.component.html',
  styleUrls: ['./administrator.component.css']
})

export class AdministratorComponent implements OnInit {
  constructor(private http: HttpClient, private router: Router) {
  }
  onSubmit() {

  }
  ngOnInit() {

  }

}
