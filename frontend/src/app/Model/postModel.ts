import {UserModel} from "./userModel";

export interface PostModel {
  id:number;
  authorLogin:string;
  authorPhotoPath?:string;
  photoPath:string;
  text:string;
  date:string;
  hashTags:string;

}
