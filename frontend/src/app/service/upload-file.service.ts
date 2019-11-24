import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {UserModel} from "../Model/userModel";

@Injectable({
  providedIn: 'root'
})
export class UploadFileService {

  constructor(private httpClient:HttpClient) { }
  postFile(fileToUpload: File, user:UserModel): Observable<boolean> {
    const endpoint = 'api/user/photo/'+user.login;
    const formData: FormData = new FormData();
    formData.append('file', fileToUpload, fileToUpload.name);
    return this.httpClient
      .post(endpoint, formData).pipe(
        map(() => { return true; })
      )
  }
}
