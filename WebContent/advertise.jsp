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
<title>Advertise</title>

</head>
<body>
<jsp:include page="indexPage.jsp"/>
<div class="container">
      <h2>Welcome to MarkToDrive!!!!!!</h2>
      <form role="form" method="post" action="Advertise">
        <div class="form-group">
          <label for="carModel">Car Model:</label>
          <input type="text" class="form-control" id="carModel" name="carModel" placeholder="Enter car model">
        </div>
        <div class="form-group">
          <label for="capacity">Total Passenger Capacity:</label>
          <input type="text" class="form-control" id="capacity" name="capacity" placeholder="Enter Total capacity">
        </div>
        <div class="form-group">
          <label for="required">Number of Passengers required</label>
          <input type="text" class="form-control" id="required" name="required" placeholder="Enter Total capacity">
        </div>
        <div class="form-group">
          <label for="charge">Per Person Charge</label>
          <input type="text" class="form-control" id="charge" name= "charge" placeholder="How much is the charge">
        </div>
        <div class="form-group">
          <label for="startStAdd">Start Street Address</label>
          <input type="text" class="form-control" id="startStAdd" name= "startStAdd" placeholder="e.g. 519 Cinder Road">
        </div>
        <div class="form-group">
          <label for="startCity">Start City</label>
          <input type="text" class="form-control" id="startCity" name= "startCity" placeholder="">
        </div>
        <div class="form-group">
          <label for="startZip">Start Zip Code</label>
          <input type="text" class="form-control" id="startZip" name= "startZip" placeholder="">
        </div>
        <div class="form-group">
          <label for="officeAdd">Office Address</label>
          <input type="text" class="form-control" id="officeAdd" name= "officeAdd" placeholder="">
        </div>
        <div class="form-group">
          <label for="officeZip">Office Zip Code</label>
          <input type="text" class="form-control" id="officeZip" name= "officeZip" placeholder="">
        </div>
        <div class="form-group">
          <label for="message">Message</label>
          <input type="text" class="form-control" id="message" name= "message" placeholder="message">
        </div>
         <div class="form-group">
          <label for="date">Date</label>
          <input type="text" class="form-control" id="date" name= "date" placeholder="month-date-year">
        </div>
         <div class="form-group">
          <label for="time">Time</label>
          <input type="text" class="form-control" id="time" name= "time" placeholder="8:00 am">
        </div>
        <button type="submit" class="btn btn-default" name="submit" value="advertise">Advertise Now</button>
      </form>
    </div>
  </body>
</html>