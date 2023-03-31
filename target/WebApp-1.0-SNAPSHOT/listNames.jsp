<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>List Data App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h2>Lists:</h2>
  <ul>
    <%
      List<String> listNames = (List<String>) request.getAttribute("listNames");
      for (String listName : listNames) {
        String href = "viewListItems.html?listName=" + URLEncoder.encode(listName, StandardCharsets.UTF_8);
    %>
    <li><a href="<%=href%>"><%=listName%></a>
    </li>
    <% } %>
  </ul>
  <br>
  <form action="editListNames.html" method="get">
    <input type="submit" value="Edit">
  </form>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>

