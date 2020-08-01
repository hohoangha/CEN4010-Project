package com.bookstore.BookStoreDemo.resource;

import com.bookstore.BookStoreDemo.model.AuthorID;
import com.bookstore.BookStoreDemo.model.Authors;
import com.bookstore.BookStoreDemo.model.Books;
import com.bookstore.BookStoreDemo.repository.AuthorsRepository;
import com.bookstore.BookStoreDemo.repository.BooksRepository;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/geektext/books")
public class BooksResource {
    
    @Autowired
    BooksRepository booksrepo;
    
    @Autowired
    AuthorsRepository authorsrepo;
    
    @GetMapping("/all")
    public List<Books> getAll() {
        return booksrepo.findAll();
    }
    
    @GetMapping("/{isbn}")
    public Books getById(@PathVariable Long isbn) throws NotFoundException {
        Optional<Books> result = booksrepo.findById(isbn);
        if (result.isEmpty()) {
            throw new NotFoundException("Book with ISBN " + isbn + " is not found!");
        }
        return result.get();
    }

    @PostMapping(value = "/create")
    public Books add(@RequestBody final Books book) throws NotFoundException {
        Authors author = book.getAuthor();
        AuthorID name = new AuthorID(author.getFirstName(), author.getLastName());
        Optional<Authors> result = authorsrepo.findById(name);
        if (result.isEmpty()) {
            throw new NotFoundException("Author of this book has not been created. Create author first, then this book!");
        }
        result.get().getBooks().add(book);
        book.setAuthor(result.get());
        authorsrepo.save(result.get());
        return booksrepo.save(book);
    }
    
    @PutMapping(value = "/update")
    public String update(@RequestBody final Books book) {
        booksrepo.save(book);
        return "Book with ISBN " + book.getIsbn() + " is updated!";
    }
    
    @DeleteMapping(value = "/delete/{isbn}")
    public String delete(@PathVariable Long isbn) throws NotFoundException {
        Optional<Books> result = booksrepo.findById(isbn);
        if(result.isEmpty()) {
            throw new NotFoundException("Book with ISBN " + isbn + " does not exist!");
        }
        booksrepo.delete(result.get());
        return "Book with ISBN " + isbn + " is deleted!";
    }
}
