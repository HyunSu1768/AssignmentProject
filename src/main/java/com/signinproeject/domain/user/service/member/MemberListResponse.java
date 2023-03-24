package com.signinproeject.domain.user.service.member;

import com.signinproeject.domain.user.service.post.PostResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberListResponse {

    public List<MemberResponse> memberResponseList;

}
