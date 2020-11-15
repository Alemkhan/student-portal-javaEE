package Services;

import DAO.UserDAO;
import Models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("search")
public class SearchService {

    private UserDAO userDAO = new UserDAO();

    @GET
    @Path("/Show_All")
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAll() {

        ArrayList<User> users = userDAO.getAll();
        return Response.status(Response.Status.OK).entity(users).build();

    }

    @GET
    @Path("/Show_Certain")
    @Produces(MediaType.APPLICATION_JSON)
    public Response showCertain(@QueryParam("search_val") String find) {

        ArrayList<User> users = userDAO.getCertain(find);
        return Response.status(Response.Status.OK).entity(users).build();

    }

    @GET
    @Path("/By_Major")
    @Produces(MediaType.APPLICATION_JSON)
    public Response showByMajor(@QueryParam("search_val") String find) {

        ArrayList<User> users = userDAO.getByMajor(find);
        return Response.status(Response.Status.OK).entity(users).build();

    }

    @GET
    @Path("/By_Year")
    @Produces(MediaType.APPLICATION_JSON)
    public Response showByYear(@QueryParam("search_val") String find) {

        ArrayList<User> users = userDAO.getByYear(find);
        return Response.status(Response.Status.OK).entity(users).build();

    }

    @GET
    @Path("/By_Group")
    @Produces(MediaType.APPLICATION_JSON)
    public Response showByGroup(@QueryParam("search_val") String find) {

        ArrayList<User> users = userDAO.getByGroup(find);
        return Response.status(Response.Status.OK).entity(users).build();

    }
}
