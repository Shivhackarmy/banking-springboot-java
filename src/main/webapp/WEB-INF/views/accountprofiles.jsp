<!DOCTYPE html>
<%@page import="com.pro.pojo.Transaction"%>
<%@page import="java.util.List"%>
<%@page import="com.pro.pojo.UserInfo"%>
<%@page import="com.pro.pojo.Account"%>
<%@ page isELIgnored="false" %>


<%
UserInfo u = (UserInfo)session.getAttribute("user");

if(u == null){
    response.sendRedirect("login");
    return;
}

String username = u.getUsername();
String email = u.getEmail();
String dp = u.getDp();
%>

<html>
<head>
<title>Admin Dashboard</title>
<%@include file="style.jsp" %>
</head>

<body>

<!-- NAVBAR -->
<div class="navbar">
<h1>ProBank</h1>

<div class="user-info">

    <!-- BUTTONS -->
    <div class="top-buttons">
        <a href="admin" class="btn green">DashBoard</a>
    </div>

    <!-- USER DETAILS + DP -->
    <div class="profile-section">
        
        <div class="text-info">
            <h2><%= username %></h2>
            <p><%= email %></p>
        </div>

        <div class="dp-box">
            <img src="dp/<%= (dp!=null)?dp:"default1.png" %>">
        </div>

    </div>

</div>
</div>

<%@include file="header.jsp" %>

<div class="profile-card">

    <!-- LEFT USER -->
    <div class="profile-left">
        <h2>User Profile</h2>

        <div class="info"><span>Name</span> ${up.username}</div>
        <div class="info"><span>Email</span> ${up.email}</div>
        <div class="info"><span>Mobile</span> ${up.mobile}</div>
        <div class="info"><span>Gender</span> ${up.gender}</div>
        <div class="info"><span>Address</span> ${up.address}</div>
    </div>

    <!-- RIGHT ACCOUNT -->
    <div class="profile-right">
        <h2>Account Details</h2>

        <div class="info"><span>Account No</span> ${ap.accno}</div>
        <div class="info"><span>Type</span> ${ap.acctype}</div>
        <div class="info balance"><span>Balance</span>  ${ap.bal}</div>
    </div>

</div>

<h2 style="text-align: center;">Transaction Requests</h2>

<table border="1" width="100%" cellpadding="10">
<tr>
<th>ID</th>
<th>Amount</th>
<th>Type</th>
<th>Status</th>
<th>Action</th>
</tr>

<%
List<Transaction> tlist = (List<Transaction>)request.getAttribute("tlist");

if(tlist != null){
    for(Transaction t : tlist){

        if(t.getTstatus()!=null && t.getTstatus().equals("pending")){
%>

<tr>
<td><%= t.getTid() %></td>
<td><%= t.getTammount() %></td>
<td><%= t.getTtype() %></td>

<td style="color:orange;">pending</td>

<td>
<form action="approveTx" method="post">
<input type="hidden" name="tid" value="<%= t.getTid() %>">
<button>Approve</button>
</form>

<form action="rejectTx" method="post">
<input type="hidden" name="tid" value="<%= t.getTid() %>">
<button>Reject</button>
</form>
</td>

</tr>

<%
        }
    }
}
%>
</table>

<div class="card" style="text-align: center;">
<h2 style="text-align: center;">Admin Transaction</h2>
<%
String msg = request.getParameter("msg");
if(msg != null){
%>

<p style="text-align:center; font-weight:bold;
color:<%= "success".equals(msg) ? "green" : "red" %>;">
    <%= "success".equals(msg) ? "Transaction Successful" : "Insufficient Balance" %>
</p>

<% } %>
<form action="adminTransaction" method="post">

<input type="hidden" name="accno" value="${ap.accno}">

Amount <input type="number" name="amount" placeholder="Enter amount" required>

<select name="type">
<option value="deposit">Deposit</option>
<option value="withdraw">Withdraw</option>
</select>

<br><br>

<button style="background:blue;color:white;">Submit</button>

</form>
</div>

<h2 style="text-align: center;">Transaction History</h2>

<table border="1" width="100%" cellpadding="10">
<tr>
<th>ID</th>
<th>Amount</th>
<th>Type</th>
<th>Status</th>
<th>Date</th>
</tr>

<%
if(tlist != null){
for(Transaction t : tlist){
%>

<tr>
<td><%= t.getTid() %></td>
<td><%= t.getTammount() %></td>
<td><%= t.getTtype() %></td>

<td style="color:
<%= "pending".equals(t.getTstatus()) ? "orange" :
    "done".equals(t.getTstatus()) ? "green" : "red" %>">

<%= t.getTstatus() %>
</td>

<td><%= t.getTdate() %></td>
</tr>

<%
}
}

%>
</table>

<%@include file="footer.jsp" %>

<style>
.card{
    background:#f9f9f9;
    padding:20px;
    border-radius:10px;
    margin-bottom:20px;
    box-shadow:0px 0px 10px #ddd;
}

h2{
    margin-top:20px;
}
table{
    margin-bottom:25px;
}

form{
    margin-top:10px;
}
</style>
</body>
</html>