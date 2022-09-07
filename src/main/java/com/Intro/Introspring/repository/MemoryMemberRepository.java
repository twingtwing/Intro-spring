package com.Intro.Introspring.repository;

import com.Intro.Introspring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// 임시 DB
// DB가 선정되면 바뀌기 때문에 쉽게 변경할 수 있도록, interface로 설곈
public class MemoryMemberRepository implements MemberRepository{
    /**
     * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
     */

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //Null을 감싸줌
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // Loop를 돌려서 원하는 값을 filter해옴
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }

}
