package com.signinproeject.domain.user.service;

import com.signinproeject.domain.user.entity.Grade;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberResponse {
    private String accountId;

    private String name;

    private Grade grade;


    private String studentNumber;
}
