package edu.miu.waa.demoinclasslab1.controller;

import edu.miu.waa.demoinclasslab1.dto.request.ReqUpdatePost;
import edu.miu.waa.demoinclasslab1.dtos.PostDtos;
import edu.miu.waa.demoinclasslab1.entity.Post;
import edu.miu.waa.demoinclasslab1.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    @Autowired
    PostService postService;
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping
//    public List<PostDtos>  findAll(){
//        return postService.findAll();
//    }
    
    @GetMapping("/{id}")
    public Post findById(@PathVariable("id") Long id){
        return postService.findById(id);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<?> savePost(@RequestBody Post post){
        return  ResponseEntity.ok(postService.save(post));
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("id") long id){
       postService.delete(id);
    }
    @PutMapping("/{id}")
    public Post updatePost(@PathVariable("id") long id, @RequestBody ReqUpdatePost post){
        return postService.update(id,post);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PostDtos> findAll(@RequestParam(value ="authorName", required =false) String author){
         return author == null ?
                 postService.findAll():
                 postService.findByAuthor(author);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/byAuthor")
    public List<PostDtos> findByAuthor(@RequestParam(value ="byAuthor") String author)      {
        System.out.println("Author: >>>"+ author);
        return postService.findByAuthor(author) ;
    }
     @ResponseStatus(HttpStatus.OK)
     @GetMapping("/byAuthorName")
     public List<PostDtos> findByAuthorName(@RequestParam(value ="byAuthorName") String authorName)      {
         System.out.println("ByAuthorName:>>> "+ authorName);
        return postService.findByAuthorName(authorName) ;
     }



}
