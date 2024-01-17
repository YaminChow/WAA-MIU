package edu.miu.waa.demoinclasslab1.repo.impl;

import edu.miu.waa.demoinclasslab1.dto.request.ReqUpdatePost;
import edu.miu.waa.demoinclasslab1.dtos.PostDtos;
import edu.miu.waa.demoinclasslab1.entity.Post;
import edu.miu.waa.demoinclasslab1.repo.PostRepo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class PostRepoImpl implements PostRepo {
    private static List<Post> posts;
    private static long postId = 200;

    static {
        posts = new ArrayList<>();
        Post p1 = new Post(100, "Head To Servlet and JPA", "Servelet & JPA", "O'Reilly");
        Post p2 = new Post(101, "Learning React", "Learning React", "O'Reilly");
        posts.add(p1);
        posts.add(p2);
    }
    @Override
    public List<Post> findAll() {
        return posts;
    }

    @Override
    public Post findById(long id) {
        return posts.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Post save(Post post) {
        post.setId(postId);
        posts.add(post);
        postId++;
        return post;
    }

    @Override
    public void delete(long id) {
        Post post = posts.stream()
                            .filter(p -> p.getId() == id)
                            .findFirst()
                            .orElse(null);
        if(post != null) {
            posts.remove(post);
        }

    }

    @Override
    public Post update(long id, ReqUpdatePost reqPost) {
       var post = posts.stream()
               .filter(p-> p.getId() == id)
               .findFirst().get();
       post.setTitle(reqPost.getTitle());
       post.setContent(reqPost.getContent());
       post.setAuthor(reqPost.getAuthor());
       return post;
    }

    @Override
    public List<Post> findByAuthor(String author) {
        return posts.stream()
                .filter(p -> p.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findByAuthorName(String authorName) {
        return posts.stream()
                .filter(p -> p.getAuthor().contains(authorName))
                .collect(Collectors.toList());
    }
}
