package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;
import uk.ac.ucl.lists.ListData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewListItems.html")
public class ViewListItemsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Get the list name from the request parameter
        String listName = request.getParameter("listName");

        // Get the data from the Model
        Model model = ModelFactory.getModel();
        ListData listData = model.getList(listName);

        // Add the list name and list data to the request object
        request.setAttribute("listName", listName);
        request.setAttribute("listData", listData.getItems());

        // Invoke the JSP
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/viewListItems.jsp");
        dispatch.forward(request, response);
    }
}

