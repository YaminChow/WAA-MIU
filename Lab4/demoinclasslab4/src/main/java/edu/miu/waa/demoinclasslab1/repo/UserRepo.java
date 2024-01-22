package edu.miu.waa.demoinclasslab1.repo;

import edu.miu.waa.demoinclasslab1.entity.Post;
import edu.miu.waa.demoinclasslab1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    @Query(value ="select distinct " +
            "u from User u join u.posts p where p.title = :title")
            public List<User>  findUserByPostTitle(String title);
    @Query("select u from User u where size(u.posts) > :postnumber")
    public List<User> findUserByPostsGreaterThan(int postnumber);

    @Query ("select u from User u join u.posts p where u.id = :userId and p.id = :postId")
    public User findByPostId(Long userId ,Long postId);

    @Query ("select distinct u from User u join u.posts p join p.commets c " +
            "where u.id = :userId and p.id = :postId and c.id = :commentId")
    public User findByPostsWithCommentId(Long userId, Long postId, Long commentId);




}
