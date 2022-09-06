package com.Intro.Introspring.repository;

import com.Intro.Introspring.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Transactional //class위에하면 모든 메서드가 트랜잭션으로 관리되고?, DDL?클래스 위에만 해놓아도 된다.
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    // jpa는 EntityManager로 모두 관리되는데 스프링 부트가 em을 생성해서 데이터베이슬 받아? 관리해줌
    // 자동으로 em을 injection해줌
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //insert구문 자동 생성해서 모두 처리해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member =  em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("SELECT m FROM MEMBER m",Member.class)
                .getResultList();
    }
}
