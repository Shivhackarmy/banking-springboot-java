<!DOCTYPE html>
<%@page import="org.apache.jasper.compiler.Node.IncludeAction"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>ProBank</title>

<%@include file="style.jsp" %>
</head>

<body>

<!-- NAVBAR -->
<div class="navbar">
<h2>ProBank</h2>
<ul>
<a class="botton" href="reg" style="background-color: green; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; display: inline-block;">Open Account</a>
<a class="botton" href="login" style="background-color: green; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; display: inline-block;">Login</a>
<a class="botton" href="#" style="background-color: green; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; display: inline-block;">Features</a>
<a class="botton" href="#" style="background-color: green; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; display: inline-block;">Contact</a>
</ul>
</div>

<%@include file="header.jsp" %>

<!-- FEATURES -->
<div class="features">
<h2>Why Choose Us</h2>

<div class="feature-box">

<div class="feature">
<img src="https://cdn-icons-png.flaticon.com/512/633/633611.png">
<h3>Fast Transfer</h3>
</div>

<div class="feature">
<img src="https://cdn-icons-png.flaticon.com/512/3064/3064197.png">
<h3>Secure System</h3>
</div>

<div class="feature">
<img src="https://cdn-icons-png.flaticon.com/512/2920/2920277.png">
<h3>Analytics</h3>
</div>

</div>
</div>

<!-- SERVICES -->
<div class="services">
<h2>Our Services</h2>

<div class="service-box">

<div class="service">
<img src="https://cdn-icons-png.flaticon.com/512/2331/2331966.png">
<h3>Money Transfer</h3>
</div>

<div class="service">
<img src="https://cdn-icons-png.flaticon.com/512/891/891419.png">
<h3>Card Services</h3>
</div>

<div class="service">
<img src="https://cdn-icons-png.flaticon.com/512/3135/3135679.png">
<h3>Investment</h3>
</div>

</div>
</div>

<%@include file="footer.jsp" %>

</body>
</html>