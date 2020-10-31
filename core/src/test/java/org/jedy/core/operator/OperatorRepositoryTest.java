package org.jedy.core.operator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OperatorRepositoryTest {

    @Autowired OperatorRepository operatorRepository;
    @Autowired EntityManager em;


    @Test
    @Commit
    public void operatorCreate() throws Exception{
        Operator jedy = new Operator("jedy", "1234");
        operatorRepository.save(jedy);
        em.flush();

        Operator findOperator = operatorRepository.findById(1L).get();
        assertEquals(jedy,findOperator);
        System.out.println("operator : " + findOperator);
    }

}