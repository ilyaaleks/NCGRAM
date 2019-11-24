package com.nc.edu.backend.service.impl;

import com.nc.edu.backend.domain.Comment;
import com.nc.edu.backend.domain.Post;
import com.nc.edu.backend.domain.User;
import com.nc.edu.backend.repository.PostRepository;
import com.nc.edu.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll() {
        return (List<Post>) postRepository.findAll();
    }

    public Post findById(long id)
    {
        return postRepository.findById(id).get();
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
    public Set<Comment> getPostCpmments(Long id)
    {
        return postRepository.findById(id).get().getComments();
    }
    public long count(){
        return postRepository.count();
    }

}
