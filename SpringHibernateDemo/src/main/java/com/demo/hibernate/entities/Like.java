package com.demo.hibernate.entities;


import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "LIKE", catalog = "sign")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Like implements Serializable{

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


}
