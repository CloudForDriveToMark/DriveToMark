<%@page import="models.Advertisement"%>
<%@page import="models.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
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
<script type="text/javascript">
function alertUser(){
	alert("An email will be sent to advertise");
}
function validateForm(){
	var seatRequested= document.check.seatsNeed.value;
	var seatAvailable = document.check.seatAvailable.value;
	if(seatRequested>seatAvailable){
		alert("Seats available are less than you requested");
		return false;
	}else{
		return true;
	}

}
</script>
<title>Advertise</title>

</head>
<body>
<jsp:include page="plainHeader.jsp"/>
<div class="container">
      <h2>Details:</h2>
      <form role="form" method="post" action="DetailedAdvertisement" name="check" onsubmit="return validateForm();" >
        <% Advertisement []advertisement = (models.Advertisement[])request.getSession().getAttribute("advertisementArray");
       		Employee []employeeArray = (models.Employee[])request.getSession().getAttribute("employeeArray");
        	int index = Integer.parseInt(request.getParameter("Id"));
        	request.getSession().setAttribute("employeeToContactEmail", employeeArray[index].getUserName());
        	request.getSession().setAttribute("employeeToContactName", employeeArray[index].getName());
        	request.getSession().setAttribute("advertiseId", advertisement[index].getAdvertisementId());
        %>
        <table>
        <tr>
        	<td><h3><span class="label label-primary">Advertiser:</span></h3></td>
        	<td><h4><% out.print(employeeArray[index].getName()); %></h4></td>
        </tr>
        <tr>
        	<td><h3><span class="label label-primary">email:</span></h3></td>
        	<td><h4><% out.print(employeeArray[index].getUserName()); %></h4></td>
        </tr>
        <tr>
        	<td><h3><span class="label label-primary">Contact:</span></h3></td>
        	<td><h4><% out.print(employeeArray[index].getContact()); %></h4></td>
        </tr>
        <tr>
        	<td><h3><span class="label label-primary">Car Model:</span></h3></td>
        	<td><h4><% out.print(advertisement[index].getCarModel()); %></h4></td>
        </tr>
        <tr>
        	<td><h3><span class="label label-primary">Price:</span></h3></td>
        	<td><h4><% out.print(advertisement[index].getCharge()); %></h4></td>
        </tr>
        <tr>
        	<td><h3><span class="label label-primary">People required:</span></h3></td>
        	<td><h4><% out.print(advertisement[index].getRequired()); %></h4></td>
        </tr>
        <tr>
        	<td><h3><span class="label label-primary">Start from:</span></h3></td>
        	<td><h4><% out.print(advertisement[index].getStartStAdd()+" "+ 
                	advertisement[index].getStartCity() + " "+ advertisement[index].getStartZip()); %></h4></td>
        </tr>
        <tr>
        	<td><h3><span class="label label-primary">Going to::</span></h3></td>
        	<td><h4><% out.print(advertisement[index].getDestination()+" "+ 
                	advertisement[index].getDestinationZip()); %></h4></td>
        </tr>
        <tr>
        	<td><h3><span class="label label-primary">Date:</span></h3></td>
        	<td><h4><% out.print(advertisement[index].getDate()); %></h4></td>
        </tr>
        <tr>
        	<td><h3><span class="label label-primary">Time:</span></h3></td>
        	<td><h4><% out.print(advertisement[index].getTime()); %></h4></td>
        </tr>
        <tr>
        	<td><h3><span class="label label-primary">Message:</span></h3></td>
        	<td><h4><% out.print(advertisement[index].getMessage()); %></h4></td>
        </tr>
      
        </table>
         <input type="hidden" id="seatAvailable" value="<%=advertisement[index].getRequired() %>">
        <label for="startAddress">How many seats you need?</label>
        	<select  name="seatsNeed" id="seatsNeed" required
                data-bv-notempty-message="field required">
   				 <option selected="selected" value="1">1 </option>
    			 <option value="2">2</option>
   			 	 <option value="3">3</option>
    			 <option value="4">4</option>
    			 <option value="5">5</option>
			</select>
        <br>
        
        <button type="submit" class="btn btn-primary"  name="submit" value="contact">Send Request</button>
      </form>
    </div>
  </body>
</html>