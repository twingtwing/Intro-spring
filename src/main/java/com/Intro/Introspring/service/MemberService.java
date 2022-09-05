package com.Intro.Introspring.service;

import com.Intro.Introspring.domain.Member;
import com.Intro.Introspring.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 내부의 memberRepository와 외부의 memberRepository가 달라지는 문제가 생김
     * 그렇기에 외부에서 쓰는 변수와 똑같도록 외부에서 입력할 수 있도록 생성자를 사용해야함
     */
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    //중복이름 불가
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                }); //ifPresent : null이면 해당 로직을 실행함
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
