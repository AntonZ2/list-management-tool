<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.AbstractMap" %>
<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.lists.Item" %>

<html>
<head>
  <title>Search Results</title>
  <jsp:include page="/meta.jsp"/>
</head>
<body>
<jsp:include page="/header.jsp"/>
<h2>Search Results</h2>
<table class="data-table">
  <tr>
    <th>List Name</th>
    <th>Key</th>
    <th>Data</th>
  </tr>
  <%
    List<AbstractMap.SimpleEntry<String, Item>> results = (List<AbstractMap.SimpleEntry<String, Item>>) request.getAttribute("result");
    for (AbstractMap.SimpleEntry<String, Item> result : results) {
      String listName = result.getKey();
      Item item = result.getValue();
  %>
  <tr>
    <td><a href="viewListItems.html?listName=<%=listName%>"><%=listName%></a></td>
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
<jsp:include page="/backButton.jsp">
  <jsp:param name="previousPage" value="search.html" />
</jsp:include>
<jsp:include page="/footer.jsp"/>
</body>
</html>
