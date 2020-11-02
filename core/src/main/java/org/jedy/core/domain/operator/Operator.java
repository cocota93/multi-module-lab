package org.jedy.core.domain.operator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints={
            @UniqueConstraint(
                    columnNames={"loginId"}
            )
        }
)
@ToString(of = {"id", "loginId"} )
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operator_id")
    private Long id;

    private String loginId;

//    @JsonIgnore
//    @Column(updatable = false, nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<OperatorAuth> authorityList = new ArrayList<>();

    public Operator(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public void addAuthority(OperatorAuth operatorAuth){
        operatorAuth.changeOwner(this);
    }
}
