package edu.miu.waa.demoinclasslab1.service;

import edu.miu.waa.demoinclasslab1.dto.request.ReqUser;
import edu.miu.waa.demoinclasslab1.dto.request.ReqUserDto;
import edu.miu.waa.demoinclasslab1.dto.response.ResUser;
import edu.miu.waa.demoinclasslab1.dto.response.ResUserDto;
import edu.miu.waa.demoinclasslab1.entity.Post;
import edu.miu.waa.demoinclasslab1.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    ResUser findById(long userId);
    User saveUser(ReqUserDto user);
    List<Post> findPostByUserId(long id);
    List<User> findUserWithMoreThanPost(int count);
    void deleteUser(ReqUser user);

    List<ResUser> findUserByPostTitle(String title);
    List<User> searchUserCriteria(Long postid, Long commentid,Long pId);
}
