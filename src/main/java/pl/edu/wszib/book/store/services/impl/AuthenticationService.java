package pl.edu.wszib.book.store.services.impl;

import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.dao.IUserDAO;
import pl.edu.wszib.book.store.model.User;
import pl.edu.wszib.book.store.services.IAuthenticationService;
import pl.edu.wszib.book.store.session.SessionObject;

import java.util.Optional;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    IUserDAO userDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public void login(String login, String password) {

        Optional<User> userBox = this.userDAO.getByLogin(login);
        if(userBox.isPresent() && userBox.get().getPassword().equals(DigestUtils.md5Hex(password))) {
            this.sessionObject.setLoggedUser(userBox.get());

        }


    }

    @Override
    public void logout() {
        this.sessionObject.setLoggedUser(null);

    }

    @Override
    public void register(User user) {
        userDAO.persist(user);
    }
}
