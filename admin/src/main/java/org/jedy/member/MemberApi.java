package org.jedy.member;

import lombok.RequiredArgsConstructor;
import org.jedy.core.member.Member;
import org.jedy.core.member.MemberRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberApi {

  private final MemberRepository memberRepository;

  @GetMapping
  public List<Member> getMembers() {
    return memberRepository.findAll();
  }

  @PostMapping
  public Member create() {
    final Member member = new Member("test");
    return memberRepository.save(member);
  }


}
