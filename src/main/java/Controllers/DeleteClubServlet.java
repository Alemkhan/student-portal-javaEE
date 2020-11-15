package Controllers;

import DAO.ClubDAO;
import Models.Club;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "DeleteClubServlet")
public class DeleteClubServlet extends HttpServlet {
    private final ClubDAO clubDAO = new ClubDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            if (action != null) {
                switch (action) {
                    case "/deleteClub":
                        deleteForm(request, response);
                        break;
                    case "/confirmDelete":
                        removeClub(request, response);
                        break;
                }
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void deleteForm(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
        ArrayList<Club> clubs = clubDAO.getAll();
        request.setAttribute("clubsForDelete", clubs);
        request.getRequestDispatcher("deleteForm.jsp").forward(request,response);
    }

    private void removeClub(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
        int clubId = Integer.parseInt(request.getParameter("club_id"));
        clubDAO.deleteClub(clubId);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}
