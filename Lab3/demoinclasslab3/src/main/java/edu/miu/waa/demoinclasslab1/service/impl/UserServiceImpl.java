package edu.miu.waa.demoinclasslab1.service.impl;

import edu.miu.waa.demoinclasslab1.dto.input.UserCriteriaRequest;
import edu.miu.waa.demoinclasslab1.dto.request.ReqPostDto;
import edu.miu.waa.demoinclasslab1.dto.request.ReqUser;
import edu.miu.waa.demoinclasslab1.dto.request.ReqUserDto;
import edu.miu.waa.demoinclasslab1.dto.response.ResUser;
import edu.miu.waa.demoinclasslab1.dto.response.ResUserDto;
import edu.miu.waa.demoinclasslab1.entity.Post;
import edu.miu.waa.demoinclasslab1.entity.User;
import edu.miu.waa.demoinclasslab1.help.ListMapper;
import edu.miu.waa.demoinclasslab1.repo.CommetRepo;
import edu.miu.waa.demoinclasslab1.repo.PostRepo;
import edu.miu.waa.demoinclasslab1.repo.UserRepo;
import edu.miu.waa.demoinclasslab1.repo.UserSearchDao;
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
        return userRepo.save(modelMapper.map(user, User.class));
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
    public List<User> findUserWithMoreThanPost(int count){
        List<User> users = userRepo.findAll();
        List<User> resUsers = new ArrayList<>();
        if(users != null){
            for(User u: users){
                if(u.getPosts().size()>count){
                    resUsers.add(u);
                }
            }
           return resUsers;
        }
        return null;
    }

    public void deleteUser(ReqUser user){
        userRepo.delete(userRepo.findById(user.getId()).get());
    }

    public List<ResUser> findUserByPostTitle(String title){
        return listMapper.mapList(userRepo.findUserByPostTitle(title),new ResUserDto());
    }

    @Autowired
    UserSearchDao userSearchDao;
    @Override
    public List<User> searchUserCriteria(Long postid, Long commentid,Long uId) {
        System.out.println("post:>>"+ postid);
        System.out.println("comment:>> "+commentid);
        System.out.println("User: >>" + uId);
        System.out.println("==========");
        var dtoRequest = new UserCriteriaRequest();
        dtoRequest.setCommentId(commentid);
        dtoRequest.setPostId(postid);
        dtoRequest.setUserId(uId);
        System.out.println("DTO>>>>>");
        System.out.println("post:>>"+ dtoRequest.getPostId());
        System.out.println("comment:>> "+dtoRequest.getCommentId());
        System.out.println("User: >>" + dtoRequest.getUserId());
        return userSearchDao.findAllByCriteria(dtoRequest);

//        return reviewSearchDao.findAllBySimpleQuery(comment,stars);
    }
}
