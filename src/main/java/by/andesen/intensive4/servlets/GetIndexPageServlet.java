package by.andesen.intensive4.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GetIndexPageServlet", value = "/GetIndexPageServlet")
public class GetIndexPageServlet extends HttpServlet {

    private static String index = "/WEB-INF/views/index.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(index).forward(request,response);
    }
}
