package by.javaguru;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/hello")
public class AuthenticationServlet extends HttpServlet {
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
        if (login.isBlank() || pswd.isBlank() || !userService.isUserExist(login))    //проверка на наличие User
        {
            sendToPath("/notfound", req, resp);
        } else if (userService.isDataCorrect(login, pswd)) {                         //проверка на верный логин, пароль
            writer.println("<p>Добро пожаловать: " + login + "</p>");
            HttpSession session = req.getSession(true);
            session.setAttribute("loginUser", login);
            sendToPath("/menu.html", req, resp);
        } else {
            sendToPath("/notcorrect", req, resp);
        }
        writer.close();
    }

    protected void sendToPath(String path, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher(path);
        dispatcher.forward(req, resp);
    }
}
