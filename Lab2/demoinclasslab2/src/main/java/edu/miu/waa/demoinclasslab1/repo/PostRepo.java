package edu.miu.waa.demoinclasslab1.repo;

import edu.miu.waa.demoinclasslab1.dto.request.ReqUpdatePost;
import edu.miu.waa.demoinclasslab1.dtos.PostDtos;
import edu.miu.waa.demoinclasslab1.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {
    List<Post> findPostByAuthor(String author);
    @Query("Select p from Post p where p.author LIKE %:authorName%")
    List<Post> findPostByAuthorName(@Param("authorName") String authorName);

}
