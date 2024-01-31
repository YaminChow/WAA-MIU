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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.CollectionUtils.arrayToList;

@SpringBootApplication
public class Demoinclasslab4Application {

	public static void main(String[] args) {

		SpringApplication.run(Demoinclasslab4Application.class, args);
		System.out.println("From front end!");

	}

//	public ListNode addTwoNumbers() {
//		int [] arr2 = {5,6,4};
//		ListNode l2 = (ListNode) arrayToList(arr2);
//
//		int [] arr1 ={2,4,3};
//		ListNode l1 = (ListNode) arrayToList(arr1);
//
//		ListNode listNode = new ListNode(0);
//		ListNode returnNode = listNode;
//		int val = 0;
//
//		while (l1 != null || l2 != null)
//		{
//			int val1 = (l1 != null) ? l1.val : 0;
//			int val2 = (l2 != null) ? l2.val : 0;
//
//			int total = val1 + val2 + val;
//			val = total/10;
//
//			returnNode.next = new ListNode(total % 10);
//			returnNode = returnNode.next;
//
//			if(l1 != null) l1 = l1.next;
//			if(l2 != null) l2 = l2.next;
//		}
//		if(val > 0){
//			returnNode.next = new ListNode(val);
//		}
//		System.out.println(listNode);
//		System.out.println(listNode.next);
//		return listNode.next;
//	}

@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}
	@Bean
	public CommandLineRunner loadData( UserRepo userRepo) {
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

	class ListNode {
		static int val;
		ListNode next;

		ListNode(int val) {
			this.val = val;
		}
	}
}
