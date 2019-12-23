export interface UserModel {
  id:number;
  name:string;
  surname:string;
  aboutMe:string;
  login:string;
  password:string;
  role:string;
  status:string;
  photoUrl:string;
  email:string;
  subscribed?:boolean;
  countOfSubscribers?:number;
  countOfSubscribtions?:number;
}
