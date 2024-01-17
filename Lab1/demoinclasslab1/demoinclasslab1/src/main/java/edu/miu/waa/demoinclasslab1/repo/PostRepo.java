package edu.miu.waa.demoinclasslab1.repo;

import edu.miu.waa.demoinclasslab1.dto.request.ReqUpdatePost;
import edu.miu.waa.demoinclasslab1.dtos.PostDtos;
import edu.miu.waa.demoinclasslab1.entity.Post;

import java.util.List;

public interface PostRepo {
    List<Post> findAll();

    Post findById(long id);

    Post save(Post post);

    void delete(long id);

    Post update(long id, ReqUpdatePost reqPost);

    List<Post> findByAuthor(String author);

    List findByAuthorName(String authorName);
}
