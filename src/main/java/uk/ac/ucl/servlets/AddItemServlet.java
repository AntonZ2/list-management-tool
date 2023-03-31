package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.File;

@WebServlet("/AddItemServlet")
public class AddItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String listName = request.getParameter("listName");
        String itemType = request.getParameter("itemType");
        String itemKey = request.getParameter("itemKey");
        String itemData = request.getParameter("itemData");

        Model model = ModelFactory.getModel();
        model.addItem(listName, itemType, itemKey, itemData);

        response.sendRedirect("viewListItems.html?listName=" + listName);
    }
}

