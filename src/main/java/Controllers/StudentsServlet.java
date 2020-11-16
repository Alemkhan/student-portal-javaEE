package Controllers;

import DAO.UserDAO;
import Models.Student;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "StudentsServlet")
public class StudentsServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            if (action != null) {
                switch (action) {
                    case "/studentsList":
                        studentsList(request, response);
                        break;
//                    case "/addStudent":
//                        addStudent(request, response);
//                        break;
                    case "/deleteStudent":
                        deleteStudent(request, response);
                        break;
                    default:
                        studentsList(request,response);
                        break;
                }
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void studentsList(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        ArrayList<User> studentsList = userDAO.getAll();
        request.setAttribute("studentsList", studentsList);
        request.getRequestDispatcher("students.jsp").forward(request,response);
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int user_id = (int) request.getAttribute("user_id");
        userDAO.deleteUser(user_id);
        request.getRequestDispatcher("/studentsList").forward(request,response);
    }

//    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
//        String first_name = request.getAttribute("first_name");
//        String last_name = request.getAttribute("last_name");
//        String email = request.getAttribute("email");
//        int major_id = request.getAttribute("major_id");
//        User student = new Student(first_name, last_name, email, major_id);
//    }
}
