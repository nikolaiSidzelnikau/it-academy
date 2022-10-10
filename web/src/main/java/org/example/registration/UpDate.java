package org.example.registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.registration.check.ChangesUser;
import org.example.registration.check.CheckUser;
import org.example.registration.inter.ChangesUserInterface;
import org.example.registration.inter.CheckUserInterface;
import org.example.registration.inter.ReadingUser;
import org.example.registration.inter.exception.LoginException;
import org.example.registration.json.GetUser;
import org.example.registration.user.User;


import java.io.File;
import java.io.IOException;
@WebServlet(name = "UpDate",
urlPatterns = "/upDate")
public class UpDate extends HttpServlet {
    private final File file = new File("C:/Users/mikol/it-academy/jarModule/src/main/resources/json/json_file.json");
    private String idKey = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        idKey = req.getParameter("id");
        HttpSession session = req.getSession();
        session.setAttribute("idName",idKey);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/upDate.jsp");
            requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ChangesUserInterface changesUser = new ChangesUser(file);
        ReadingUser readingUser = new GetUser(file);
        CheckUserInterface checkUser = new CheckUser();
        HttpSession session = req.getSession();
        User user1 = (User) session.getAttribute("user");
        String id = (String) session.getAttribute("idName");
        if (id!=null){
            idKey = id;
        }
        String name = req.getParameter("userName");
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        User user ;
        user = readingUser.getUserByKey(idKey);
        if (user !=null){
            try {
                if (!user.getUserName().equals(name)){
                    checkUser.isValidationPasswordOrUserName(name);
                    user.setUserName(name);
                    checkUser.IsExistUserUserName(user,file);
                    changesUser.changesUserBySelectionTable(String.valueOf(user.getId()),"userName", name,file);
                }
                if (!user.getPassword().equals(pass)){
                    checkUser.isValidationPasswordOrUserName(pass);
                    user.setPassword(pass);
                    checkUser.IsExistUserPassword(user,file);
                    changesUser.changesUserBySelectionTable(idKey,"password", pass, file);
                }
                if (!user.getEmail().equals(email)){
                    checkUser.isValidationEmail(email);
                    user.setEmail(email);
                    checkUser.IsExistUserEmail(user,file);
                    changesUser.changesUserBySelectionTable(idKey,"email", email, file);
                }
            } catch (LoginException e) {
                String error = String.valueOf(e.getMessage());
                req.setAttribute("error", error);
                req.setAttribute("name", name);
                req.setAttribute("pass", pass);
                req.setAttribute("emailName", email);
                RequestDispatcher rd= req.getRequestDispatcher("WEB-INF/upDate.jsp");
                rd.forward(req, resp);
            }
        }
        if (user1!=null&&user!=null){
            if (user1.getRole().equals("admin")) {
                if (user1.getId()==(user.getId())) {
                    session.setAttribute("idUserName", name);
                } else {
                    user1 = readingUser.getUserByKey(String.valueOf(user1.getId()));
                    session.setAttribute("idUserName", user1.getUserName());
                }
            }else {
                session.setAttribute("idUserName",name);
            }
        }
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/hello");
        dispatcher.forward(req,resp);
    }

}