package Controllers;

import DAO.ClubDAO;
import DAO.UserDAO;
import Models.Club;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddClubServlet")
public class AddClubServlet extends HttpServlet {
    private final ClubDAO clubDAO = new ClubDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clubName = request.getParameter("name");
        String description = request.getParameter("description");
        User user = (User) request.getSession().getAttribute("user");
        Club club = new Club(clubName, description, null, user);
        try {
            clubDAO.createClub(user.getId(), club);
            response.sendRedirect("index.jsp");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
