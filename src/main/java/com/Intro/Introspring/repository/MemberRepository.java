package com.Intro.Introspring.repository;

import com.Intro.Introspring.domain.Member;

import java.util.List;
import java.util.Optional;

// interface 이유 :
// DB가 선정되지 않았기에, 인터페이스로 주요 기능을 먼저 설계하여 DB가 바뀔때마다 클래스를 쉽게 구현하고, 변경할수있도록 설계를 위해
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
