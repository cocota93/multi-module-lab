package org.jedy.core.domain.operator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames={"owner_id", "type"}
                )
        }
)
@ToString(of = {"id", "type"} )
public class OperatorAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operator_auth_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OperatorAuthType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Operator owner;

    public OperatorAuth(OperatorAuthType type) {
        this.type = type;
    }

    public void changeOwner(Operator owner){
        this.owner = owner;
        this.owner.getAuthorityList().add(this);
    }
}
