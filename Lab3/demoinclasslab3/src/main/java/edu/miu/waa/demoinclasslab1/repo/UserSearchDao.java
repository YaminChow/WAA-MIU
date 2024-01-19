package edu.miu.waa.demoinclasslab1.repo;

import edu.miu.waa.demoinclasslab1.dto.input.UserCriteriaRequest;
import edu.miu.waa.demoinclasslab1.entity.Commet;
import edu.miu.waa.demoinclasslab1.entity.Post;
import edu.miu.waa.demoinclasslab1.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserSearchDao {
    private final EntityManager em;
    public List<User> findAllByCriteria(UserCriteriaRequest userCriteriaRequest){ // You can make a search request object for the input
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        List<Predicate> predicates = new ArrayList<>();

        // select * from Comment
        Root<User> rootUser = criteriaQuery.from(User.class);
        Root<Post> rootPost = criteriaQuery.from(Post.class);

        Join<Post, User> userPostJoin = rootUser.join("posts", JoinType.INNER);
        Join<Commet, Post> commentPostJoin = rootPost.join("commets", JoinType.INNER);

        System.out.println("Post User>>>"+userPostJoin.getParent());
        System.out.println("Commet Post>>>"+commentPostJoin.getParent());


        List<Predicate> predicateList = new ArrayList<Predicate>();
        predicates.add(criteriaBuilder.equal(userPostJoin.get("id"), userCriteriaRequest.getUserId()));
        predicates.add(criteriaBuilder.equal(userPostJoin.get("commets").get("id"), userCriteriaRequest.getPostId()));
        predicates.add(criteriaBuilder.equal(commentPostJoin.getParent().get("commets").get("id"), userCriteriaRequest.getCommentId()));
        criteriaQuery.where(criteriaBuilder.and((Predicate[]) predicates.toArray(new Predicate[0])));
        criteriaQuery.select(rootUser).distinct(true);
        return em.createQuery(criteriaQuery).getResultList(); //Without statement#: Throw




//        Predicate userPredicate = criteriaBuilder.equal(rootUser.get("id"), userCriteriaRequest.getPostId());
//
//        System.out.println("user: >>>" + userPredicate.toString());
//        predicates.add(userPredicate);
//
//        if(userCriteriaRequest.getPostId()!=null){
//            System.out.println("post exits.!>>>>");
//            Predicate postPredicate = criteriaBuilder.equal(rootUser.get("posts").get("id"), userCriteriaRequest.getUserId());
//            System.out.println("post:: >>>"+postPredicate.toString());
//            predicates.add(postPredicate);
//        }
//        if(userCriteriaRequest.getCommentId()!=null){
//            System.out.println("Comment exits>>>");
//            Predicate commetPredicate = criteriaBuilder.equal(rootPost.get("commets").get("id"),userCriteriaRequest.getCommentId());
//            System.out.println("Comment:: >>>"+commetPredicate.toString());
//            predicates.add(commetPredicate);
//        }
//        predicates.forEach(System.out::println);
//
//        criteriaQuery.where(
//                criteriaBuilder.and(predicates.toArray(new Predicate[0]))
//        );
//
//        TypedQuery<User> query = em.createQuery(criteriaQuery);
//        query.getResultList().forEach(System.out::println);
//        return query.getSingleResult();
    }


}
