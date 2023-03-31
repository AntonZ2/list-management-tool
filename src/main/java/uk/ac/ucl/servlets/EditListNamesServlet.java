package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/editListNames.html")
public class EditListNamesServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();
        List<String> listNames = model.getListNames();
        request.setAttribute("listNames", listNames); // Set the listNames attribute here

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/editListNames.jsp");
        dispatch.forward(request, response);
    }
}


