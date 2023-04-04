package com.signinproeject.domain.post.entity;

import javax.persistence.*;

import com.signinproeject.domain.post.controller.dto.request.PostUpdateRequest;
import com.signinproeject.domain.comment.entity.Comment;
import com.signinproeject.domain.like.entity.Likes;
import com.signinproeject.domain.user.entity.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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


    public Post(Long id, String title, String description,  Member member) {
        this.title = title;
        this.description = description;
        this.member = member;
    }

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
