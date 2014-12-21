<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lookup Results</title>
<jsp:include page="plainHeader.jsp"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<style type="text/css">
   .labels {
     color: red;
     background-color: white;
     font-family: "Lucida Grande", "Arial", sans-serif;
     font-size: 10px;
     font-weight: bold;
     text-align: center;
     width: 40px;     
     white-space: nowrap;
   }
 </style>
</head>

<body onload="getLocation()">

	<%
	models.Advertisement[] advertisement = (models.Advertisement[])request.getSession().getAttribute("advertisementArray");
	System.out.println("got array of advertisement");
	String[] lableIndex = new String[advertisement.length];
	Double[] lat = new Double[advertisement.length]; 
	Double[] lng = new Double[advertisement.length]; 
	Double[] destLat = new Double[advertisement.length]; 
	Double[] destLng = new Double[advertisement.length]; 
	String[] destinationString = new String[advertisement.length];
	String[] destinationZipString =  new String[advertisement.length];
	for(int j = 0;j<advertisement.length;j++){
		lat[j] = advertisement[j].getLatitute();
		lng[j] = advertisement[j].getLongitude();
		destLat[j] = advertisement[j].getDestLat();
		destLng[j] = advertisement[j].getDestLng();
		destinationString[j] = advertisement[j].getDestination();
		destinationZipString[j] = advertisement[j].getDestinationZip();
		lableIndex[j] = String.valueOf(j+1);
	}
	%>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" type="text/javascript"></script> 
<script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script> 
<script src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/markerwithlabel/src/markerwithlabel.js" type="text/javascript"></script>
<script type="text/javascript"> 

	function getLocation(){
		
		
		
		var jsLatArray = new Array();
		var jsLngArray = new Array();
		var jsDestLatArray = new Array();
		var jsDestLngArray = new Array();
		var destination = [];
		var lableIndexJs = [];
		var destination_zip = new Array();
		var map;
	
		var x = 0;
		var y = 0;
		var z = 0;
		var w = 0;
		var r = 0;
		var s = 0;
		
		map = new google.maps.Map(document.getElementById('map_canvas'), {
		     zoom: 8,
		     center: new google.maps.LatLng(40, -74),
		     mapTypeId: google.maps.MapTypeId.ROADMAP
		 });
		
		<% for(Double element:lat){
			System.out.println("getting something in lar");
		%> jsLatArray[x] = <%=element %>
			x=x+1;
		<% } %>
	
	
		<% for(Double lgnElement:lng){
			%> jsLngArray[y] = <%=lgnElement %>
			y=y+1;
		<% } %>
		
		<% for(Double eachDestLat:destLat){
			%> jsDestLatArray[r] = <%=eachDestLat %>
			r=r+1;
		<% } %>
			
		<% for(Double eachDestLng:destLng){
			%> jsDestLngArray[s] = <%=eachDestLng %>
			s=s+1;
		<% } %>
		
		
		<% for(String eachDestination:destinationString){
			%>
			destination[z] = "<%=eachDestination %>"
			z=z+1;
		<% } %>
		
		<% for(String label:lableIndex){
			%> lableIndexJs[w] = "<%=label %>"
			w=w+1;
		<% } %>	
		
		 map = new google.maps.Map(document.getElementById('map_canvas'), {
		     zoom: 12,
		     center: new google.maps.LatLng(jsLatArray[0], jsLngArray[0]),
		     mapTypeId: google.maps.MapTypeId.ROADMAP
		 });

		               var infowindow = new google.maps.InfoWindow();

		               var marker, i;

		               for (i = 0; i < jsLatArray.length; i++) {  
		            	  
		                 marker = new MarkerWithLabel({
		                   position: new google.maps.LatLng(jsLatArray[i], jsLngArray[i]),
		                   title: destination[i],
		                   map: map,
		                   labelContent: lableIndexJs[i],
		                   labelAnchor: new google.maps.Point(22, 0),
		                   labelClass: "labels", // the CSS class for the label
		                   labelStyle: {opacity: 0.75}
		                 });

		                 google.maps.event.addListener(marker, 'click', (function(marker, i) {
		                   return function() {
		                     
		                	  
		                	   marker = new MarkerWithLabel({
				                   position: new google.maps.LatLng(jsDestLatArray[i], jsDestLngArray[i]),
				                   map: map,
				                   labelContent: lableIndexJs[i],
				                   labelAnchor: new google.maps.Point(22, 0),
				                   labelClass: "labels", // the CSS class for the label
				                   labelStyle: {opacity: 0.75}
				                 });
		                	   
		                	   var flightPlanCoordinates = [
		                	                                new google.maps.LatLng(jsLatArray[i], jsLngArray[i]),
		                	                                new google.maps.LatLng(jsDestLatArray[i], jsDestLngArray[i])
		                	                                ]
		                	   var flightPath = new google.maps.Polyline({
		                		    path: flightPlanCoordinates,
		                		    geodesic: true,
		                		    strokeColor: '#FF0000',
		                		    strokeOpacity: 1.0,
		                		    strokeWeight: 2
		                		  });
		                	   flightPath.setMap(map);
		                   }
		                   
		                 })(marker, i));
		                 
		                
		                 
		               }
		               
		               
	}
		
</script>

   
   <div id="map_canvas" align="left" style="width:1200px;height:500px;">Google Map</div>  
   <jsp:include page="filter.jsp"></jsp:include>
   <table class="table table-striped">
   <caption>Trips in your area</caption>
   <thead>
      <tr>
         <th>number on map</th>
         <th>source</th>
         <th>destination</th>
         <th>date</th>
         <th>time</th>
         <th>People Required</th>
      </tr>
   </thead>
   <tbody>
   <% if(advertisement.length>0){ 
      for(int i = 0;i<advertisement.length ; i++) { 
     %>
      <tr>
         <td><a href =<%= "\"detailedAdvertisement.jsp?Id=" + i + "\"" %> ><% out.print(i+1); %></a></td>
         <td><% out.print(advertisement[i].getStartCity()); %></td>
         <td><% out.print(advertisement[i].getDestination()); %></td>
         <td><% out.print(advertisement[i].getDate()); %></td>
         <td><% out.print(advertisement[i].getTime()); %></td>
         <td><% out.print(advertisement[i].getRequired()); %></td>
      </tr>
      <% } 
   }else{ %>
      <tr>
      	<td><h5>Sorry we could not find anything for you...</h5></td>
      </tr>
      
      <%}%>
   </tbody>
</table>
   
   
</body>
</html>