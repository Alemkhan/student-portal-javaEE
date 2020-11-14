package Services;

import DAO.ClubDAO;
import DAO.EventDAO;
import DAO.NewsDAO;
import Models.Club;
import Models.Event;
import Models.News;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DashboardService {

    @Context private HttpServletRequest request;
    @Context private HttpServletResponse response;

    private final ClubDAO clubDAO = new ClubDAO();
    private final NewsDAO newsDAO = new NewsDAO();
    private final EventDAO eventDAO = new EventDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<String, ArrayList<?>> showContent() throws SQLException {
        HashMap<String, ArrayList<?>> content = new HashMap<>();
        ArrayList<Club> allClubs = clubDAO.getAll();
        ArrayList<Event> allEvents = eventDAO.getAll();
        ArrayList<News> allNews = newsDAO.getAll();
        Collections.sort(allEvents);
        Collections.sort(allNews);
        content.put("clubs", allClubs);
        content.put("events", allEvents);
        content.put("news", allNews);
        return content;
    }

}
