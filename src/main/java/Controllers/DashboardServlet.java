package Controllers;

import Models.Club;
import Models.Event;
import Models.News;
import Services.DashboardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "DashboardServlet", value="/dashboard")
public class DashboardServlet extends HttpServlet {

    private DashboardService ds = new DashboardService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            HashMap<String, ArrayList<?>> content = ds.showContent();

            ArrayList<Club> clubs = (ArrayList<Club>) content.get("clubs");
            ArrayList<Event> events = (ArrayList<Event>) content.get("events");
            ArrayList<News> news = (ArrayList<News>) content.get("news");
            request.setAttribute("clubs", clubs);
            request.setAttribute("events", events);
            request.setAttribute("news", news);

            request.getRequestDispatcher("index.jsp").forward(request,response);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
