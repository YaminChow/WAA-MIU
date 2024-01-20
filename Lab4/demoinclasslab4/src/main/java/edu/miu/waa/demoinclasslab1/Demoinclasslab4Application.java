package edu.miu.waa.demoinclasslab1;

import edu.miu.waa.demoinclasslab1.entity.Commet;
import edu.miu.waa.demoinclasslab1.entity.Post;
import edu.miu.waa.demoinclasslab1.entity.User;
import edu.miu.waa.demoinclasslab1.repo.PostRepo;
import edu.miu.waa.demoinclasslab1.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Demoinclasslab4Application {

	public static void main(String[] args) {
		SpringApplication.run(Demoinclasslab4Application.class, args);
	}
	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}
	@Bean
	public CommandLineRunner loadData(PostRepo postRepo, UserRepo userRepo) {
		List<Post> posts = new ArrayList<>();
		List<Commet> commets = new ArrayList<>();
		Commet commet1 = new Commet("good");
		Commet commet2 = new Commet("great");
		commets.add(commet1);
		commets.add(commet2);
		Post p1 = new Post("Head To Servlet and JPA", "Servelet & JPA", "O'Reilly",commets);

		posts.add(p1);

		List<Commet> commets1 = new ArrayList<>();
		Commet commet3 = new Commet("nice");
		commets1.add(commet3);
		Post p2 = new Post("Learning React", "Learning React", "O'Reilly",commets1);

		posts.add(p2);

		User user1 = new User("John",posts);
		List<Commet> commets2 = new ArrayList<>();
		List<Post> posts1 = new ArrayList<>();
		Commet commet4 = new Commet("Better");
		commets2.add(commet4);
		Post p3 = new Post( "Head To Servlet and JPA", "Servelet & JPA", "O'Reilly",commets2);

		posts1.add(p3);
		User user2 = new User( "Mira",posts1);

		return args ->{
			userRepo.save(user1);
			userRepo.save(user2);
		};

	}
}
