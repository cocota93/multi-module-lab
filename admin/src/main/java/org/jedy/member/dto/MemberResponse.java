package org.jedy.member.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jedy.core.domain.member.Member;
import org.omg.CORBA.WStringSeqHelper;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponse {

    private String name;

    public MemberResponse(final Member member) {
        this.name = member.getName();
    }
}
