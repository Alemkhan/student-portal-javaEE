package Controllers;

import Models.Admin;
import Models.Club;
import Models.Student;
import Models.User;
import Services.ClubService;
import Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private final UserService us = new UserService();
    private final ClubService cs = new ClubService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int param = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getRole().getRole_name().equals("admin")) {

            try {
                ((Admin) user) .setAllClubs(cs.getAllClubs());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else {

            try {
                ((Student) user).setClubs(us.getStudentClubs(param));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        session.setAttribute("user", user);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
}
