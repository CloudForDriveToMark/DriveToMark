DOCTYPE html>
    <html>
  <head>
    <meta charset="utf-8">
    <title>ski finder</title>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true&libraries=places"></script>

    <style>
      #map {
        height: 400px;
        width: 600px;
        border: 1px solid #333;
        margin-top: 0.6em;
      }
    </style>

      </head>
      <body>
      <%
		String[] lat = (String[])request.getSession().getAttribute("addressArray");
		
		java.util.ArrayList<Double> lngArray = (java.util.ArrayList<Double>)request.getSession().getAttribute("lngArray");
		Double[] lng = new Double[lngArray.size()]; 
		lngArray.toArray(lng);
		System.out.println("got array lat and long");

	  %>
    <script>
  		var geocoder;
  		var map;
     	var infowindow;
     	var addressArray = new Array();
     	var address = ["519 Cinder road, 08820","10001"];
     	<% for(String element:lat){
    		System.out.println("getting something in lar");
    	%> addressArray[x] = <%=element %>
    	x=x+1;
    	<% } %>
     	
      function initialize() {
        geocoder = new google.maps.Geocoder();
        var loca = new google.maps.LatLng(41.7475, -74.0872);

        map = new google.maps.Map(document.getElementById('map'), {
          mapTypeId: google.maps.MapTypeId.ROADMAP,
          center: loca,
          zoom: 8
        });

      }

      function callback(results, status) {
        if (status == google.maps.places.PlacesServiceStatus.OK) {
          for (var i = 0; i < results.length; i++) {
            createMarker(results[i]);
          }
        }
      }

      function createMarker(place) {
        var placeLoc = place.geometry.location;
        var marker = new google.maps.Marker({
          map: map,
          position: place.geometry.location
        });

        google.maps.event.addListener(marker, 'mouseover', function() {
          infowindow.setContent(place.name);
          infowindow.open(map, this);
        });
      }

  function callCodeAddress(){
	  
	  var i;
	  for(i=0;i<address.length;i++){
		  codeAddress(address[i]);
	  }
  }
      
  function codeAddress(address) {
    
    geocoder.geocode( { 'address': address}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        map.setCenter(results[0].geometry.location);
        var marker = new google.maps.Marker({
            map: map,
            position: results[0].geometry.location
        });
        var request = {
          location: results[0].geometry.location,
          
        };
        infowindow = new google.maps.InfoWindow();
        var service = new google.maps.places.PlacesService(map);
        service.nearbySearch(request, callback);
      } else {
        alert("Geocode was not successful for the following reason: " + status);
      }
    });
  }

      google.maps.event.addDomListener(window, 'load', initialize);
      </script>
        
        <form>
       
        <input type="button" value="Submit" onclick="callCodeAddress();" ></input>
   		 
        <div id="map"></div>
        
	</form>
      </body>
    </html>
    
    
    
    
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
<!--

/*
Auto Refresh Page with Time script
By JavaScript Kit (javascriptkit.com)
Over 200+ free scripts here!
*/

//enter refresh time in "minutes:seconds" Minutes should range from 0 to inifinity. Seconds should range from 0 to 59
var limit="0:30"

if (document.images){
var parselimit=limit.split(":")
parselimit=parselimit[0]*60+parselimit[1]*1
}
function beginrefresh(){
if (!document.images)
return
if (parselimit==1)
window.location.reload()
else{ 
parselimit-=1
curmin=Math.floor(parselimit/60)
cursec=parselimit%60
if (curmin!=0)
curtime=curmin+" minutes and "+cursec+" seconds left until page refresh!"
else
curtime=cursec+" seconds left until page refresh!"
window.status=curtime
setTimeout("beginrefresh()",1000)
}
}

window.onload=beginrefresh

//-->
</script>
</head>
<body onload="getLocation()">

<%
models.Advertisement[] advertisement = (models.Advertisement[])request.getSession().getAttribute("advertisementArray");
System.out.println("got array of advertisement");
%>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" type="text/javascript"></script> 
<script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script> 
<script type="text/javascript"> 

var httpKeywordReq, httpAllTweetReq, interval;
var httpSpecificUserTweetReq, mapOptions, map;

	function retrieveKeywords() {
		if (window.XMLHttpRequest) {
			httpKeywordReq = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			httpKeywordReq = new ActiveXObject("Microsoft.XMLHTTP");
		}
		httpKeywordReq.open('POST', '/SNSEndPoint', true);
		httpKeywordReq.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded');
		httpKeywordReq.onreadystatechange = handleKeywordsResponse;
		httpKeywordReq.send();
	}
	function retrieveTweets(keyword) {
		if (window.XMLHttpRequest) {
			httpAllTweetReq = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			httpAllTweetReq = new ActiveXObject("Microsoft.XMLHTTP");
		}
		httpAllTweetReq.open('POST', '/SNSEndPoint', true);
		httpAllTweetReq.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');
		
		httpAllTweetReq.onreadystatechange = handleTweetsResponse;
		httpAllTweetReq.send(keyword);
	}
	
	function getTweets() {
		var txt = document.getElementById('username').value;
		if (txt.length > 0) {
			if (window.XMLHttpRequest) {
				httpSpecificUserTweetReq = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				httpSpecificUserTweetReq = new ActiveXObject(
						"Microsoft.XMLHTTP");
			}
			httpSpecificUserTweetReq.open('POST',
					'/SNSEndPoint', true);
			httpSpecificUserTweetReq.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded');
			httpSpecificUserTweetReq.onreadystatechange = httpSpecificUserTweetReq;
			httpSpecificUserTweetReq.send("username=" + txt);
			
			deleteMarkers();
			interval = setInterval(function(){ retrieveTweets("type=live"); }, 5000);
		} else {
			var elem = document.getElementById('selectKeyword');
			var strUser = elem.options[elem.selectedIndex].value;
			if (strUser == "All Tweets") {
				retrieveTweets();
			} else {
				retrieveTweets("input=" + strUser);
			}
		}
	}

	function handleTweetsResponse() {
		
		if (httpAllTweetReq.readyState == 4) {
			var myVar = JSON.parse(httpAllTweetReq.responseText);
			var data = myVar.data;
			if(myVar.type != "live"){
				deleteMarkers();
			}
			if(myVar.count == 0 && myVar.type == "live" && myVar.msg == "done"){
				clearInterval(interval);
			}
			for (var j = 0; j < data.length; j++) {
				var json = JSON.parse(data[j]);
				var point = new google.maps.LatLng(json.latitude, json.longitude);
				
				var text = "Username: @" + json.username + "\n"
						+ "Sentiment: " + json.sentiment + "\n"
						+ "Timestamp: " + json.timestamp + "\n"
						+ "Tweet text: " + json.text;
				var pc = pinColorDefault;
				if(json.sentiment == "positive"){
					pc = pinColorPositive;
				} else if(json.sentiment == "negative"){
					pc = pinColorNegative;
				} else if(json.sentiment == "neutral"){
					pc = pinColorNeutral;
				}
				addMarker(point, text, pc);
			}
		}
	}
	function getLocation(){
	
	
		var jsLatArray = new Array();
		var jsLngArray = new Array();
		var carModel = new Array();
		var message = new Array();
		var x = 0;
		var y = 0;
		alert("fine till declaration");
		<%
		
		for(int i = 0; i< advertisement.length;i++){
			%> jsLatArray[x] = <%=advertisement[i].getLatitute()%>
			   jsLngArray[x] = <%=advertisement[i].getLongitude()%>
			   carModel[x] = <%=advertisement[i].getCarModel()%>
			   message[x] = <%=advertisement[i].getMessage()%>
		   x=x+1;
		<%}%>
		
		alert("fine till ar");
			               var map = new google.maps.Map(document.getElementById('map_canvas'), {
			                 zoom: 3,
			                 center: new google.maps.LatLng(0, 0),
			                 mapTypeId: google.maps.MapTypeId.ROADMAP
			               });

			               var infowindow = new google.maps.InfoWindow();

			               var marker, i;

			               for (i = 0; i < jsLatArray.length; i++) {  
			                   marker = new google.maps.Marker({
			                   position: new google.maps.LatLng(jsLatArray[i], jsLngArray[i]),
			                   title : carModel[i],
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
<button onclick="getTweets()">Get Live Tweets</button>
   <div id="map_canvas" style="width:1200px;height:800px;">Google Map</div> 
</body>
</html>