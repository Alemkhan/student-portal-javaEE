package Services;

import DAO.UserDAO;
import Models.Admin;
import Models.Student;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

@Path("userService")
public class UserService {

    @Context
    private HttpServletRequest request;

    private final UserDAO userDAO = new UserDAO();

    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_HTML)
    public Response login(@FormParam("email") String email, @FormParam("password") String password) throws SQLException, ServletException, IOException {
        User logginedUser = userDAO.getForLogin(email, password);
        if (logginedUser == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Wrong email or password").build();
        } else {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(5*60);
            session.setAttribute("user", logginedUser);
        }
        return Response.seeOther(URI.create("dashboard")).build();
    }

}
