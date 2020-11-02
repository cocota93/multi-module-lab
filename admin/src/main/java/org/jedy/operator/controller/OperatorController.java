package org.jedy.operator.controller;

import net.bytebuddy.asm.Advice;
import org.jedy.core.domain.operator.Operator;
import org.jedy.core.domain.operator.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OperatorController {

    @Autowired OperatorRepository operatorRepository;
    @Autowired PasswordEncoder passwordEncoder;

    @GetMapping("/create")
    @ResponseBody
    public Operator create(){
        Operator operator = new Operator("jedy", passwordEncoder.encode("1234"));

        return operatorRepository.save(operator);
    }

}
