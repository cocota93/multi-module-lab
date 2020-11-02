package org.jedy.operator.controller;

import org.jedy.core.domain.operator.Operator;
import org.jedy.core.domain.operator.OperatorAuth;
import org.jedy.core.domain.operator.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperatorService implements UserDetailsService {

    @Autowired private OperatorRepository operatorRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Operator loginOperator = operatorRepository.findFetchAuthByLoginId(username);
        List<SimpleGrantedAuthority> authorityList = loginOperator.getAuthorityList().stream()
                .map(auth -> new SimpleGrantedAuthority(auth.getType().getAuthority()))
                .collect(Collectors.toList());

        return new User(loginOperator.getLoginId(), loginOperator.getPassword(), authorityList);
    }
}
