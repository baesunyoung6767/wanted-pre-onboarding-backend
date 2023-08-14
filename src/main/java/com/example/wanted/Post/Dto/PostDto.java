package com.example.wanted.Post.Dto;

import com.example.wanted.Post.Entity.Post;
import com.example.wanted.User.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private String postTitle;
    private String postContent;
    private String postUser;

    public Post toEntity(User postUser) {
        return new Post(this.postTitle, this.postContent, postUser);
    }
}
