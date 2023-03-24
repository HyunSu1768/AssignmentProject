package com.signinproeject.domain.user.service.member;

import com.signinproeject.domain.user.entity.Grade;
import com.signinproeject.domain.user.entity.Member;
import com.signinproeject.domain.user.service.post.PostResponse;
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
                        .description(it.getDescription())
                        .memberId(it.getMember().getId())
                        .title(it.getTitle())
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
