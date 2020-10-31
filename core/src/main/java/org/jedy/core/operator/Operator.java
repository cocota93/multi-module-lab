package org.jedy.core.operator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.IdentityHashMap;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

//    @JsonIgnore
    @Column(updatable = false, nullable = false, length = 20)
    private String password;

    public Operator(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
