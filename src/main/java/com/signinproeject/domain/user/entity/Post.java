package com.signinproeject.domain.user.entity;

import javax.persistence.*;

import com.signinproeject.domain.user.service.post.PostUpdateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "post")
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy = "post")
    private List<Likes> likes;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();



    public Post(String title, String description,  Member member) {
        this.title = title;
        this.description = description;
        this.member = member;
    }

    public void editPost(PostUpdateRequest postUpdateRequest){
        this.title = postUpdateRequest.getTitle();
        this.description = postUpdateRequest.getDescription();
    }
}
