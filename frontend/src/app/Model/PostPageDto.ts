import {PostModel} from "./postModel";

export interface PostPageDto {
  posts: Array<PostModel>
  currentPage:number
  totalPage:number
}
