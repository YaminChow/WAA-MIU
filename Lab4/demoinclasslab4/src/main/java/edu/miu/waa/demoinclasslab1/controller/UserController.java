package edu.miu.waa.demoinclasslab1.controller;

import edu.miu.waa.demoinclasslab1.aspect.LoggerAspect;
import edu.miu.waa.demoinclasslab1.aspect.annotation.ExecutionTime;
import edu.miu.waa.demoinclasslab1.aspect.annotation.LogMe;
import edu.miu.waa.demoinclasslab1.dto.request.ReqUser;
import edu.miu.waa.demoinclasslab1.dto.request.ReqUserDto;
import edu.miu.waa.demoinclasslab1.dto.response.ResUser;
import edu.miu.waa.demoinclasslab1.dto.response.ResUserDto;
import edu.miu.waa.demoinclasslab1.dtos.PostDtos;
import edu.miu.waa.demoinclasslab1.entity.Logger;
import edu.miu.waa.demoinclasslab1.entity.Post;
import edu.miu.waa.demoinclasslab1.entity.User;
import edu.miu.waa.demoinclasslab1.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@Transactional
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    LoggerAspect loggerAspect;
    @LogMe
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<User> findAll(){
        return  userService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @LogMe
    @ExecutionTime
    public ResUser findById(@PathVariable("id") long userId) throws Exception{

        return userService.findById(userId);
    }
    /**
     * Using HATEOAS level 3 method.
     * */

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/h/{id}")
    @LogMe
    public EntityModel<ResUser> findByIdH(@PathVariable("id") long userId) throws Exception{

        EntityModel<ResUser> resUser = EntityModel.of(userService.findById(userId));
        //response all user url
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                .linkTo(
                        WebMvcLinkBuilder.methodOn(this.getClass()).findAll());
        resUser.add(linkTo.withRel("all-users"));
        return resUser;
    }

    @LogMe
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User save(@RequestBody ReqUserDto user){

        return userService.saveUser(user);
    }

    @LogMe
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/posts")
    public List<Post> findByIdWithPost(@PathVariable("id") long userId) throws Exception{

        return userService.findPostByUserId(userId);
    }

    @LogMe
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/morethanpost/{count}")
    public List<User> findUserWithMoreThanPost(@PathVariable("count") int count){

        return userService.findUserWithMoreThanPost(count);
    }

    @LogMe
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public void delete(@RequestBody ReqUser user) throws Exception{
        try {
            userService.deleteUser(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @LogMe
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/byPostTitle")
    public List<ResUser> findByIdWithPostTitle(@RequestParam("byPostTitle") String title){
        Logger log = new Logger(UUID.randomUUID().toString(), LocalDate.now(), LocalTime.now(),"admin","findById");
        //loggerAspect.logMe(log);
        return userService.findUserByPostTitle(title);
    }

    @LogMe
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/filter")
    public List<User> searchReviews(
            @PathVariable("id")Long id,
            @RequestParam(value = "posts", required = false) Long postid,
            @RequestParam(value = "comments",required = false) Long commentid){
        Logger log = new Logger(UUID.randomUUID().toString(), LocalDate.now(), LocalTime.now(),"admin","findById");
        //loggerAspect.logMe(log);
        return userService.searchUserCriteria(postid,commentid,id);
    }
}
