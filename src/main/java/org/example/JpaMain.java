package org.example;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // [엔티티 매니저 팩토리] - 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-study");
        // [엔티티 매니저] - 생성
        EntityManager em = emf.createEntityManager();
        // [트랜잭션] - 휙득
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin(); // [트랜잭션] - 시작
            logic(em); // 비즈니스 로직 실행
            tx.commit(); // [트랜잭션] - 커밋

        } catch (Exception e) {
            tx.rollback(); // [트랜잭션] - 롤백
        } finally {
            em.close(); // [엔티티 매니저] - 종료
        }
        emf.close(); // [엔티티 매니저 펙토리] - 종료

    }
    private static void logic(EntityManager em) {
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("유제");
        member.setAge(24);

        //등록
        em.persist(member);
        //INSERT INTO MEMBER (ID, NAME, AGE) VALUES ('id1', '유제', 24)
        //수정
        member.setAge(20);

//        UPDATE MEMBER
        //        SET AGE=20, NAME="유제"
//        WHERE ID='id1'

        //한 건 조회
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember="+findMember.getUsername()+", age="+findMember.getAge());

        //목록 조회
        List<Member> members = em.createQuery("select m from Member m", Member.class) // -> TypedQuery<Member> query
                .getResultList(); // Java Persistence Query Language: class + field에 대하여 쿼리를 날림.
        // SELECT M.ID, M.NAME, M.AGE FROM MEMBER M
        System.out.println("members.size="+members.size());

        //삭제
        em.remove(member);
        //DELETE FROM MEMBER WHERE ID = 'id1';
    }
}
