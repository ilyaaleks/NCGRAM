package com.nc.edu.backend.service.impl;

import com.nc.edu.backend.domain.Comment;
import com.nc.edu.backend.domain.Post;
import com.nc.edu.backend.domain.User;
import com.nc.edu.backend.dto.PostDto;
import com.nc.edu.backend.dto.PostPageDto;
import com.nc.edu.backend.repository.PostRepository;
import com.nc.edu.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public PostPageDto findAll(Pageable pageable)
    {
        Page<Post> page=postRepository.findAll(pageable);
        List<Post> postList=page.getContent();
        List<PostDto> postDtoList=new ArrayList<>();
        for(Post post :postList)
        {
            postDtoList.add(new PostDto(post.getId(),
                    post.getAuthor().getId(),
                    post.getPhotoPath(),post.getText(),
                    post.getDate()));
        }
        return new PostPageDto(postDtoList,
                pageable.getPageNumber(),
                page.getTotalPages());
    }

    public Post findById(long id)
    {
        return postRepository.findById(id);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public void delete(long id) {
        postRepository.deleteById(id);
    }

    public void saveAll(Iterable<Post> posts)
    {
        postRepository.saveAll(posts);
    }
    public User getUserByPostId(Long id)
    {
        return postRepository.findById(id).get().getAuthor();

    }
    public Set<Comment> getPostComments(Long id)
    {
        return postRepository.findById(id).get().getComments();
    }
    public long count(){
        return postRepository.count();
    }

}
