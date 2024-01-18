package edu.miu.waa.demoinclasslab1.service.impl;

import edu.miu.waa.demoinclasslab1.dto.request.ReqPostDto;
import edu.miu.waa.demoinclasslab1.dto.request.ReqUserDto;
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
    public User findById(long userId){
        return userRepo.findById(userId).get();
    }

    public User saveUser(ReqUserDto user){
        List<Post> posts = listMapper.mapList(user.getReqPosts(),new Post());
        User user1 = new User();
        user1.setName(user.getName());
        user1.setPosts(posts);
        return userRepo.save(user1);
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
