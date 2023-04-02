<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <meta charset="UTF-8">
  <title>Lists Management App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<h2>Welcome to List Manager</h2>
<nav>
  <ul>
    <li>
      <a href="viewListNames.html">View All Lists</a>
    </li>
    <li>
      <a href="search.html">Search by Keyword</a>
    </li>
  </ul>
</nav>
<jsp:include page="/footer.jsp"/>
</body>
</html>