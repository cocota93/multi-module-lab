package org.jedy.member;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jedy.core.domain.member.Member;
import org.jedy.core.domain.member.MemberAuth;
import org.jedy.core.domain.member.MemberAuthType;
import org.jedy.core.domain.operator.Operator;
import org.jedy.core.domain.operator.OperatorAuth;
import org.jedy.core.domain.operator.OperatorAuthType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCreateResponse {

    private Long id;

    private String loginId;

    private String name;

    private String email;

    private List<MemberAuthType> authList = new ArrayList<>();

    public MemberCreateResponse(Member member) {
        this.id = member.getId();
        this.loginId = member.getLoginId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.authList = member.getAuthorityList().stream()
                .map(MemberAuth::getType)
                .collect(Collectors.toList());
    }
}
