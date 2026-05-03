<!DOCTYPE html>
<%@page import="com.pro.pojo.UserInfo"%>
<%
UserInfo u = (UserInfo)session.getAttribute("user");

if(u == null){
    response.sendRedirect("login");
    return;
}
%>
<html>

<head>
<meta charset="UTF-8">
<title>Register - ProBank</title>

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

<h2>Create Account</h2>
<p class="subtitle">Open your ProBank account</p>
<%
String msg=(String)request.getAttribute("msg");
if(msg!=null)
	out.print(msg);
%>
<form action="openByadmin" method="post">

<table>

<tr>
<td>Full Name</td>
<td>
<input type="text" name="username" required>
</td>
</tr>

<tr>
<td>Email</td>
<td>
<input type="email" name="email" placeholder="eg.:abc@gmail.com" required>
</td>
</tr>

<tr>
<td>Mobile</td>
<td>
<input type="text" name="mobile" maxlength="10" placeholder="+91">
</td>
</tr>

<tr>
<td>Gender</td>
<td>

<select name="gender">

<option selected="selected" disabled="disabled">select Gender</option>
<option>Male</option>
<option>Female</option>

</select>

</td>
</tr>

<tr>
<td>Bank Branch</td>
<td>

<select name="branch">

<option selected="selected" disabled="disabled">select branch</option>
<option>Datoda</option>
<option>Simrol</option>
<option>Indore</option>
<option>Ujjain</option>
<option>Mhow</option>


</select>

</td>
</tr>

<tr>
<td>Date of Birth</td>
<td>
<input type="date" name="dob">
</td>
</tr>

<tr>
<td>Address</td>
<td>
<input type="text" name="address" placeholder="eg.:101, block, city">
</td>
</tr>

<tr>
<td>Account Type</td>
<td>

<select name="acctype">

<option selected="selected" disabled="disabled">select Type</option>
<option>Savings</option>
<option>Current</option>

</select>

</td>
</tr>

<tr>
<td>Initial Deposit</td>
<td>
<input type="number" name="bal" min="600" placeholder="Minimum 600" required>
</td>
</tr>

<tr>
<td>Pan Number</td>
<td>
<input type="text" name="pan" maxlength="10" placeholder="eg.:EUVPG5418L">
</td>
</tr>



<tr>

<td colspan="2">

<button type="submit">

Open Account

</button>

</td>

</tr>

</table>

</form>


<div class="login-link">


<a href="admin">

Home Dashboard

</a>

</div>


</div>

</body>

</html>