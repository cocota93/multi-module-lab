package org.jedy.core.domain.operator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public Operator(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
