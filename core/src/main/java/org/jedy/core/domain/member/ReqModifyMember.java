package org.jedy.core.domain.member;

import lombok.Data;

@Data
public class ReqModifyMember {

    private String password;
    private String name;
    private String email;

}
