<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="js/jquery-1.7.2.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
 
<link href="http://getbootstrap.com/examples/carousel/carousel.css" rel="stylesheet">
<title>DriveToMark</title>


</head>
<body>
<div class="container">
        <div class="container">
        <ul class="nav nav-tabs">
        <li><a href="profileIndexAfterLogin.jsp?sec=aboutUs">About Us</a></li>
        <li><a href="profileIndexAfterLogin.jsp?sec=home">Home</a></li>
        <li><a href="profileIndexAfterLogin.jsp?sec=contactUs">Contact Us</a></li>
        <li><a href="profileIndexAfterLogin.jsp?sec=news">News Letter</a></li>
        <li><a href="profileIndexAfterLogin.jsp?sec=logOut">Logout</a></li>
      </ul>
</div>
</div>
      <!-- Carousel
    ================================================== -->
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner" role="listbox">
        <div class="item active">
          <img src="images/carpooling4.jpg" alt="First slide">
          <div class="container">
            <div class="carousel-caption">
              <h1>Welcome to Drive to mark.....</h1>
            </div>
          </div>
        </div>
        <div class="item">
          <img src="images/carpooling5.jpg" alt="Second slide">
          <div class="container">
            <div class="carousel-caption">
              <h1>Search cars near your locations......</h1>
            </div>
          </div>
        </div>
        <div class="item">
          <img src="images/carpoolthird.jpg" alt="Third slide">
          <div class="container">
            <div class="carousel-caption">
              <h1>Advertise your cars to pool........</h1>
            </div>
          </div>
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right"></span>
        <span class="sr-only">Next</span>
      </a>
    </div><!-- /.carousel -->
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
    <script src="http://getbootstrap.com/assets/js/docs.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>