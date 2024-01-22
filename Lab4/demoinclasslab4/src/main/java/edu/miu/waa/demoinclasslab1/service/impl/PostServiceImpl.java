package edu.miu.waa.demoinclasslab1.service.impl;

import edu.miu.waa.demoinclasslab1.dto.request.ReqUpdatePost;
import edu.miu.waa.demoinclasslab1.dtos.PostDtos;
import edu.miu.waa.demoinclasslab1.entity.Post;
import edu.miu.waa.demoinclasslab1.help.ListMapper;
import edu.miu.waa.demoinclasslab1.repo.PostRepo;
import edu.miu.waa.demoinclasslab1.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;


@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepo postRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ListMapper listMapper;
    @Override
    public List<PostDtos> findAll() {
        return (List<PostDtos>)  listMapper.mapList(postRepo.findAll(),new PostDtos()) ;
    }

    @Override
    public Post findById(long id) {
        return postRepo.findById(id).get();
    }

    @Override
    public Post save(Post post) {
        return postRepo.save(post);
    }

    @Override
    public void delete(long id) {
        postRepo.deleteById(id);
    }

    @Override
    public Post update(long id, Post post) {

        return postRepo.save(post);
    }

    @Override
    public List<PostDtos> findByAuthor(String author) {
        return (List<PostDtos>) listMapper.mapList(postRepo.findPostByAuthor(author),new PostDtos());
    }

    @Override
    public List<PostDtos> findByAuthorName(String authorName) {
        return (List<PostDtos>) listMapper.mapList(postRepo.findPostsByAuthorContains(authorName),new PostDtos());
    }

    @Override
    public List<PostDtos> findByTitle(String title){
        return (List<PostDtos>) listMapper.mapList(postRepo.findPostByTitle(title),new PostDtos());
    }


}
