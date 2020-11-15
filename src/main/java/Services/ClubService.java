package Services;

import DAO.ClubDAO;
import DAO.EventDAO;
import DAO.NewsDAO;
import Models.Club;
import Models.News;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@Path("clubs")
public class ClubService {

    @Context private HttpServletRequest request;
    @Context private HttpServletResponse response;

    private final ClubDAO clubDAO = new ClubDAO();
    private final NewsDAO newsDAO = new NewsDAO();
    private final EventDAO eventDAO = new EventDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Club getClub(int club_id) throws SQLException {

        Club club = clubDAO.get(club_id);
        ArrayList<News> clubNews = clubDAO.getAllClubNews(club_id);
        return club;

    }

    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<Club, String> getAllClubs() throws SQLException {

        return clubDAO.getAllInfo();

    }

}
