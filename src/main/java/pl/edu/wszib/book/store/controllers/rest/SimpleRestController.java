package pl.edu.wszib.book.store.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.model.Book;

import java.util.Optional;

@RestController
public class SimpleRestController {

    private final IBookDAO bookDAO;

    public SimpleRestController(IBookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @RequestMapping(path = "/test1/{id}/{text}", method = RequestMethod.GET)
                public void test1(@PathVariable int id,
                                  @PathVariable String text,
                                  @RequestParam int param1,
                                  @RequestParam String param2,
                                  @RequestHeader("header1") String h1,
                                  @RequestHeader String header2) {

            System.out.println(id);
            System.out.println(text);
            System.out.println(param1);
            System.out.println(param2);
            System.out.println(h1);
            System.out.println(header2);

    }

    @RequestMapping(path = "test2/{id}", method = RequestMethod.GET)
    public Book test2(@PathVariable int id) {
        Optional<Book> book = this.bookDAO.getById(id);
        if (book.isPresent()) {
            return book.get();
        } return null;
    }

    @RequestMapping(path = "test3", method = RequestMethod.POST)
    public void test3(@RequestBody Book book){
        System.out.println(book);
    }

    @RequestMapping(path = "test4", method = RequestMethod.GET)
    public ResponseEntity<Book> test4() {
        Optional<Book> book = this.bookDAO.getById(1);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .header("h1", "v1")
                .header("h2","v2")
                .body(book.get());

    }

}
