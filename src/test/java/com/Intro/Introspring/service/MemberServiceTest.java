package com.Intro.Introspring.service;

import com.Intro.Introspring.domain.Member;
import com.Intro.Introspring.repository.MemberRepository;
import com.Intro.Introspring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){ //테스트는 독립적으로 사용해야하므로 선언또한 개별적으로 시작함??
        //memberService의 memberRepository와 여기의 memberRepository가 동일해짐
        //외부에서 생성자를 통해 변수를 입력해 동일하게 만드는것을 Dependices Injection (DI)라고 함 
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //Test case 는 기본적을 given - when - then 방식으로 진행됨
        //given : 어떤 데이터를 기반으로
        Member member = new Member();
        member.setName("intro");

        //when : 무슨 상황이 벌어졌을 때
        Long saveId = memberService.join(member);

        //then : 어떻게 검증하는가?
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try{
//            memberService.join(member2);
//            Assertions.fail();
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}