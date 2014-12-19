<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
      <h2>Welcome to MarkToDrive!!!!!!</h2>
      <form role="form" method="post" action="SignUpProcess">
        <div class="form-group">
          <label for="email">Email/User Name:</label>
          <input type="email" class="form-control" id="email" name="email" placeholder="Enter email">
        </div>
        <div class="form-group">
          <label for="pwd">Password:</label>
          <input type="password" class="form-control" id="pwd" name="password" placeholder="Enter password">
        </div>
        <div class="form-group">
          <label for="Name">Name:</label>
          <input type="Text" class="form-control" id="name" name="name" placeholder="Enter name">
        </div>
        <div class="form-group">
          <label for="contact">Contact:</label>
          <input type="text" class="form-control" id="contact" name="contact"placeholder="Enter contact number">
        </div>
        <div class="form-group">
          <label for="stAdd">Street Address:</label>
          <input type="text" class="form-control" id="stAdd" name= "stAdd" placeholder="Enter street address. e.g. 519 Cinder Raad">
        </div>
        <div class="form-group">
          <label for="city">City</label>
          <input type="text" class="form-control" id="city" name="city" placeholder="Enter city">
        </div>
        <div class="form-group">
          <label for="state">State</label>
          <input type="text" class="form-control" id="state" name="state" placeholder="Enter state">
        </div>
        <div class="form-group">
          <label for="country">Country</label>
          <input type="text" class="form-control" id="country" name="country" placeholder="Enter country">
        </div>
        <div class="form-group">
          <label for=""zip"">Zip Code</label>
          <input type="text" class="form-control" id="zip" name="zip" placeholder="Enter zip">
        </div>
        
        <button type="submit" class="btn btn-default" name="submit" value="signup">signup</button>
      </form>
    </div>
  </body>
</html>