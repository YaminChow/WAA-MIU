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

        Join<User, Post> userPostJoin = rootUser.join("posts", JoinType.INNER);
        Join<Commet, Post> commentPostJoin = rootPost.join("commets", JoinType.INNER);
        //criteriaQuery.multiselect(rootUser, rootPost).getParameters().forEach(System.out::println);

        System.out.println( ">>>>>>>");

        System.out.println("Post User>>>"+userPostJoin.getParent());
        System.out.println("Commet Post>>>"+commentPostJoin.getParent());


        List<Predicate> predicateList = new ArrayList<Predicate>();
        if(userCriteriaRequest.getUserId() != null)
        predicates.add(criteriaBuilder.equal(userPostJoin.getParent().get("id"), userCriteriaRequest.getUserId()));
        if(userCriteriaRequest.getPostId() != null)
        predicates.add(criteriaBuilder.equal(commentPostJoin.getParent().get("id"), userCriteriaRequest.getPostId()));
       if(userCriteriaRequest.getCommentId() != null)
        predicates.add(criteriaBuilder.equal(commentPostJoin.get("id"), userCriteriaRequest.getCommentId()));
        criteriaQuery.where(criteriaBuilder.and((Predicate[]) predicates.toArray(new Predicate[0])));
        criteriaQuery.select(rootUser).distinct(true);
        return em.createQuery(criteriaQuery).getResultList();
    }


}
