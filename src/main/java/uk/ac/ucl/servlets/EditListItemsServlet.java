package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;
import uk.ac.ucl.lists.Item;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/editListItems.html")
public class EditListItemsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();
        String listName = request.getParameter("listName");

        List<Item> items = model.getList(listName).getItems();
        request.setAttribute("items", items); // Set the items attribute here

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/editListItems.jsp");
        dispatch.forward(request, response);
    }
}
