package Controllers;

import Services.DashboardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
