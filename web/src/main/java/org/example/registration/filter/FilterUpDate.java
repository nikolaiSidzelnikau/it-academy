package org.example.registration.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.registration.inter.ReadingUser;
import org.example.registration.json.GetUser;
import org.example.registration.user.User;

import java.io.File;
import java.io.IOException;

@WebFilter(servletNames = {"UpDate"})
public class FilterUpDate implements Filter {
    private final File file = new File("C:/Users/mikol/it-academy/jarModule/src/main/resources/json/json_file.json");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String idKey = request.getParameter("id");
        User user;
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            session.setAttribute("id", idKey);
            ReadingUser readingUser = new GetUser(file);
            user = readingUser.getUserByKey(String.valueOf(idKey));
            request.setAttribute("name", user.getUserName());
            request.setAttribute("pass", user.getPassword());
            request.setAttribute("emailName", user.getEmail());
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}