package org.jedy.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jedy.core.domain.member.Member;
import org.jedy.core.domain.member.MemberAuth;
import org.jedy.core.domain.member.MemberAuthType;
import org.jedy.core.domain.member.MemberRepository;
import org.jedy.member.dto.response.MemberCreateResponse;
import org.jedy.core.domain.member.ReqSignupMember;
import org.jedy.member.exception.MemberLoginFailException;
import org.jedy.member.service.MemberAuthService;
import org.jedy.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Slf4j
public class MemberApiController {

    private final MemberAuthService memberAuthService;

    @PostMapping(value = "/signup")
    public MemberCreateResponse signup(@Valid ReqSignupMember reqSignupMember) {
        MemberCreateResponse member = memberAuthService.signup(reqSignupMember);
        return member;
    }


    @PostMapping(value = "/login")
    public String login(@RequestParam String loginId, @RequestParam String password) {
        String loginToken = memberAuthService.login(loginId, password);
        return loginToken;
    }

    //postman으로 헤더에 X_AUTH_TOKEN 제대로 안넣어서 보내면 requestId가 익명으로 나오고
    //제대로 넣어서 보내면 누군지 식별됨.
    //이대한 일련의 과정은 JwtAuthenticationFilter에서 처리됨.
//    @PostMapping(value = "/modify")
//    public String modify(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String requestId = authentication.getName();
//
//        Member findMember = memberRepository.findFetchAuthByLoginId(requestId);
//        if(findMember == null){
//            return "";
//        }
//
//        return "modify";
//    }

}
