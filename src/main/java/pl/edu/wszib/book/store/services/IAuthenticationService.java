package pl.edu.wszib.book.store.services;

import pl.edu.wszib.book.store.model.User;

public interface IAuthenticationService {

    void login (String login, String password);

    void logout();

    void register(User user);

}
