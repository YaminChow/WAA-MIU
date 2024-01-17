package edu.miu.waa.demoinclasslab1.controller;

import edu.miu.waa.demoinclasslab1.dto.request.ReqUserDto;
import edu.miu.waa.demoinclasslab1.entity.Post;
import edu.miu.waa.demoinclasslab1.entity.User;
import edu.miu.waa.demoinclasslab1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserService userService;
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<User> findAll(){
        return  userService.findAll();
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public User findById(@PathVariable("id") long userId){
        return userService.findById(userId);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User save(@RequestBody ReqUserDto user){
        return userService.saveUser(user);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/posts")
    public List<Post> findByIdWithPost(@PathVariable("id") long userId){
        return userService.findPostByUserId(userId);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/morethanonepost")
    public List<User> findUserWithMoreThanOnePost(){
        return userService.findUserWithMoreThanOnePost();
    }
}
