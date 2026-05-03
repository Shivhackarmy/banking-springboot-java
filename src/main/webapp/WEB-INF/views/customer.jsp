<!DOCTYPE html>

<%@page import="com.pro.pojo.Transaction"%>
<%@page import="java.util.List"%>
<%@page import="com.pro.pojo.UserInfo"%>

<%
UserInfo u = (UserInfo)session.getAttribute("user");

if(u == null || !"CUSTOMER".equals(u.getRole())){
response.sendRedirect("login");
return;
}

String username = u.getUsername();
String email = u.getEmail();
String dp = u.getDp();

com.pro.pojo.Account account = (com.pro.pojo.Account) request.getAttribute("account");

Transaction lastTx = (Transaction) request.getAttribute("lastTx");
boolean isPending = (lastTx != null && "pending".equals(lastTx.getTstatus()));

String status = (account != null) ? account.getAccstatus() : "";
String msg = request.getParameter("msg");
%>

<html lang="en">
<head>
<meta charset="UTF-8">
<title>ProBank</title>

<%@include file="style.jsp" %>

</head>

<body>

<div class="navbar">
<h1>ProBank</h1>
<div class="user-info">

<div class="top-buttons">
<button onclick="openPassPopup()" class="btn green">Change Password</button>
<button onclick="openPopup()" class="btn gray">Edit Profile</button>
<a href="logout" class="btn red">Logout</a>
</div>

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

<div id="popupOverlay" class="overlay" onclick="closePopup()"></div>

<div id="editPopup" class="modal">
<h2>Edit Profile</h2>

<form id="editForm" action="updateProfile" method="post" enctype="multipart/form-data">
<input type="text" name="username" value="<%= username %>" hidden>

<label>Profile Photo</label> <input type="file" name="dp" required>

<div class="modal-buttons">
<button type="button" onclick="submitForm()" class="btn save">Save</button>
<button type="button" onclick="closePopup()" class="btn cancel">Cancel</button>
</div>
</form>
</div>

<div id="passPopup" class="modal">
<h2>Change Password</h2>
<div id="passError" style="color:red; text-align:center;"></div>

<form id="passForm">
<label>Previous Password</label>
<input type="password" name="oldpass" required>

<label>New Password</label> <input type="password" name="newpass" required>

<div class="modal-buttons">
<button type="button" onclick="submitPassword()" class="btn save">Update</button>
<button type="button" onclick="closePassPopup()" class="btn cancel">Cancel</button>
</div>
</form>
</div>

<%@include file="header.jsp" %>

<div class="profile-card">

<div class="profile-left">
<h2>User Profile</h2>

<div class="info"><span>Name</span> <%= u.getUsername() %></div>
<div class="info"><span>Email</span> <%= u.getEmail() %></div>
<div class="info"><span>Mobile</span> <%= u.getMobile() %></div>
<div class="info"><span>Gender</span> <%= u.getGender() %></div>
<div class="info"><span>Address</span> <%= u.getAddress() %></div>
<div class="info"><span>Branch</span> <%= u.getBranch() %></div>
<div class="info"><span>DOB</span> <%= u.getDob() %></div>
<div class="info"><span>PAN</span> <%= u.getPan() %></div>
</div>

<div class="profile-right">
<h2>Account Details</h2>

<div class="info"><span>Account No</span> ${account.accno}</div>
<div class="info"><span>Type</span> ${account.acctype}</div>
<div class="info balance"><span>Balance</span> ${account.bal}</div>
<div class="info"><span>Status</span> ${account.accstatus}</div>
</div>

</div>

<% if("unfreeze_requested".equals(msg)){ %>

<p style="color:green;text-align:center;">Unfreeze request sent</p>
<% } %>

<% if("freeze".equals(status) || "unfreeze_requested".equals(status)){ %>

<div class="card" style="text-align:center;">
<h2>Account Frozen</h2>

<form action="requestUnfreeze" method="post">
<input type="hidden" name="accno" value="<%= account.getAccno() %>">

<% if("unfreeze_requested".equals(status)){ %> <button disabled>Request Already Sent</button>
<% } else { %> <button class="btn-red">Request Unfreeze</button>
<% } %>

</form>
</div>

<% } else { %>

<div class="card">
<h2 style="text-align:center;">Transaction Request</h2>

<% if(isPending){ %>

<p style="color:red;text-align:center;">Your last request is pending</p>
<% } %>

<form action="requestTransaction" method="post">

<input type="hidden" name="accno" value="<%= account.getAccno() %>">

<input type="number" name="amount" required <%= isPending?"disabled":"" %>>

<select name="type" <%= isPending?"disabled":"" %>>

<option value="deposit">Deposit</option>
<option value="withdraw">Withdraw</option>
</select>

<br><br>

<button class="btn-blue" <%= isPending?"disabled":"" %>>
<%= isPending?"Request Pending":"Send Request" %> </button>

</form>
</div>

<% } %>

<h2 style="text-align:center;">Transaction History</h2>

<table border="1" width="100%" cellpadding="10">
<tr>
<th>ID</th>
<th>Amount</th>
<th>Type</th>
<th>Status</th>
<th>Date</th>
</tr>

<%
List<Transaction> tlist = (List<Transaction>) request.getAttribute("tlist");

if(tlist != null){
for(Transaction t : tlist){
%>

<tr>
<td><%= t.getTid() %></td>
<td><%= t.getTammount() %></td>
<td><%= t.getTtype() %></td>

<td style="color:
<%= "pending".equals(t.getTstatus())?"orange":
"done".equals(t.getTstatus())?"green":"red" %>">

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

<script>
function openPopup(){
document.getElementById("editPopup").style.display="block";
document.getElementById("popupOverlay").style.display="block";
}

function closePopup(){
document.getElementById("editPopup").style.display="none";
document.getElementById("popupOverlay").style.display="none";
}

function openPassPopup(){
document.getElementById("passPopup").style.display="block";
document.getElementById("popupOverlay").style.display="block";
}

function closePassPopup(){
document.getElementById("passPopup").style.display="none";
document.getElementById("popupOverlay").style.display="none";
}

function submitPassword(){
var data = new FormData(document.getElementById("passForm"));

fetch("changePassword",{method:"POST",body:data})
.then(res=>res.text())
.then(res=>{
if(res==="success"){
alert("Password Changed Successfully");
closePassPopup();
}else{
document.getElementById("passError").innerText=res;
}
});
}
</script>

</body>
</html>
