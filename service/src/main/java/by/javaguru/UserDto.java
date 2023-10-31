package by.javaguru;

public class UserDto {
    private String name, email, login, psd;
    private int age;

    public UserDto(String name, String email, String login, String psd, int age) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.psd = psd;
        this.age = age;
    }
}
