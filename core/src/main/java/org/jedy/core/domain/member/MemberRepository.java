package org.jedy.core.domain.member;


import org.jedy.core.domain.operator.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m join fetch m.authorityList auth where m.loginId = :loginId")
    Member findFetchAuthByLoginId(@Param("loginId") String loginId);
}
