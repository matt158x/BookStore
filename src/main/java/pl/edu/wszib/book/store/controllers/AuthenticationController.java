package pl.edu.wszib.book.store.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.book.store.model.User;
import pl.edu.wszib.book.store.services.IAuthenticationService;
import pl.edu.wszib.book.store.session.SessionObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class AuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(path ="/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("userModel", new User());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        return "login";
    }


    @RequestMapping (path = "/login",method = RequestMethod.POST)
    public String login(@ModelAttribute User user) {
        this.authenticationService.login(user.getLogin(),user.getPassword());
        if(this.sessionObject.isLogged()){
            return "redirect:/main";
        }

        return "redirect:/login";

    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.authenticationService.logout();
        return "redirect:/main";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user) {
        String hashedPassword = hashWithMD5(user.getPassword());
        user.setPassword(hashedPassword);
        this.authenticationService.register(user);
        return "redirect:/login";
    }

    private String hashWithMD5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}