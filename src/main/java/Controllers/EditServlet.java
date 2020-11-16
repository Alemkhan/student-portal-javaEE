package Controllers;

import DAO.EventDAO;
import DAO.NewsDAO;
import Models.Club;
import Models.Event;
import Models.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "EditServlet")
public class EditServlet extends HttpServlet {
    private final EventDAO eventDAO = new EventDAO();
    private final NewsDAO newsDAO = new NewsDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            if (action != null) {
                switch (action) {
                    case "/editEvent":
                        editEvent(request, response);
                        break;
                    case "/editNews":
                        editNews(request, response);
                        break;
                    case "/updateEvent":
                        updateEvent(request, response);
                        break;
                    case "/updateNews":
                        updateNews(request, response);
                        break;
                }
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void editEvent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int event_id = Integer.parseInt(request.getParameter("event_id"));
        Event event = eventDAO.get(event_id);
        request.setAttribute("eventToChange", event);
        request.getRequestDispatcher("editEventForm.jsp").forward(request,response);
    }

    private void editNews(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int news_id = Integer.parseInt(request.getParameter("news_id"));
        News news = newsDAO.get(news_id);
        request.setAttribute("newsToChange", news);
        request.getRequestDispatcher("editNewsForm.jsp").forward(request,response);
    }

    private void updateEvent(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
        int club_id = Integer.parseInt(request.getParameter("club_id"));
        int event_id = Integer.parseInt(request.getParameter("event_id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Date date = Date.valueOf(LocalDate.now());
        Club club = new Club(club_id);
        Event event = new Event(event_id,title,description, date, club);
        eventDAO.editEvent(club_id, event);
        Event newEvent = eventDAO.get(event_id);
        request.setAttribute("eventToChange", newEvent);
        response.sendRedirect("/clubEdit?club_id=" + club_id);
    }

    private void updateNews(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
        int club_id = Integer.parseInt(request.getParameter("club_id"));
        int news_id = Integer.parseInt(request.getParameter("news_id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Date date = Date.valueOf(LocalDate.now());
        Club club = new Club(club_id);
        News news = new News(news_id,title,description, date, club);
        newsDAO.changeNews(club, news);
        News news1 = newsDAO.get(news_id);
        request.setAttribute("newsToChange", news1);
        response.sendRedirect("/clubEdit?club_id=" + club_id);
    }

}
