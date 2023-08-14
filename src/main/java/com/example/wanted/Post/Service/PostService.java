package com.example.wanted.Post.Service;

import com.example.wanted.Post.Entity.Post;
import com.example.wanted.Post.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Post savedPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> postList() {
        return postRepository.findAll();
    }

    public Optional<Post> getPost(int postId) {
        return postRepository.findById(postId);
    }
}
