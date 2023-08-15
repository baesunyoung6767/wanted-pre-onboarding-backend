package com.example.wanted.Post.Service;

import com.example.wanted.Exception.AppException;
import com.example.wanted.Exception.ErrorCode;
import com.example.wanted.Post.Dto.PageResponseDto;
import com.example.wanted.Post.Dto.PostDto;
import com.example.wanted.Post.Dto.PostUpdateDto;
import com.example.wanted.Post.Entity.Post;
import com.example.wanted.Post.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public PageResponseDto searchAllPaging(int pageNo, int pageSize, Sort.Direction sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<Post> postPage = postRepository.findAll(pageable);

        List<Post> listPosts = postPage.getContent();

        return PageResponseDto.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalElements(postPage.getTotalElements())
                .totalPages(postPage.getTotalPages())
                .last(postPage.isLast())
                .build();
    }

    @Transactional
    public void updatePost(int postId, String loginUser , PostUpdateDto postUpdateDto) {
        Optional<Post> foundPost = postRepository.findById(postId);

        if(loginUser.equals(foundPost.get().getUser().getEmail())) {
            foundPost.get().updatePost(postUpdateDto.getUpdateTitle(), postUpdateDto.getUpdateContent());
            postRepository.save(foundPost.get());
        } else {
            throw new AppException(ErrorCode.USER_MISMATCH);
        }
    }

    @Transactional
    public void deletePost(int postId, String loginUser) {
        Optional<Post> foundPost = postRepository.findById(postId);

        if(loginUser.equals(foundPost.get().getUser().getEmail())) {
            postRepository.delete(foundPost.get());
        } else {
            throw new AppException(ErrorCode.USER_MISMATCH);
        }
    }
}
