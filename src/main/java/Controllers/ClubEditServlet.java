package Controllers;

import DAO.ClubDAO;
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

@WebServlet(value = "/clubEdit")

public class ClubEditServlet extends HttpServlet {
    private final ClubService cs = new ClubService();
    private final EventDAO eventDAO = new EventDAO();
    private final NewsDAO newsDAO = new NewsDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/editClub":
                    list(request, response);
                    break;
                case "/deleteEvent":
                    deleteEvent(request, response);
                    break;
                case "/editEvent":
                    editEvent(request, response);
                    break;
                case "/editNews":
                    editNews(request, response);
                    break;
                case "/deleteNews":
                    deleteNew(request, response);
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
        request.setAttribute("clubForEdit", clubBody);
        request.getRequestDispatcher("clubEdit.jsp?club_id=" + club_id).forward(request,response);
    }

    private void deleteEvent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int club_id = Integer.parseInt(request.getParameter("club_id"));
        int event_id = Integer.parseInt(request.getParameter("event_id"));
        eventDAO.deleteEvent(event_id, club_id);
        request.getRequestDispatcher("editClub?club_id=" + club_id).forward(request,response);
    }

    private void editEvent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int club_id = Integer.parseInt(request.getParameter("club_id"));
        int event_id = Integer.parseInt(request.getParameter("event_id"));
        String event_title = request.getParameter("newTitle");
        String event_description = request.getParameter("newDescription");
        Date localDate = Date.valueOf(LocalDate.now());
        Club club = new Club(club_id);
        Event newEvent = new Event(event_id, event_title, event_description, localDate, club);
        eventDAO.editEvent(club_id, newEvent);
        request.getRequestDispatcher("editClub?club_id=" + club_id).forward(request,response);
    }

    private void deleteNew(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int club_id = Integer.parseInt(request.getParameter("club_id"));
        int news_id = Integer.parseInt(request.getParameter("news_id"));
        newsDAO.deleteNews(news_id, club_id);
        request.getRequestDispatcher("editClub?club_id=" + club_id).forward(request,response);
    }

    private void editNews(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int club_id = Integer.parseInt(request.getParameter("club_id"));
        int news_id = Integer.parseInt(request.getParameter("news_id"));
        String newsTitle = request.getParameter("title");
        Date date = Date.valueOf(LocalDate.now());
        String description = request.getParameter("description");
        Club club = new Club(club_id);
        News news = new News(news_id, newsTitle, description, date, club);
        newsDAO.changeNews(club,news);
        request.getRequestDispatcher("editClub?club_id=" + club_id).forward(request,response);
    }
}
