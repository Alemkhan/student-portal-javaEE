package Controllers;

import Models.User;
import Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    private final UserService us = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userEmail = request.getParameter("email");
        String userPassword = request.getParameter("password");

        try {
            User user = us.login(userEmail, userPassword);
            if (user == null) {
                request.setAttribute("messageResponse","Login failed: wrong login credentials");
                request.getRequestDispatcher("/").forward(request,response);
            } else {
                request.setAttribute("messageResponse",null);
                HttpSession userSession = request.getSession();
                userSession.setAttribute("user", user);
                userSession.setMaxInactiveInterval(60*15);
                int i = (int) (new Date().getTime()/1000);
                String time = Integer.toString(i);
                Cookie cookie = new Cookie("time",time);
                response.addCookie(cookie);
                response.sendRedirect("/dashboard");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
