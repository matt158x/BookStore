package pl.edu.wszib.book.store.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.edu.wszib.book.store.services.ICartService;
import pl.edu.wszib.book.store.session.SessionObject;



@Controller
public class CartController {

    @Autowired
    ICartService cartService;



    @Resource
    SessionObject sessionObject;

    @RequestMapping(path = "/book/add/{bookId}", method = RequestMethod.GET)
    public String addBook(@PathVariable final int bookId) {
        this.cartService.addBookToCart(bookId);


        return "redirect:/main";

    }

    @RequestMapping(path = "/cart", method = RequestMethod.GET)
    public String cart(Model model) {
        model.addAttribute("cart", this.sessionObject.getCart());
        model.addAttribute("isLogged", this.sessionObject.isLogged());

        return "cart";

    }

    @RequestMapping(path = "/order", method = RequestMethod.GET)
    public String order(){

        if (this.cartService.order()) {
            return "redirect:/main";
        }
            return "redirect:/cart";
    }
}

