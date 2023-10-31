package by.javaguru.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserDao {
    private static Map<String, User> userDao = new HashMap<>();

    public UserDao() {
        User belka = new User("Belka", "belka@gmail.com", "belka123", "java123", 18);
        userDao.put(belka.getLogin(), belka);
        User strelka = new User("Strelka", "strelka@gmail.com", "strelka123", "java123", 19);
        userDao.put(strelka.getLogin(), strelka);
    }

    public Optional<User> getUser(String login) {
        return Optional.of(userDao.get(login));
    }

    public boolean isFoundUser(String login) {
        return (userDao.containsKey(login));
    }

    public boolean isDataCorrect(String login, String pswd){
        return (userDao.get(login).getPsd().equals(pswd));
    }

    public void addUser(String login, String pswd, String name, String email, int age) {
        userDao.put(login, new User(login, pswd, name, email, age));
    }
    public void setPswd(String login, String pswd){
        userDao.get(login).setPsd(pswd);
    }
}
