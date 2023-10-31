package by.javaguru;

import by.javaguru.dao.User;
import by.javaguru.dao.UserDao;

import java.util.Optional;

public class UserService {
    private final UserDao userDao = new UserDao();

    public boolean isUserExist(String login) {
        return userDao.isFoundUser(login);
    }

    public boolean isDataCorrect(String login, String pswd) {
        return userDao.isDataCorrect(login, pswd);
    }

    public String addUser(String login, String pswd, String name, String email, int age) {
        if (userDao.isFoundUser(login))
            return "Пользователь с данным логином уже существует!";
        else
            userDao.addUser(login, pswd, name, email, age);
        return "Пользователь успешно зарегистрирован!";
    }

    public Optional<User> getUser(String login) {
        return userDao.getUser(login);
    }

    public String changePswd(String login, String pswd) {
        if (userDao.isFoundUser(login)) {
            userDao.setPswd(login, pswd);
            return "Пароль успешно изменен!";
        } else
            return "Пользователь с таким логином не найден!";
    }
}

