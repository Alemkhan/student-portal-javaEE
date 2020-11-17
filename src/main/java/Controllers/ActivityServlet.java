package Controllers;

import DAO.EventDAO;
import DAO.NewsDAO;
import Models.Club;
import Models.Event;
import Models.News;
import Services.ClubService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "ActivityServlet")
public class ActivityServlet extends HttpServlet {
    private final ClubService cs = new ClubService();
    private final NewsDAO newsDAO = new NewsDAO();
    private final EventDAO eventDAO = new EventDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/activity":
                    list(request, response);
                    break;
                case "/addActivity":
                    addActivity(request, response);
                    break;
                default:
                    list(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int club_id = Integer.parseInt(request.getParameter("club_id"));
        Club clubBody = cs.getClub(club_id);
        request.setAttribute("clubForAdd", clubBody);
        request.getRequestDispatcher("activity.jsp?club_id=" + club_id).forward(request,response);
    }

    private void addActivity(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int club_id = Integer.parseInt(request.getParameter("club_id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Club club = new Club(club_id);
        Date localDate = Date.valueOf(LocalDate.now());
        String activity = request.getParameter("activity");
        if (activity.equals("news")) {
            News news = new News(title, description, localDate, club);
            newsDAO.insertNews(club_id, news);
        }
        else {
            Event newEvent = new Event(title, description, localDate, club);
            eventDAO.addEvent(newEvent, club_id);
        }
        Club clubA = cs.getClub(club_id);
        request.setAttribute("clubForAdd", clubA);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int club_id = Integer.parseInt(request.getParameter("club_id"));
        Club clubBody = null;
        try {
            clubBody = cs.getClub(club_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("clubForAdd", clubBody);
        request.getRequestDispatcher("activity.jsp?club_id=" + club_id).forward(request,response);
    }
}
