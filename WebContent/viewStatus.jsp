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
      <form role="form" method="post" action="ViewStatus">
      <% String status= request.getParameter("status");
        if( status != null){
         	if( status.equals("alreadyRequested"))
       		{ %>
        		<font size="3" color="red">You have already requested to this advertiser</font>
        	<%}
        	else if(status.equals("requestSent"))
        	{ %>
        		<font size="3" color="green">We have successfully sent your request to advertiser</font>
        	<% }
        }%>
        <% Request []requestReceived = (models.Request[])request.getSession().getAttribute("requestReceiveArray");
           Request []requestSent = (models.Request[])request.getSession().getAttribute("requestSentArray");
           
           %>
           <%if(requestReceived!=null && requestReceived.length>0 ){ %>
           	   <h3>Requests Received by you</h3>	
        	   <table>
               <tr> 
               	<th>Requester</th>
               	<th>Seats Requested</th>
               	<th>Approval Status</th>
               	<th></th>
               </tr>
               
        	  	<%for(int i =0;i<requestReceived.length;i++){  %>
        		  <tr>
        			<td><% out.print(requestReceived[i].getRequester()); %></td>
        			<td align="center"><% out.print(requestReceived[i].getNumberOfPeople()); %></td>
        			<td align="center"><% out.print(requestReceived[i].isApproved()); %></td>
        			<% if(requestReceived[i].isApproved().equals("no")){ %>
        				<td><button type="submit" class="btn btn-primary" name="submit" value="approve<%=i %>">Approve</button></td>	
        			<% }else{ %>
        				<td><button type="submit" class="btn btn-primary" disabled="disabled" name="submit" value="approve<%=i %>">Approved</button></td>
       				 <%} %>
       			 </tr>
       			 <% } %>
        	  	</table>
        	  <%} %> 
     			
     			 
     			
     			
       	 <% if(requestSent!=null && requestSent.length>0){   
       	 %><h3>Requests Sent by you</h3>	
        	<table title="Requests sent by you">
               <tr> 
               	<th>Advertiser</th>
               	<th>Approval Status</th>
               	<th></th>
               	<th></th>
               	
               </tr>
        	  	<%for(int i =0;i<requestSent.length;i++){  %>
        		<tr>
        		<td><% out.print(requestSent[i].getApprover()); %></td>
        		<td align="center"><% out.print(requestSent[i].isApproved()); %></td>
        		
        		
       			 <%} %>
     			</table> <% } %>
        <br>
        <button type="submit" class="btn btn-primary"  name="submit" value="lookUp">Back to Home</button>
      </form>
    </div>
  </body>
</html>