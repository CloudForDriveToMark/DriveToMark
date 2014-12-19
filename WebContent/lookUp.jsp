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
      <form role="form" method="post" action="LookUp">
        <div class="form-group">
          <label for="city">Start City</label>
          <input type="text" class="form-control" id="city" name="city" placeholder="Enter city">
        </div>
        <div class="form-group">
          <label for="zipStart">Zip Code</label>
          <input type="text" class="form-control" id="zipStart" name="zipStart" placeholder="Enter zip code of current location">
        </div>
        <div class="form-group">
          <label for="people">Number of People</label>
          <input type="text" class="form-control" id="people" name="people" placeholder="How many people are you?">
        </div>
        <div class="form-group">
          <label for="officeAdd">Office Branch</label>
          <input type="text" class="form-control" id="officeAdd" name="officeAdd" placeholder="e.g. New York, New Jersy">
        </div>
        <div class="form-group">
          <label for="officeZip">Office Zip Code</label>
          <input type="text" class="form-control" id="officeZip" name="officeZip" placeholder="Enter office zip code">
        </div>
        <div class="form-group">
          <label for="distance">Distance Range</label>
         	 <select class="form-control" name = "distance">
   				 <option value="one">1 miles</option>
    			 <option value="five">5 miles</option>
   			 	 <option value="ten">10 miles</option>
    			 <option value="fifty">50 miles</option>
    			 <option value="auto">auto</option>
			</select>
        </div>
        
        <button type="submit" class="btn btn-default" name="submit" value="lookUp">LookUp</button>
      </form>
    </div>
  </body>
</html>