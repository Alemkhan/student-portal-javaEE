package Services;

import DAO.UserDAO;
import Models.Club;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@Path("userService")
public class UserService {

    @Context
    private HttpServletRequest request;

    private final UserDAO userDAO = new UserDAO();

    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_HTML)
    public User login(String email,String password) throws SQLException, ServletException, IOException {
        return userDAO.getForLogin(email, password);
    }

    @GET
    @Path("/getUser/{user_id}")
    public User getUser(int user_id) throws SQLException{
        return null;
    }

    @GET
    @Path("/checkUserClubs/{user_id}")
    public HashMap<Club, String > getStudentClubs(int user_id) throws SQLException {
        return userDAO.getStudentClubs(user_id);
    }

}
