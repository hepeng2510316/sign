package com.demo.hibernate.entities;


import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "COMMENT", catalog = "sign")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "缺少recordId")
    @Column(name = "RECORD_ID", nullable = false)
    private Integer recordId;

    @NotBlank(message = "缺少openId")
    @Column(name = "OPEN_ID", nullable = false)
    private String openId;

    @NotBlank(message = "缺少commentInfo")
    @Column(name = "COMMENT_INFO", nullable = false)
    private String commentInfo;
}
