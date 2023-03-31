<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Search Results</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h1>Search Results</h1>
  <%
    // data type is confirmed so warning should not be worried about
    List<Map<String, String>> results = (List<Map<String, String>>) request.getAttribute("result");
    if (results.size() != 0) {
  %>
  <ul>
    <%
      for (Map<String, String> itemData : results) {
    %>
    <li>
      <% if (itemData.containsKey("text")) { %>
      <p><%= itemData.get("text") %></p>
      <% } %>
      <% if (itemData.containsKey("url")) { %>
      <a href="<%= itemData.get("url") %>"><%= itemData.get("url") %></a>
      <% } %>
      <% if (itemData.containsKey("image")) { %>
      <img src="data:image/png;base64,<%= itemData.get("image") %>" />
      <% } %>
    </li>
    <% }
    %>
  </ul>
  <% } else { %>
  <p>No results found.</p>
  <% } %>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>