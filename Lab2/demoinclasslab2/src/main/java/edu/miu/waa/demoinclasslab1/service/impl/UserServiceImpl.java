package edu.miu.waa.demoinclasslab1.service.impl;

import edu.miu.waa.demoinclasslab1.dto.request.ReqPostDto;
import edu.miu.waa.demoinclasslab1.dto.request.ReqUserDto;
import edu.miu.waa.demoinclasslab1.dto.response.ResUser;
import edu.miu.waa.demoinclasslab1.entity.Post;
import edu.miu.waa.demoinclasslab1.entity.User;
import edu.miu.waa.demoinclasslab1.help.ListMapper;
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
    @Autowired
    ListMapper listMapper;

    public List<User> findAll(){
        return userRepo.findAll();
    }
    public ResUser findById(long userId){

        return modelMapper.map(userRepo.findById(userId).get(),ResUser.class) ;
    }

    public User saveUser(ReqUserDto user){
        User users = modelMapper.map(user, User.class);
        users.setPosts(listMapper.mapList(user.getReqPosts(),new Post()));
        return userRepo.save(users);
    }
    public List<Post> findPostByUserId(long id){
        try{
            User user = userRepo.findById(id).get();

            if(user != null){
                List<Post> posts = listMapper.mapList(user.getPosts(), new Post());
                return posts;
            }else{
                throw new RuntimeException("This is no post.");
            }

        }catch(Exception e){
            throw new RuntimeException("This is no post.");
        }
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
