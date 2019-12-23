import {PostModel} from "./postModel";
import {CommentModel} from "./comment-model";

export interface CommentPageDto {
  comments: Array<CommentModel>
  currentPage:number
  totalPage:number
}
