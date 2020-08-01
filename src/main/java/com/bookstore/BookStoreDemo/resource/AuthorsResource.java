package com.bookstore.BookStoreDemo.resource;

import com.bookstore.BookStoreDemo.model.AuthorID;
import com.bookstore.BookStoreDemo.model.Authors;
import com.bookstore.BookStoreDemo.model.Books;
import com.bookstore.BookStoreDemo.repository.AuthorsRepository;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/geektext/authors")
public class AuthorsResource {
    
    @Autowired
    AuthorsRepository authorsrepo;
    
    @GetMapping(value = "/all")
    public List<Authors> getAll() {
        return authorsrepo.findAll();
    }
    
    @GetMapping(value = "/find-their-books")
    public List<Books> getAll(@RequestBody final Authors author) throws NotFoundException {
       AuthorID name = new AuthorID(author.getFirstName(), author.getLastName());
       Optional<Authors> result = authorsrepo.findById(name);
       if (result.isEmpty()) {
           throw new NotFoundException("This author does not exist!");
       }
       return result.get().getBooks();
    }
    
    @PostMapping(value = "/create")
    public Authors add(@RequestBody final Authors author) {
       author.setFirstName(author.getFirstName());
       author.setLastName(author.getLastName());
       return authorsrepo.save(author);
    }
    
    @PutMapping(value = "/update")
    public String update(@RequestBody final Authors author) {
        author.setFirstName(author.getFirstName());
        author.setLastName(author.getLastName());
        authorsrepo.save(author);
        return "Author " + author.getFirstName() + " " + author.getLastName() + " is updated!";
    }
    
    @DeleteMapping(value = "/delete")
    public String delete(@RequestBody final Authors author) throws NotFoundException {
       AuthorID name = new AuthorID(author.getFirstName(), author.getLastName());
       Optional<Authors> result = authorsrepo.findById(name);
       if (result.isEmpty()) {
           throw new NotFoundException("This author does not exist!");
       }
       authorsrepo.delete(result.get());
       return "Author " + author.getFirstName() + " " + author.getLastName() + " is deleted!";
    }
}
