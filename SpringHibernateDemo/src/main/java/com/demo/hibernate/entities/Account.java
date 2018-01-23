package com.demo.hibernate.entities;


import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SIGN_USER", catalog = "sign")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "nickName不能为空")
    @Column(name = "NIKE_NAME", nullable = false)
    private String nickName;

    @NotBlank(message = "openId不能为空")
    @Column(name = "OPEN_ID", nullable = false, unique = true)
    private String openId;

    @NotBlank(message = "avatarUrl不能为空")
    @Column(name = "AVATAR_URL", nullable = false)
    private String avatarUrl;

    @Column(name = "CONTINUE_SIGN_COUNT")
    private int continueSignCount;

    @Column(name = "TOTAL_SIGN_COUNT")
    private int totalSignCount;
}
