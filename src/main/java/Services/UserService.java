package Services;

import DAO.UserDAO;
import Models.Admin;
import Models.Student;
import Models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.sql.SQLException;

@Path("userService")
public class UserService {

    @Context
    private HttpServletRequest request;

    private UserDAO userDAO = new UserDAO();

    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_HTML)
    public Response login(@FormParam("email") String email, @FormParam("password") String password) throws SQLException {
        User logginedUser = userDAO.getForLogin(email, password);
        if (logginedUser == null) {
            return Response.status(400).entity("Wrong email or password").build();
        } else if (logginedUser instanceof Admin) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(5*60);
            session.setAttribute("admin", logginedUser);
        } else if (logginedUser instanceof Student) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(5*60);
            session.setAttribute("student", logginedUser);
        }
        return Response.seeOther(URI.create("/dashboard")).build();
    }

}
