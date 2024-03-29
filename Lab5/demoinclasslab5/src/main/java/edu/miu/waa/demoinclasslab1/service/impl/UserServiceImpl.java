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
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public ResUser findById(long userId) throws Exception{
        Optional<User> user = userRepo.findById(userId);
        if(user.isPresent()){
            return modelMapper.map(user.get(),ResUser.class) ;
        }else{
            throw new Exception("User "+ userId+" can't find out.");
        }
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
        List<User> users = userRepo.findUserByPostsGreaterThan(count);
        return users;
    }

    public void deleteUser(ReqUser user) throws Exception{
        Optional<User> deleteUser = userRepo.findById(user.getId());

            if(deleteUser.isPresent()){
                userRepo.delete(deleteUser.get());
            }else {
                throw new Exception("User with ID " + user.getId() + " not found");
            }
    }

    public List<ResUser> findUserByPostTitle(String title){
        return listMapper.mapList(userRepo.findUserByPostTitle(title),new ResUserDto());
    }

    @Autowired
    UserSearchDao userSearchDao;
    @Override
    public List<User> searchUserCriteria(Long postid, Long commentid,Long uId) {

        var dtoRequest = new UserCriteriaRequest();
        dtoRequest.setCommentId(commentid);
        dtoRequest.setPostId(postid);
        dtoRequest.setUserId(uId);

        return userSearchDao.findAllByCriteria(dtoRequest);
    }

    public List<User> searchUserCriteria1(Long userId, Long postId,Long commentId ) {
        List<User> users = new ArrayList<>();
        if(postId != null && userId != null && commentId != null){
            users.add(userRepo.findByPostsWithCommentId(userId,postId,commentId));
            return users;
        }
        if(postId != null && userId != null && commentId == null){
            users.add(userRepo.findByPostId(userId,postId));
            return users;
        }
        if(userId != null && commentId == null && postId == null){
            users.add(userRepo.findById(userId).get());
            return users;
        }
        return users;
    }
}
