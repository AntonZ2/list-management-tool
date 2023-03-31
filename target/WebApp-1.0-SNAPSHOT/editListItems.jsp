<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.lists.Item" %>

<html>
<head>
  <title>Edit List Items</title>
  <style>
    table {
      width: 100%;
      border-collapse: collapse;
    }
    th, td {
      padding: 8px;
      text-align: left;
      border-bottom: 1px solid #ddd;
    }
    th {
      background-color: #f2f2f2;
    }
    img {
      max-width: 200px;
      max-height: 200px;
    }
  </style>
</head>
<body>
<h2>Edit List Items</h2>

<h3>Add Item</h3>
<form action="AddItemServlet" method="post">
  <input type="hidden" name="listName" value="<%= request.getParameter("listName") %>" />
  Item Type: <select name="itemType">
  <option value="text">Text</option>
  <option value="url">URL</option>
  <option value="image">Image</option>
  <option value="list_link">List Link</option>
</select><br>
  Key: <input type="text" name="itemKey" /><br>
  Data: <input type="text" name="itemData" /><br>
  <input type="submit" value="Add Item" />
</form>

<h3>Update/Delete Items</h3>
<table>
  <tr>
    <th>Key</th>
    <th>Data</th>
    <th>Actions</th>
  </tr>
  <%
    String listName = request.getParameter("listName");
    List<Item> items = (List<Item>) request.getAttribute("items");
    int itemIndex = 0;
    for (Item item : items) {
      String itemKey = item.getKey();
  %>
  <tr>
    <td><%= itemKey %></td>
    <td>
      <% if (item.isImage()) { %>
      <img src="data:image/png;base64,<%= item.getData() %>" alt="<%= itemKey %>" />
      <% } else { %>
      <%= item.getData() %>
      <% } %>
    </td>
    <td>
      <form action="UpdateItemServlet" method="post" style="display:inline;">
        <input type="hidden" name="listName" value="<%= listName %>" />
        <input type="hidden" name="itemIndex" value="<%= itemIndex %>" />
        New Data: <input type="text" name="newData" />
        <input type="submit" value="Update Item" />
      </form>
      <form action="DeleteItemServlet" method="post" style="display:inline;">
        <input type="hidden" name="listName" value="<%= listName %>" />
        <input type="hidden" name="itemIndex" value="<%= itemIndex %>" />
        <input type="submit" value="Delete Item" />
      </form>
    </td>
  </tr>
  <% itemIndex++; } %>
</table>
</body>
</html>
