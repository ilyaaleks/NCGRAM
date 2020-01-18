import {TypeOfVote} from "./type-of-vote.enum";

export interface LikeOrDislike {
  id:number;
  postId:number;
  authorId:number;
  typeOfVote:TypeOfVote;
  date:Date;
  countOfLikes?:number;
  countOfDislikes?:number;
}
