package org.jedy.operator.controller;

import org.jedy.core.domain.operator.Operator;
import org.jedy.core.domain.operator.OperatorAuth;
import org.jedy.core.domain.operator.OperatorAuthType;
import org.jedy.core.domain.operator.OperatorRepository;
import org.jedy.operator.dto.OperatorCreateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/operator")
public class OperatorController {

    @Autowired OperatorRepository operatorRepository;
    @Autowired PasswordEncoder passwordEncoder;

    @GetMapping("/create")
    @ResponseBody
    public OperatorCreateResponse create(){
        Operator operator = new Operator("jedy", passwordEncoder.encode("1234"));
        operator.addAuthority(new OperatorAuth(operator, OperatorAuthType.PAY_MANAGER));
        operatorRepository.save(operator);
        return new OperatorCreateResponse(operator);
    }

}
