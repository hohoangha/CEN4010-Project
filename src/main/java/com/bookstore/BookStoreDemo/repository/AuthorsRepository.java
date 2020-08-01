
package com.bookstore.BookStoreDemo.repository;

import com.bookstore.BookStoreDemo.model.AuthorID;
import com.bookstore.BookStoreDemo.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsRepository extends JpaRepository<Authors, AuthorID>{
    
}
