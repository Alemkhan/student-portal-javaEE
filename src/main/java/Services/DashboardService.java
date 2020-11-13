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

@Path("dashboard")
public class DashboardService {

    @Context private HttpServletRequest request;
    @Context private HttpServletResponse response;

    private final ClubDAO clubDAO = new ClubDAO();
    private final NewsDAO newsDAO = new NewsDAO();
    private final EventDAO eventDAO = new EventDAO();

    @GET
    @Path("/showAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response showContent() throws SQLException {
        ArrayList<Club> allClubs = clubDAO.getAll();
        return Response.status(Response.Status.OK).entity(allClubs).build();
    }
}
