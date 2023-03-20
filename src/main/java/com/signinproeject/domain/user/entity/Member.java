package com.signinproeject.domain.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    //test
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id",nullable = false,length = 20)
    private String accountId;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    private String studentNumber;


}
