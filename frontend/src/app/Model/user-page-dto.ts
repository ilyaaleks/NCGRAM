import {UserModel} from "./userModel";

export interface UserPageDto {
  users: Array<UserModel>
  currentPage:number
  totalPage:number
}
