<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Text</title>
</head>
<body>
<h2>${message}</h2>
  <form method="post" action="save">
    <p>Enter the text: <input type="text" name="userText"/></p>
    <input type="submit" name="save" value="Save">
  </form>
  <form method="post" action="logout">
    <input type="submit" name="logout" value="Logout"/>
  </form>
</body>
</html>
