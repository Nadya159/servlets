package by.javaguru;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registr")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var writer = resp.getWriter();
        String login = req.getParameter("login");
        String pswd = req.getParameter("pswd");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));
        if (userService.isUserExist(login)) {
            writer.println("<h4>Пользователь с данным логином уже существует!</h4>");
        } else {
            writer.println("<h4>" + userService.addUser(login, pswd, name, email, age) + "</h4>");
        }
        writer.close();
    }
}
