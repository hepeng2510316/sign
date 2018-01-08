package com.demo.hibernate.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TIPS", catalog = "sign")
public class Tips implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "CONTENT", nullable = false)
    private String content;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Tips{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
