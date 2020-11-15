package Controllers;

import Models.Club;
import Services.ClubService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/clubs")
public class ClubServlet extends HttpServlet {

    private final ClubService cs = new ClubService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            Club clubBody = cs.getClub(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("club", clubBody);
            request.getRequestDispatcher("club.jsp").forward(request,response);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
