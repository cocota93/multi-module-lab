package org.jedy.core.domain.operator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

//    @JsonIgnore
//    @Column(updatable = false, nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<OperatorAuth> authorityList = new ArrayList<>();

    public Operator(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public void addAuthority(OperatorAuth operatorAuth){
        authorityList.add(operatorAuth);
    }
}
