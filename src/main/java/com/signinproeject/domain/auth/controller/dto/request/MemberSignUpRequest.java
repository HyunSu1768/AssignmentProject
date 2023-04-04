package com.signinproeject.domain.auth.controller.dto.request;

import com.signinproeject.domain.user.entity.grade.Grade;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberSignUpRequest {

    @Size(max = 20,message = "최대 20자 입니다")
    private String accountId;

    @Size(max = 4,min = 2,message = "2자 이상 4자 이하여야 합니다")
    private String name;

    @Pattern(
        regexp = "^(?=.*\\d{4,})(?=.*[a-zA-Z]{4,}).{4,16}$",
        message = "영어 4자 이상, 숫자 4자 이상, 16자 이하여야 합니다."
    )
    private String password;


    private Grade grade;

    @Min(value = 1101, message = "1101학번 이상만 가능합니다")
    @Max(value = 3420, message = "3420학번 이하만 가능합니다")
    private int studentNumber;
}
