<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body onload="getLocation()">
<%

models.Advertisement[] advertisement = (models.Advertisement[])request.getSession().getAttribute("advertisementArray");
System.out.println("got array of advertisement");

Double[] lat = new Double[advertisement.length]; 
Double[] lng = new Double[advertisement.length]; 
String[] destinationString = new String[advertisement.length];
String[] destinationZipString =  new String[advertisement.length];
for(int j = 0;j<advertisement.length;j++){
	lat[j] = advertisement[j].getLatitute();
	lng[j] = advertisement[j].getLongitude();
	destinationString[j] = advertisement[j].getDestination();
	destinationZipString[j] = advertisement[j].getDestinationZip();
}
System.out.println("got array lat and long");

%>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" type="text/javascript"></script> 
<script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script> 
<script type="text/javascript"> 

var httpKeywordReq, httpAllTweetReq, interval;
var httpSpecificUserTweetReq, mapOptions, map;

	

	
	function getLocation(){
	
	
	var jsLatArray = new Array();
	var jsLngArray = new Array();
	var destination = [];
	var destination_zip = new Array();
	
	var x = 0;
	var y = 0;
	var z = 0;
	<% for(Double element:lat){
		System.out.println("getting something in lar");
	%> jsLatArray[x] = <%=element %>
	x=x+1;
	<% } %>
	
	
	<% for(Double lgnElement:lng){
		%> jsLngArray[y] = <%=lgnElement %>
		y=y+1;
		<% } %>
		
		alert("before assigment");
		<% for(String eachDestination:destinationString){
			%>
			alert("working till here");
			destination[z] = "<%=eachDestination %>"
			alert("here also");
			z=z+1;
			<% } %>
		
		alert("after assigmnt");
		               var map = new google.maps.Map(document.getElementById('map_canvas'), {
		                 zoom: 5,
		                 center: new google.maps.LatLng(40, -74),
		                 mapTypeId: google.maps.MapTypeId.ROADMAP
		               });

		               var infowindow = new google.maps.InfoWindow();

		               var marker, i;

		               for (i = 0; i < jsLatArray.length; i++) {  
		                 marker = new google.maps.Marker({
		                   position: new google.maps.LatLng(jsLatArray[i], jsLngArray[i]),
		                   title: destination[i],
		                   map: map
		                 });

		                 google.maps.event.addListener(marker, 'click', (function(marker, i) {
		                   return function() {
		                     
		                     infowindow.open(map, marker);
		                   }
		                 })(marker, i));
		                 
		               }
		               
		               interval = setInterval(function(){ retrieveTweets("type=live"); }, 5000);
	}
		
</script>
<% int counter = 0; %>

   <div id="map_canvas" style="width:1200px;height:800px;">Google Map</div> 
</body>
</html>