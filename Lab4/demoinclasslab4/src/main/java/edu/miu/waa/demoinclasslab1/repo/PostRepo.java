package edu.miu.waa.demoinclasslab1.repo;

import edu.miu.waa.demoinclasslab1.dto.request.ReqUpdatePost;
import edu.miu.waa.demoinclasslab1.dtos.PostDtos;
import edu.miu.waa.demoinclasslab1.entity.Post;
import edu.miu.waa.demoinclasslab1.entity.Commet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {
    List<Post> findPostByAuthor(String author);
    @Query("Select p from Post p where p.author LIKE %:authorName%")
    List<Post> findPostByAuthorName(@Param("authorName") String authorName);

    List<Post> findPostByTitle(String title);

    List<Post> findPostsByAuthorContains(String authorName);
//    @Query("Select p.commets from Post p join p.commets where p.id = :id")
//    List<Post> findCommentByPostId(Long id);

}
