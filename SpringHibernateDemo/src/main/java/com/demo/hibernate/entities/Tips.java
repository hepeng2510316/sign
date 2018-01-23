package com.demo.hibernate.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TIPS", catalog = "sign")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Tips implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CONTENT", nullable = false)
    private String content;

}
