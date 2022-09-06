package com.Intro.Introspring.repository;

import com.Intro.Introspring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository{
// JpaRepository을 상속받으면, 구현체를 자동으로 만들어서 spring를 자동으로 등록을 해줌
    //JpaRepository가 기본적인 메서드를 다 제공해줌(save,page처리 등등 까지 전부 만들어줌)
    // 해당 클래스로 가서 확인 가능
    // 단, pk가 아닌 값을 검색을 할경우는 공통으로 제공해주지 못함

    @Override
    Optional<Member> findByName(String name);
    //그러나, 메서드 이름을 특정 규칙에 맞게 써주면, 자동으로 만들어줌
}
