package com.signinproeject.domain.user.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberListResponse {

    public List<MemberResponse> memberResponseList;

}
