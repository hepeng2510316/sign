package com.demo.hibernate.entities;


import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SIGN_RECORD", catalog = "sign")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SignRecord implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "nickName不能为空")
    @Column(name = "OPEN_ID", nullable = false)
    private String openId;

    @Column(name = "SING_INFO", nullable = false)
    @NotBlank(message = "singInfo不能为空")
    private String singInfo;

    @Column(name = "SIGN_TIME", nullable = false)
    private Date signTime;



}
