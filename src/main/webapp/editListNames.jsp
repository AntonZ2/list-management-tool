<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Edit List Names</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h2>Edit Lists:</h2>
  <ul>
    <%
      List<String> listNames = (List<String>) request.getAttribute("listNames");
      for (String listName : listNames) {
    %>
    <li>
      <%= listName %>
      <form action="renameList.html" method="post" style="display:inline;">
        <input type="hidden" name="oldListName" value="<%= listName %>"/>
        <input type="text" name="newListName"/>
        <input type="submit" value="Rename"/>
      </form>
      <form action="removeList.html" method="post" style="display:inline;">
        <input type="hidden" name="listName" value="<%= listName %>"/>
        <input type="submit" value="Delete"/>
      </form>
    </li>
    <% } %>
  </ul>
  <form action="addList.html" method="post">
    <label for="listName">New List Name:</label>
    <input type="text" id="listName" name="listName" required>
    <input type="submit" value="Add List">
  </form>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>

