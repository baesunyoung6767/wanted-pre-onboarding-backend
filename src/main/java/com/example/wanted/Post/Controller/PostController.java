package com.example.wanted.Post.Controller;

import com.example.wanted.Post.Dto.PageResponseDto;
import com.example.wanted.Post.Dto.PostDto;
import com.example.wanted.Post.Dto.PostUpdateDto;
import com.example.wanted.Post.Entity.Post;
import com.example.wanted.Post.Service.PostService;
import com.example.wanted.User.Entity.User;
import com.example.wanted.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void savedPost(@RequestBody PostDto postDto) {
        User foundUser = userService.getUserByEmail(postDto.getPostUser());
        Post post = postDto.toEntity(foundUser);
        postService.savedPost(post);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping
    public List<Post> postList() {
        return postService.postList();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{page_num}/{page_size}/{sort_by}")
    public PageResponseDto pageGetPost(@PathVariable int page_num, @PathVariable int page_size, @PathVariable int sort_by) {
        Sort.Direction sortStr;
        if(sort_by == 0) sortStr = Sort.Direction.ASC;
        else sortStr = Sort.Direction.DESC;

        return postService.searchAllPaging(page_num, page_size, sortStr);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{post_id}")
    public Optional<Post> getPost(@PathVariable int post_id) {
        return postService.getPost(post_id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/{post_id}")
    public void updatePost(@PathVariable int post_id, Principal principal, @RequestBody PostUpdateDto postUpdateDto) {
        String loginUser = principal.getName();
        postService.updatePost(post_id,loginUser,postUpdateDto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @DeleteMapping("/{post_id}")
    public void deletePost(@PathVariable int post_id, Principal principal) {
        String loginUser = principal.getName();
        postService.deletePost(post_id,loginUser);
    }
}
