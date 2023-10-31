package by.javaguru;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = (String) req.getSession().getAttribute("loginUser");
        String pswd = req.getParameter("pswd");
        if (pswd.length() < 3){
            ServletContext servletContext = getServletContext();
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/notcorrect");
            dispatcher.forward(req, resp);
        }
        else {
            var writer = resp.getWriter();
            writer.println("<html><body>");
            writer.println("<h3> Изменение пароля: </h3>");
            writer.println("<p style='color:Red'> " + userService.changePswd(login, pswd) + "</p>");
            writer.println("</body></html>");
            writer.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = (String) req.getSession().getAttribute("loginUser");
        var user = userService.getUser(login);
        var writer = resp.getWriter();
        var reader = req.getReader();
        writer.println("<html><body>");
        writer.println("<h3> Мои настройки: </h3>");
        writer.println("<p>Логин: " + user.get().getLogin() + "</p>");
        writer.println("<p>Пароль: " + user.get().getPsd() + "</p>");
        writer.println("<p>Имя пользователя: " + user.get().getName() + "</p>");
        writer.println("<p>E-mail: " + user.get().getEmail() + "</p>");
        writer.println("<p>Возраст: " + user.get().getAge() + "</p>");
        writer.println("<h3>Изменить пароль:</h3>");
        writer.println("<form action=\"user\" method=\"POST\">");
        writer.println("<p>Новый пароль (не менее 3-х символов):</p> <input type=\"text\" name=\"pswd\"> <br/>");
        writer.println("<br><br><input type=\"submit\" value=\"Изменить\"/>");
        writer.println("</form></body></html>");
        writer.close();
    }
}
