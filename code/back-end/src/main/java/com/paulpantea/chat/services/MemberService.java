package com.paulpantea.chat.services;

import com.paulpantea.chat.core.Member;
import com.paulpantea.chat.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member getMemberByUsername(String username) {
        return this.memberRepository.findByUsername(username);
    }

    public List<Member> getMembersByNames(List<String> members) {

        List<Member> result = new ArrayList<>();

        for (String username : members) {
            Member member = this.memberRepository.findByUsername(username);

            if (member == null) {
                continue;
            }

            result.add(member);
        }

        return result;
    }
}
