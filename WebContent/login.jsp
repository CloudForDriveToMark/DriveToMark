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
<div class="container">
      <h2>Welcome to MarkToDrive!!!!!!</h2>
      <% String status= request.getParameter("status");
        if( status != null){
         	if( status.equals("emptyFields"))
       		{ %>
        		<font size="3" color="red">Fields Cannot be Left Empty</font>
        	<%}
        	else if(status.equals("invalidInput"))
        	{ %>
        		<font size="3" color="red">Incorrect UserName or Password</font>
        	<% }
        }%>
      <form role="form" method="post" action="Welcome">
        <div class="form-group">
          <label for="userName">User Name:</label>
          <input type="email" class="form-control" id="userName" name="userName" placeholder="Enter email">
        </div>
        <div class="form-group">
          <label for="pwd">Password:</label>
          <input type="password" class="form-control" id="pwd" name="password" placeholder="Enter password">
        </div>
        <div class="checkbox">
          <label><input type="checkbox"> Remember me</label>
        </div>
        
        <button type="submit" class="btn btn-default" name="submit" value="login">login</button>
        <h3>New User? Sign Up Here</h3>
        <button type="submit" class="btn btn-default" name="submit" value="signup">signup</button>
      </form>
    </div>
</html>