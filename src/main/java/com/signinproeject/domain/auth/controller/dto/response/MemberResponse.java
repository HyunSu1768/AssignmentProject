package com.signinproeject.domain.auth.controller.dto.response;

import com.signinproeject.domain.comment.controller.dto.response.CommentResponse;
import com.signinproeject.domain.like.controller.dto.response.LikeResponse;
import com.signinproeject.domain.post.controller.dto.response.PostResponse;
import com.signinproeject.domain.user.entity.entity.Member;
import com.signinproeject.domain.user.entity.grade.Grade;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class MemberResponse {

    private String accountId;

    private String name;

    private Grade grade;

    private int studentNumber;

    private List<PostResponse> postResponses;

    public static MemberResponse of(Member member){
        List<PostResponse> postResponseList = member.getPostList().stream()
                .map(it -> PostResponse.builder()

                        .viewCount(it.getViewCount())
                        .like(it.getLikeCount())

                        .likeResponses(it.getLikes().stream().map(it1 -> LikeResponse.builder()
                                .postId(it.getId())
                                .memberId(it.getMember().getId())
                                .build())
                                .toList())

                        .description(it.getDescription())
                        .memberId(it.getMember().getId())
                        .title(it.getTitle())

                        .commentResponses(it.getComments().stream()
                                .map(it1 -> CommentResponse.builder()
                                        .createTime(it1.getCreateTime())
                                        .content(it1.getContent())
                                        .build())
                                .toList())
                        .build())
                .toList();
        return MemberResponse.builder()
                .studentNumber(member.getStudentNumber())
                .accountId(member.getAccountId())
                .name(member.getName())
                .grade(member.getGrade())
                .postResponses(postResponseList)
                .build();

    }
}
