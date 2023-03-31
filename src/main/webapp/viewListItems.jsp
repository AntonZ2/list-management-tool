<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.lists.Item" %>
\<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>View List Items</title>
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
<jsp:include page="/header.jsp"/>
<div class="main">
  <h2>Items in <%=request.getAttribute("listName")%>:</h2>
  <table>
    <tr>
      <th>Key</th>
      <th>Data</th>
    </tr>
    <%
      List<Item> items = (List<Item>) request.getAttribute("listData");
      for (Item item : items) {
    %>
    <tr>
      <td><%=item.getKey()%></td>
      <td>
        <% if (item.isImage()) { %>
        <img src="data:image/png;base64, <%=item.getData()%>" alt="<%=item.getKey()%>"/>
        <% } else if (item.isUrl()) { %>
        <a href="<%=item.getData()%>"><%=item.getData()%></a>
        <% } else if (item.isListLink()) { %>
        <a href="viewListItems.html?listName=<%=item.getData()%>"><%=item.getData()%></a>
        <% } else { %>
        <%=item.getData()%>
        <% } %>
      </td>
    </tr>
    <% } %>
  </table>
  <br>
  <form action="editListItems.html" method="get">
    <input type="hidden" name="listName" value="<%= request.getParameter("listName") %>" />
    <input type="submit" value="Edit" />
  </form>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>


