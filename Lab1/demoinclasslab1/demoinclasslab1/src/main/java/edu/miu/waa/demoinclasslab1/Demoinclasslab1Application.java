package edu.miu.waa.demoinclasslab1;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.ModelMap;

@SpringBootApplication
public class Demoinclasslab1Application {

	public static void main(String[] args) {
		SpringApplication.run(Demoinclasslab1Application.class, args);
	}
	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}
}
