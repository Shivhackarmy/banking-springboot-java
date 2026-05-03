<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Login - ProBank</title>

<style>

body{
font-family: 'Segoe UI', Arial, sans-serif;
background:linear-gradient(135deg,#6a11cb,#2575fc);
margin:0;
min-height:100vh;
display:flex;
justify-content:center;
align-items:center;
}

.register-box{
width:460px;
background:white;
padding:30px 40px;
border-radius:14px;
box-shadow:0 15px 35px rgba(0,0,0,0.25);
}


.register-box h2{
text-align:center;
margin-bottom:5px;
color:#2575fc;
font-size:26px;
}

.subtitle{
text-align:center;
color:#6b7280;
margin-bottom:25px;
font-size:14px;
}


table{
width:100%;
border-spacing:0 10px;
}

td{
padding:6px;
font-size:14px;
}


td:first-child{
font-weight:600;
color:#374151;
width:40%;
}


width:100%;
padding:10px;
border-radius:8px;
border:1px solid #d1d5db;
font-size:14px;
transition:0.3s;
background:#f9fafb;
}

input:focus,select:focus{
border-color:#2563eb;
background:white;
box-shadow:0 0 0 2px rgba(37,99,235,0.15);
outline:none;
}


input[type="checkbox"]{
width:auto;
margin-right:6px;
}


button{
width:100%;
padding:12px;
background:linear-gradient(135deg,#6a11cb,#2575fc);
color:white;
border:none;
border-radius:25px;
font-size:16px;
cursor:pointer;
margin-top:10px;
transition:0.3s;
}

button:hover{
transform:scale(1.03);
box-shadow:0 5px 15px rgba(0,0,0,0.2);
}


.login-link{
text-align:center;
margin-top:18px;
font-size:14px;
}

a{
text-decoration:none;
color:#2575fc;
font-weight:600;
}

a:hover{
text-decoration:underline;
}

</style>

</head>

<body>

<div class="register-box">

<h2>Login Account</h2>
<p class="subtitle">Login your ProBank account</p>
<%
String msg=(String)request.getAttribute("msg");
if(msg!=null)
	out.print(msg);
%>
<form action="checkUser" method="post">

<table>

<tr>
<td>Email</td>
<td>
<input type="email" name="email" required>
</td>
</tr>

<tr>
<td>Password</td>
<td>
<input type="password" name="password" required>
</td>
</tr>


<tr>

<td colspan="2">

<button type="submit">

Login

</button>

</td>

</tr>

</table>

</form>


<div class="login-link">

Create New Account?

<a href="reg">

Register Here

</a>

</div>


</div>

</body>

</html>