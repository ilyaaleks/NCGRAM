package com.nc.edu.backend.service.impl;

import com.nc.edu.backend.converter.Converter;
import com.nc.edu.backend.domain.Comment;
import com.nc.edu.backend.domain.HashTag;
import com.nc.edu.backend.domain.Post;
import com.nc.edu.backend.domain.User;
import com.nc.edu.backend.dto.PostDto;
import com.nc.edu.backend.dto.PostPageDto;
import com.nc.edu.backend.repository.HashTagRepository;
import com.nc.edu.backend.repository.PostRepository;
import com.nc.edu.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private HashTagRepository tagRepository;

    @Value("${upload.path}")
    private String uploadPath;


    public PostPageDto findAll(Pageable pageable) {
        Page<Post> page = postRepository.findAll(pageable);
        List<Post> postList = page.getContent();
        List<PostDto> postDtoList = new ArrayList<>();
        Converter converter = new Converter();
        PostDto postDto;
        for (Post post : postList) {
            postDto=converter.converToPostDto(post);
            postDto.setAuthorPhotoPath(post.getAuthor().getPhotoUrl());
            postDtoList.add(converter.converToPostDto(post));
        }
        return new PostPageDto(postDtoList,
                pageable.getPageNumber(),
                page.getTotalPages());
    }

    public Post findById(long id) {
        return postRepository.findById(id);
    }

    public PostDto save(Post post, String fileStr, String fileName, String hashTags) {
        String decoded = new String(Base64.getDecoder().decode(fileStr));
        byte[] file = Base64.getDecoder().decode(fileStr);
        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + fileName;
            try (FileOutputStream fos = new FileOutputStream(uploadPath + "/" + resultFilename)) {
                fos.write(file);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            post.setPhotoPath(resultFilename);
            post =postRepository.save(post);
            Set<HashTag> tagList = new HashSet<>();
            if (hashTags != null && !hashTags.equals("")) {
                String[] tags = hashTags.split("#");
                for (String tag : tags) {
                    if (tag != null && !tag.equals("") && !tag.equals(" ")) {
                        HashTag hashTag=tagRepository.findByText(tag);
                        if(hashTag==null) {
                            HashTag ht=new HashTag(tag);
                            ht.getPosts().add(post);
                            tagList.add(ht);
                        }
                        else
                        {
                            hashTag.getPosts().add(post);
                            tagList.add(hashTag);
                        }
                    }
                }
            }
            Iterable<HashTag> hashTagsObj = tagRepository.saveAll(tagList);
        }
        Converter converter=new Converter();
        return converter.converToPostDto(post);
    }

    public void delete(long id) {
        postRepository.deleteById(id);
    }

    public void saveAll(Iterable<Post> posts) {
        postRepository.saveAll(posts);
    }

    public User getUserByPostId(Long id) {
        return postRepository.findById(id).get().getAuthor();

    }

    public Set<Comment> getPostComments(Long id) {
        return postRepository.findById(id).get().getComments();
    }

    public long count() {
        return postRepository.count();
    }

    public PostPageDto getPostOfAuthor(long userId,Pageable pageable) {
        Page<Post> page = postRepository.findByAuthor(userId,pageable);
        List<Post> postList = page.getContent();
        List<PostDto> postDtoList = new ArrayList<>();
        Converter converter = new Converter();
        PostDto postDto;
        for (Post post : postList) {
            postDto=converter.converToPostDto(post);
            postDto.setAuthorPhotoPath(post.getAuthor().getPhotoUrl());
            postDtoList.add(converter.converToPostDto(post));
        }
        return new PostPageDto(postDtoList,
                pageable.getPageNumber(),
                page.getTotalPages());
    }
}
