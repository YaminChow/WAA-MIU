package edu.miu.waa.demoinclasslab1.service.impl;

import edu.miu.waa.demoinclasslab1.dto.request.ReqPostDto;
import edu.miu.waa.demoinclasslab1.dto.request.ReqUserDto;
import edu.miu.waa.demoinclasslab1.entity.Post;
import edu.miu.waa.demoinclasslab1.entity.User;
import edu.miu.waa.demoinclasslab1.repo.PostRepo;
import edu.miu.waa.demoinclasslab1.repo.UserRepo;
import edu.miu.waa.demoinclasslab1.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PostRepo postRepo;
    @Autowired
    ModelMapper modelMapper;

    public List<User> findAll(){
        return userRepo.findAll();
    }
    public User findById(long userId){
        return userRepo.findById(userId).get();
    }

    public User saveUser(ReqUserDto user){
        List<Post> posts = new ArrayList<>();
        for(ReqPostDto post : user.getReqPosts()){
            posts.add(modelMapper.map(post, Post.class));
        }
        User user1 = new User();
        user1.setName(user.getName());
        user1.setPosts(posts);
        return userRepo.save(user1);
    }
    public List<Post> findPostByUserId(long id){
        User user = userRepo.findById(id).get();
        List<Post> posts = new ArrayList<>();
        if(user != null){
            for (Post p: user.getPosts()){
                posts.add(p);
            }
            return posts;
        }
        return null;
    }
    public List<User> findUserWithMoreThanOnePost(){
        List<User> users = userRepo.findAll();
        List<User> resUsers = new ArrayList<>();
        if(users != null){
            for(User u: users){
                if(u.getPosts().size()>1){
                    resUsers.add(u);
                }
            }
           return resUsers;
        }
        return null;
    }



}
