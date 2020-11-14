package Services;

import DAO.UserDAO;
import Models.Admin;
import Models.Student;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.sql.SQLException;

@Path("userService")
public class UserService {

    @Context
    private HttpServletRequest request;

    private final UserDAO userDAO = new UserDAO();

    @POST
    @Path("/login")
    public User login(String email,String password) throws SQLException, ServletException, IOException {
        return userDAO.getForLogin(email, password);
    }

}
