<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="com.pro.pojo.UserInfo"%>
<%@page import="com.pro.pojo.Account"%>

<%
UserInfo u = (UserInfo)session.getAttribute("user");

if(u == null || !"ADMIN".equals(u.getRole())){
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
        <a href="regadmin" class="btn green">Open New Account</a>
        <button onclick="openPopup()" class="btn gray">Edit Profile</button>
        <a href="logout" class="btn red">Logout</a>
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
<!-- OVERLAY -->
<div id="popupOverlay" class="overlay" onclick="closePopup()"></div>

<!-- POPUP -->
<div id="editPopup" class="modal">

    <h2>Edit Profile</h2>

    <form id="editForm" action="updateProfile" method="post" enctype="multipart/form-data">
        
        <input type="text" name="username" value="<%= username %>" hidden>

        <label>Profile Photo</label>
        <input type="file" name="dp" required>

        <div class="modal-buttons">
            <button type="button" onclick="submitForm()" class="btn save">Save</button>
            <button type="button" onclick="closePopup()" class="btn cancel">Cancel</button>
        </div>

    </form>

</div>



<%@include file="header.jsp" %>

<%@page import="java.util.List"%>
<%@page import="com.pro.pojo.UserInfo"%>

<%
List<UserInfo> users = (List<UserInfo>)request.getAttribute("users");
%>

<div class="table-container">
<h2 style="text-align:center;">All Users</h2>

<table border="1" width="100%" cellpadding="10">
<tr>
<!--<th>ID</th>-->
<th>UID</th>
<th>Name</th>
<th>Email</th>
<th>Gender</th>
<th>Address</th>
<th>Action</th>
</tr>

<%
if(users != null){
for(UserInfo user : users){
%>
<tr>
<%if(user.getRole().equals("CUSTOMER") || user.getRole().equals("block")){ %>
<td><%= user.getUid() %></td>
<td><%= user.getUsername() %></td>
<td><%= user.getEmail() %></td>
<td><%= user.getGender() %></td>
<td><%= user.getAddress() %></td>
<%if(user.getRole().equals("CUSTOMER")){ %>
<td>
<form action="userStatus" method="post" style="display:inline;">
<input type="hidden" name="uid" value="<%= user.getUid() %>">
<input type="hidden" name="action" value="block">
<button style="background:red;color:white;">Block</button>
</form>
</td>
<%}
else{%>
<td>
<form action="userStatus" method="post" style="display:inline;">
<input type="hidden" name="uid" value="<%= user.getUid() %>">
<input type="hidden" name="action" value="unblock">
<button style="background:green;color:white;">UnBlock</button>
</form>
</td>
<%} 
}%>
</tr>
<%
}
}else{
%>

<tr>
<td colspan="4">No Users Found</td>
</tr>

<%
}
%>

</table>
</div>

<%
List<Account> list = (List<Account>)request.getAttribute("accounts");
%>

<div class="table-container">
<h2 style="text-align:center;">Account Requests</h2>

<table border="1" width="100%" cellpadding="10">
<tr>
<th>UID</th>
<th>ACCID</th>
<th>Name</th>
<th>Type</th>
<th>Balance</th>
<th>Status</th>
<th>Action</th>
</tr>

<%
if(list != null){
for(Account acc : list){
String status = acc.getAccstatus();
%>

<tr>
<td><%= acc.getUid() %></td>
<td><%= acc.getAccno() %></td>
<td><%= acc.getAccname() %></td>
<td><%= acc.getAcctype() %></td>
<td> <%= acc.getBal() %></td>

<td>
<%= (status == null) ? "request" : status %>
</td>

<td>

<%-- 🔵 NEW REQUEST --%>
<% if(status == null || status.equals("request")){ %>

<form action="updateStatus" method="post" style="display:inline;">
<input type="hidden" name="accno" value="<%= acc.getAccno() %>">
<input type="hidden" name="action" value="approve">
<button style="background:green;color:white;">Approve</button>
</form>

<form action="updateStatus" method="post" style="display:inline;">
<input type="hidden" name="accno" value="<%= acc.getAccno() %>">
<input type="hidden" name="action" value="reject">
<button style="background:red;color:white;">Reject</button>
</form>

<% } %>

<%-- 🟢 APPROVED --%>
<% if("active".equals(status)){ %>

<form action="updateStatus" method="post" style="display:inline;">
<input type="hidden" name="accno" value="<%= acc.getAccno() %>">
<input type="hidden" name="action" value="freeze">
<button style="background:orange;color:white;">Freeze</button>
</form>


<% } %>

<%-- 🔴 FROZEN --%>
<% if("freeze".equals(status)){ %>

<form action="updateStatus" method="post">
<input type="hidden" name="accno" value="<%= acc.getAccno() %>">
<input type="hidden" name="action" value="unfreeze">
<button style="background:blue;color:white;">Unfreeze</button>
</form>

<% } %>

<%-- 🟡 UNFREEZE REQUEST --%>
<% if("unfreeze_requested".equals(status)){ %>

<form action="updateStatus" method="post">
<input type="hidden" name="accno" value="<%= acc.getAccno() %>">
<input type="hidden" name="action" value="unfreeze_request">
<button style="background:purple;color:white;">Unfreeze</button>
</form>

<% } %>

<%-- ❌ OTHER STATUS --%>
<% if("deactive".equals(status) || "block".equals(status)){ %>
<%= status %>
<% } %>

</td>
</tr>

<%
}
}else{
%>

<tr>
<td colspan="6" style="text-align:center;">No Accounts Data Found</td>
</tr>

<%
}
%>

</table>
</div>

<%
List<Account> activeAccounts = (List<Account>)request.getAttribute("activeAccounts");
%>

<div class="table-container">
<h2 style="text-align:center;">Active Accounts</h2>

<table border="1" width="100%" cellpadding="10">
<tr>
<th>UID</th>
<th>Account No</th>
<th>Name</th>
<th>Type</th>
<th>Balance</th>
<th>Action</th>
</tr>

<%
if(activeAccounts != null){
for(Account acc : activeAccounts){
%>

<tr>
<td><%= acc.getUid() %></td>
<td><%= acc.getAccno() %></td>
<td><%= acc.getAccname() %></td>
<td><%= acc.getAcctype() %></td>
<td><%= acc.getBal() %></td>

<td>
<form action="accountprofiles" method="post" style="display:inline;">
<input type="hidden" name="uid" value="<%= acc.getUid() %>">
<button style="background:green;color:white;">User_Profile</button>
</form>
</td>

</tr>

<%
}
}else{
%>

<tr>
<td colspan="4">No Active Accounts</td>
</tr>

<%
}
%>

</table>
</div>

<%@include file="footer.jsp" %>

<script>
function openPopup(){
    document.getElementById("editPopup").style.display="block";
    document.getElementById("popupOverlay").style.display="block";

    setTimeout(()=>{
        document.getElementById("editPopup").classList.add("active");
    },10);
}

function closePopup(){
    document.getElementById("editPopup").classList.remove("active");

    setTimeout(()=>{
        document.getElementById("editPopup").style.display="none";
        document.getElementById("popupOverlay").style.display="none";
    },200);
}

function submitForm(){

    var form = document.getElementById("editForm");
    var data = new FormData(form);

    fetch("updateProfile", {
        method: "POST",
        body: data
    })
    .then(res => res.text())
    .then(res => {

        if(res === "success"){
            alert("Profile Updated");
            location.reload();
        } else {
            alert("Error updating profile");
        }

    });
}
</script>

</body>
</html>