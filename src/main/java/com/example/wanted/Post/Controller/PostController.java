package com.example.wanted.Post.Controller;

import com.example.wanted.Post.Dto.PostDto;
import com.example.wanted.Post.Entity.Post;
import com.example.wanted.Post.Service.PostService;
import com.example.wanted.User.Entity.User;
import com.example.wanted.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{post_id}")
    public Optional<Post> getPost(@PathVariable int post_id) {
        return postService.getPost(post_id);
    }
}
