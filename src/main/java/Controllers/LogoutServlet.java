package Controllers;

import Models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

@WebServlet(value = "/logout", name = "LogoutServlet")
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("user");
        session.invalidate();
        Cookie[] cookies = request.getCookies();
        for (Cookie ck:cookies ) {
            if(ck.getName().equals("time")) {
                try {
                    File myObj = new File("C:\\Users\\alemh\\filename.txt");
                    if (myObj.createNewFile()) {
                        System.out.println("File created: " + myObj.getName());
                    } else {
                        System.out.println("File already exists.");
                    }
                    FileWriter myWriter = new FileWriter("C:\\Users\\alemh\\filename.txt", true);
                    myWriter.write(user.getFirst_name() + " " + user.getLast_name() + " Time spend on the website: " + ((int) (new Date().getTime()/1000) - Integer.parseInt(ck.getValue())) + "\n");
                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                ck.setMaxAge(0);
                response.addCookie(ck);
                break;
            }
        }
        response.sendRedirect("login.jsp");
    }
}
