package org.jedy.core.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.jedy.core.domain.operator.Operator;
import org.jedy.core.domain.operator.OperatorRepository;
import org.jedy.core.domain.operator.QOperator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class OperatorRepositoryTest {

    @Autowired
    OperatorRepository operatorRepository;
    @Autowired EntityManager em;


    @Test
    @Commit
    public void operatorCreate() throws Exception{
        Operator jedy = new Operator("jedy", "1234");
        operatorRepository.save(jedy);
        em.flush();

        Operator findOperator = operatorRepository.findById(1L).get();
        assertEquals(jedy.getLoginId(),findOperator.getLoginId());
    }

    @Test
    @Commit
    public void qoperatorCreate() throws Exception{
        Operator jedy = new Operator("jedy", "1234");
        operatorRepository.save(jedy);
        em.flush();

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QOperator qOperator = QOperator.operator;

        Operator findOperator = queryFactory
                .select(qOperator)
                .from(qOperator)
                .fetchOne();
        assertEquals(jedy.getLoginId(),findOperator.getLoginId());
    }

}