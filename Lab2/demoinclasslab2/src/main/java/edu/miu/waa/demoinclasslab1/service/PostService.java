package edu.miu.waa.demoinclasslab1.service;

import edu.miu.waa.demoinclasslab1.dto.request.ReqUpdatePost;
import edu.miu.waa.demoinclasslab1.dtos.PostDtos;
import edu.miu.waa.demoinclasslab1.entity.Post;

import java.util.List;

public interface PostService {
    List<PostDtos>  findAll();

    Post findById(long id);

    Post save(Post post);

    void delete(long id);

    Post update (long id, Post post);

    List<PostDtos> findByAuthor(String author);

    List<PostDtos> findByAuthorName(String authorName);
}
