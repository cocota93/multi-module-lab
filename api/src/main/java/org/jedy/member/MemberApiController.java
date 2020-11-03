package org.jedy.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jedy.core.domain.member.Member;
import org.jedy.core.domain.member.MemberAuth;
import org.jedy.core.domain.member.MemberAuthType;
import org.jedy.core.domain.member.MemberRepository;
import org.jedy.member.exception.MemberLoginFailException;
import org.jedy.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Slf4j
public class MemberApiController {

    private final MemberRepository memberRepository;
    @Autowired PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public List<Member> getMembers() {

        return memberRepository.findAll();
    }

    @GetMapping(value = "/create")
    public MemberCreateResponse create() {
        Member member = new Member("jedy", passwordEncoder.encode("1234"), "ryong", "cocota93@gmail.com");
        member.addAuthority(new MemberAuth(member, MemberAuthType.COMMON_USER));
        memberRepository.save(member);
        return new MemberCreateResponse(member);

    }

    @PostMapping(value = "/login")
    public String login(@RequestParam String loginId, @RequestParam String password) {
        Member loginMember = memberRepository.findFetchAuthByLoginId(loginId);
        if (loginMember == null || !passwordEncoder.matches(password, loginMember.getPassword())){
            throw new MemberLoginFailException(loginId);
        }

        List<String> authorityList = loginMember.getAuthorityList().stream()
                .map(auth -> auth.getType().getAuthority())
                .collect(Collectors.toList());

        String token = jwtTokenProvider.createToken(String.valueOf(loginMember.getLoginId()), authorityList);
        log.info("token : " + token);
        log.info("Jwt 토큰으로 인증 정보를 조회 : " + jwtTokenProvider.getAuthentication(token));
        log.info("Jwt 토큰에서 회원 구별 정보 추출 : " + jwtTokenProvider.getUserPk(token));
//        log.info("Request의 Header에서 token 파싱 : " + jwtTokenProvider.resolveToken(header));
        log.info("Jwt 토큰의 유효성 + 만료일자 확인 : " + jwtTokenProvider.validateToken(token));

        return token;
    }

    //postman으로 헤더에 X_AUTH_TOKEN 제대로 안넣어서 보내면 requestId가 익명으로 나오고
    //제대로 넣어서 보내면 누군지 식별됨.
    //이대한 일련의 과정은 JwtAuthenticationFilter에서 처리됨.
    @PostMapping(value = "/modify")
    public String modify(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String requestId = authentication.getName();

        Member findMember = memberRepository.findFetchAuthByLoginId(requestId);
        if(findMember == null){
            return "";
        }

        return "modify";
    }

}
