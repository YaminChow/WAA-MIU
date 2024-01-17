package edu.miu.waa.demoinclasslab1;

import edu.miu.waa.demoinclasslab1.entity.Post;
import edu.miu.waa.demoinclasslab1.entity.User;
import edu.miu.waa.demoinclasslab1.repo.PostRepo;
import edu.miu.waa.demoinclasslab1.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Demoinclasslab1Application {

	public static void main(String[] args) {
		SpringApplication.run(Demoinclasslab1Application.class, args);
	}
	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}
	@Bean
	public CommandLineRunner loadData(PostRepo postRepo, UserRepo userRepo) {
		List<Post> posts = new ArrayList<>();
		Post p1 = new Post("Head To Servlet and JPA", "Servelet & JPA", "O'Reilly");
		Post p2 = new Post("Learning React", "Learning React", "O'Reilly");
		posts.add(p1);
		posts.add(p2);
		User user1 = new User("John",posts);

		List<Post> posts1 = new ArrayList<>();
		Post p3 = new Post( "Head To Servlet and JPA", "Servelet & JPA", "O'Reilly");
		posts1.add(p3);
		User user2 = new User( "Mira",posts1);

		return args ->{
			
			userRepo.save(user1);
			userRepo.save(user2);
		};

	}
}
