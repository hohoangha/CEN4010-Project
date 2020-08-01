package com.bookstore.BookStoreDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.bookstore.BookstoreDemo.repository")
@SpringBootApplication
public class BookStoreDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreDemoApplication.class, args);
	}

}
