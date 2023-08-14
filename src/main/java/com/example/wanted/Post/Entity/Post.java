package com.example.wanted.Post.Entity;

import com.example.wanted.User.Entity.User;
import com.example.wanted.User.Service.UserService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int postId;

    @Column(nullable = false)
    private String postTitle;

    @Column(nullable = false)
    private String postContent;

    @ManyToOne
    @JoinColumn(name = "email")
    private User user;

    public Post(String postTitle, String postContent, User postUser) {
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.user = postUser;
    }
}
