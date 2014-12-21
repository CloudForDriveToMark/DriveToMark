<%@page import="models.Advertisement"%>
<%@page import="models.Employee"%>
<%@page import="models.Request"%>
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
<title>Advertise</title>

</head>
<body>
<jsp:include page="plainHeader.jsp"/>
<div class="container">
      <h2>Details:</h2>
      <form role="form" method="post" action="DetailedAdvertisement">
        <% Request []requestReceived = (models.Request[])request.getSession().getAttribute("requestReceiveArray");
           Request []requestSent = (models.Request[])request.getSession().getAttribute("requestSentArray");
           if(requestReceived!=null){ %>
        	   <table>
               <tr> </tr>
        	  	<%for(int i =0;i<requestReceived.length;i++){  %>
        		<tr>
        		<td><h3><span class="label label-primary">recieved from:</span></h3></td>
        		<td><h4><% out.print(requestReceived[i].getRequester()); %></h4></td>
        		<td><h4><% out.print(requestReceived[i].isApproved()); %></h4></td>	
        		
       			 <%} %>
     			</table> <% } %>
       	 <% if(requestSent!=null){
        	  for(int i =0;i<requestSent.length;i++){ 
           
        %>
        	<table>
        	<tr>
        		<td><h3><span class="label label-primary">sent to from:</span></h3></td>
        		<td><h4><% out.print(requestSent[i].getApprover()); %></h4></td>
        		<td><h4><% out.print(requestSent[i].isApproved()); %></h4></td>	
        	</tr>
        	</table>
        <%}} %>
        <br>
        <button type="submit" class="btn btn-primary"  name="submit" value="contact">Contact Advertiser</button>
      </form>
    </div>
  </body>
</html>