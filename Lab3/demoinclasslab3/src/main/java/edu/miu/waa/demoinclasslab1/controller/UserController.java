package edu.miu.waa.demoinclasslab1.controller;

import edu.miu.waa.demoinclasslab1.dto.request.ReqUser;
import edu.miu.waa.demoinclasslab1.dto.request.ReqUserDto;
import edu.miu.waa.demoinclasslab1.dto.response.ResUser;
import edu.miu.waa.demoinclasslab1.dto.response.ResUserDto;
import edu.miu.waa.demoinclasslab1.dtos.PostDtos;
import edu.miu.waa.demoinclasslab1.entity.Post;
import edu.miu.waa.demoinclasslab1.entity.User;
import edu.miu.waa.demoinclasslab1.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<User> findAll(){
        return  userService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResUser findById(@PathVariable("id") long userId){
        return userService.findById(userId);
    }
    /**
     * Using HATEOAS level 3 method.
     * */

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/h/{id}")
    public EntityModel<ResUser> findByIdH(@PathVariable("id") long userId){
        EntityModel<ResUser> resUser = EntityModel.of(userService.findById(userId));
        //response all user url
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                .linkTo(
                        WebMvcLinkBuilder.methodOn(this.getClass()).findAll());
        resUser.add(linkTo.withRel("all-users"));
        return resUser;
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
    @GetMapping("/morethanpost/{count}")
    public List<User> findUserWithMoreThanPost(@PathVariable("count") int count){

        return userService.findUserWithMoreThanPost(count);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public void delete(@RequestBody ReqUser user){
        userService.deleteUser(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/byPostTitle")
    public List<ResUser> findByIdWithPostTitle(@RequestParam("byPostTitle") String title){
        return userService.findUserByPostTitle(title);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/filter")
    public List<User> searchReviews(
            @PathVariable("id")Long id,
            @RequestParam(value = "posts", required = false) Long postid,
            @RequestParam(value = "comments",required = false) Long commentid){
        return userService.searchUserCriteria(postid,commentid,id);
    }

    



}
