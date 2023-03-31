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

@WebServlet("/UpdateItemServlet")
public class UpdateItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String listName = request.getParameter("listName");
        int itemInd = Integer.parseInt(request.getParameter("itemIndex"));
        String newData = request.getParameter("newData");

        Model model = ModelFactory.getModel();
        model.updateItem(listName, itemInd, newData);

        response.sendRedirect("viewListItems.html?listName=" + listName);
    }
}
