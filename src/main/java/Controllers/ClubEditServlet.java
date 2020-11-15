//package Controllers;
//
//import DAO.ClubDAO;
//import Models.Club;
//import Services.ClubService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.SQLException;
//
//@WebServlet("/clubEdit")
//public class ClubEditServlet extends HttpServlet {
//    private final ClubService cs = new ClubService();
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request,response);
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getServletPath();
//        try {
//            switch (action) {
//                case "/editClub":
//                    list(request, response);
//                    break;
//                case "/deleteEvent":
//                    deleteEvent(request, response);
//                    break;
//                case "/editEvent":
//                    edutEvent(request, response);
//                    break;
//                case "/editNews":
//                    editNews(request, response);
//                    break;
//                case "/deleteNews":
//                    deleteNews(request, response);
//                    break;
//                default:
//                    list(request, response);
//                    break;
//            }
//        } catch (SQLException ex) {
//            throw new ServletException(ex);
//        }
//    }
//
//    private void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
//        int club_id = Integer.parseInt(request.getParameter("club_id"));
//        Club clubBody = cs.getClub(club_id);
//        request.setAttribute("clubForEdit", clubBody);
//        request.getRequestDispatcher("clubEdit.jsp?club_id=" + club_id).forward(request,response);
//    }
//
//    private void deleteEvent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
//        int club_id = Integer.parseInt(request.getParameter("club_id"));
//        int event_id = Integer.parseInt(request.getParameter("event_id"));
//
//
//    }
//}

//qwkmqowegpfw
