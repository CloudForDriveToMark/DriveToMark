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
<style type="text/css">
.container{
   width: 500px;
   height: 500px;
   line-height: 500px;
   text-align:center;
   border:1px solid red;
}

.form-group{

   width:100px;
   height:100px;
   display:inline-block;
}
</style>
</head>
<body>


     
      <form role="form" method="post" action="LookUp">
      
          <label for="startAddress">Start City</label>
          <input type="text" id="startAddress" name="startAddress" required
                data-bv-notempty-message="start city is required"  placeholder="e.g. Nyu Poly, NY">
          <label for="startZip">Start ZipCode</label>
          <input type="text" id="startZip" name="startZip" required
                data-bv-notempty-message="start zipcode is required" placeholder="e.g. 11201">
        
          <label for="distance">Distance Range</label>
         	 <select  name = "distance">
         	     <option selected="selected" value="0">select </option>
   				 <option value="1">1 miles</option>
    			 <option value="5">2 miles</option>
   			 	 <option value="10">5 miles</option>
    			 <option value="50">10 miles</option>
			</select>
		  <label for="people">People Required</label>
         	 <select  name = "people">
         	     <option selected="selected" value="0">select </option>
   				 <option value="1">1 </option>
    			 <option value="2">2</option>
   			 	 <option value="3">3</option>
    			 <option value="4">4</option>
    			 <option value="5">5</option>
			</select>
       
          <label for="destinationAddress">Destination Address</label>
          <input type="text" id="destinationAddress" name="destinationAddress" placeholder="e.g. New York">
        
        <button type="submit" class="btn btn-default" name="submit" value="filter">Apply</button>
      </form>
   
  </body>
</html>