<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session = "true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<title>DriveToMark</title>

</head>
<body>
<jsp:include page="indexPage.jsp"/>
<div class="container">
      <h1>Welcome <% 
      System.out.println("hi "+ request.getSession().getAttribute("name") );
      out.print(request.getSession().getAttribute("name")); %></h1>
      <h2>What would you like you do today?</h2>
      <form role="form" method="post" action="UserProfile">
        <button type="submit" class="btn btn-primary" name="submit" value="advertise">Advertise Cars</button><br><br>
        <button type="submit" class="btn btn-primary" name="submit" value="lookUp">Lookup Cars</button><br><br>
        <button type="submit" class="btn btn-primary" name="submit" value="viewStatus">View Status</button><br><br>
      </form>
    </div>
  </body>
</html>