package com.Intro.Introspring.domain;

import javax.persistence.*;

@Entity
public class Member { //Domain

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY ) // db가 id를 자동생성하는 전략 : IDENTITY??전략
    private Long id;

    private String name; //column이름이 다르면 @Column(name="")로 직접 수정할 수 있다.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
