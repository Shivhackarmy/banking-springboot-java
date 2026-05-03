<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
<style>
*{margin:0;padding:0;box-sizing:border-box;font-family:'Segoe UI';}
body{background:#f4f7fb;}

/* NAVBAR */
.navbar{
display:flex;
justify-content:space-between;
padding:15px 40px;
background:white;
box-shadow:0 2px 10px rgba(0,0,0,0.1);
}

.navbar h1{color:#2563eb;}
.navbar ul{display:flex;gap:20px;list-style:none;}
.navbar li{cursor:pointer;font-weight:500;}

/* HERO */
.hero{
display:flex;
align-items:center;
justify-content:space-between;
padding:60px;
background:linear-gradient(135deg,#2563eb,#1e3a8a);
color:white;
}

.hero-text{max-width:500px;}
.hero h1{font-size:50px;margin-bottom:20px;}
.hero img{width:420px;}

/* BUTTON */
.btn{
padding:12px 25px;
background:white;
color:#2563eb;
border:none;
border-radius:25px;
cursor:pointer;
}

/* FEATURES */
.features{
padding:60px;
text-align:center;
}

.feature-box{
display:flex;
gap:20px;
margin-top:30px;
}

.feature{
flex:1;
background:white;
padding:20px;
border-radius:10px;
box-shadow:0 5px 15px rgba(0,0,0,0.1);
}

.feature img{
width:70px;
margin-bottom:10px;
}

/* SERVICES */
.services{
padding:60px;
background:#e0e7ff;
text-align:center;
}

.service-box{
display:flex;
gap:20px;
margin-top:30px;
}

.service{
flex:1;
background:white;
padding:20px;
border-radius:10px;
}

.service img{width:80px;margin-bottom:10px;}

/* APP PREVIEW */
.app{
display:flex;
justify-content:space-between;
align-items:center;
padding:60px;
}

.app img{
width:300px;
}

/* TESTIMONIAL */
.testimonial{
padding:60px;
background:#111827;
color:white;
text-align:center;
}

/* FOOTER */
.footer{
padding:30px;
text-align:center;
background:#020617;
color:white;
}

.form-container {
    width: 400px;
    margin: 40px auto;
    padding: 25px;
    background: #f9f9f9;
    border-radius: 10px;
    box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
}

.form-container h2 {
    text-align: center;
    margin-bottom: 20px;
}

.form-container label {
    display: block;
    margin-top: 10px;
    font-weight: bold;
}

.form-container input,
.form-container select {
    width: 100%;
    padding: 8px;
    margin-top: 5px;
    border-radius: 5px;
    border: 1px solid #ccc;
}

.form-container button {
    width: 100%;
    margin-top: 20px;
    padding: 10px;
    background: green;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.form-container button:hover {
    background: darkgreen;
}


/* 🔥 SECTION TITLE */
.table-container h2{
    margin-bottom:15px;
    font-size:22px;
    color:#1e293b;
    text-align:center;
}

/* 🔥 CARD EFFECT */
.table-container{
    width:95%;
    margin:30px auto;
    padding:20px;
    background:white;
    border-radius:12px;
    box-shadow:0 5px 20px rgba(0,0,0,0.08);
}

/* 🔥 TABLE MODERN */
table{
    width:100%;
    border-collapse:collapse;
    overflow:hidden;
    border-radius:10px;
}

/* HEADER */
th{
    background:linear-gradient(135deg,#2563eb,#1e3a8a);
    color:white;
    padding:14px;
    font-size:14px;
    text-transform:uppercase;
}

/* BODY */
td{
    padding:12px;
    border-bottom:1px solid #eee;
    font-size:14px;
}

/* ROW EFFECT */
tr:hover{
    background:#f1f5f9;
    transition:0.2s;
}

/* 🔥 STATUS COLORS */
td:nth-child(6){
    font-weight:bold;
}

/* 🔥 BUTTONS */
button{
    padding:6px 12px;
    border:none;
    border-radius:6px;
    font-size:13px;
    cursor:pointer;
    transition:0.2s;
}

/* BUTTON COLORS */
button:hover{
    transform:scale(1.05);
}

.btn-green{ background:#22c55e; color:white; }
.btn-red{ background:#ef4444; color:white; }
.btn-orange{ background:#f59e0b; color:white; }
.btn-blue{ background:#3b82f6; color:white; }
.btn-purple{ background:#9333ea; color:white; }

/* 🔥 GAP BETWEEN BUTTONS */
td form{
    margin:3px;
    display:inline-block;
}

/* 🔥 BETTER ALIGNMENT */
td, th{
    text-align:center;
}
.table-container{
    animation:fadeIn 0.5s ease;
}

@keyframes fadeIn{
    from{opacity:0; transform:translateY(10px);}
    to{opacity:1; transform:translateY(0);}
}
.dashboard {
text-align: center;
}

.navbar{
    display:flex;
    justify-content:space-between;
    align-items:flex-start;
    padding:15px 30px;
}

/* RIGHT SIDE */
.user-info{
    display:flex;
    flex-direction:column;
    align-items:flex-end;
}

/* BUTTONS */
.top-buttons{
    display:flex;
    gap:10px;
    margin-bottom:10px;
}

.btn{
    padding:8px 15px;
    border-radius:5px;
    text-decoration:none;
    color:white;
    border:none;
    cursor:pointer;
}

.green{ background:green; }
.red{ background:red; }
.gray{ background:#444; }

/* PROFILE SECTION */
.profile-section{
    display:flex;
    align-items:center;
    gap:15px;
}

/* TEXT */
.text-info{
    text-align:right;
}

.text-info h2{
    margin:0;
}

.text-info p{
    margin:0;
    font-size:14px;
}

/* DP */
.dp-box img{
    width:70px;
    height:70px;
    border-radius:50%;
    object-fit:cover;
}

/* 🔥 DARK OVERLAY */
.overlay{
    display:none;
    position:fixed;
    top:0;
    left:0;
    width:100%;
    height:100%;
    background:rgba(0,0,0,0.5);
    backdrop-filter: blur(5px);
    z-index:1000;
}

/* 🔥 MODAL BOX */
.modal{
    display:none;
    position:fixed;
    top:50%;
    left:50%;
    transform:translate(-50%,-50%) scale(0.9);
    background:white;
    padding:30px;
    border-radius:12px;
    width:320px;
    box-shadow:0 10px 30px rgba(0,0,0,0.3);
    z-index:1001;
    text-align:center;
    transition:0.3s ease;
}

/* ANIMATION */
.modal.active{
    transform:translate(-50%,-50%) scale(1);
}

/* TITLE */
.modal h2{
    margin-bottom:15px;
}

/* INPUT */
.modal input[type="file"]{
    margin:15px 0;
    padding:8px;
    width:100%;
}

/* BUTTONS */
.modal-buttons{
    display:flex;
    justify-content:space-between;
    gap:10px;
}

.btn.save{
    background:green;
}

.btn.cancel{
    background:red;
}
/* 🔥 PROFILE CARD */
.profile-card{
    width:95%;
    margin:30px auto;
    display:flex;
    gap:20px;
}

/* LEFT & RIGHT BOX */
.profile-left, .profile-right{
    flex:1;
    background:white;
    padding:25px;
    border-radius:12px;
    box-shadow:0 5px 20px rgba(0,0,0,0.08);
}

/* HEADINGS */
.profile-left h2,
.profile-right h2{
    margin-bottom:20px;
    color:#1e293b;
}

/* INFO ROW */
.info{
    display:flex;
    justify-content:space-between;
    padding:10px 0;
    border-bottom:1px solid #eee;
    font-size:15px;
}

/* LABEL */
.info span{
    color:#64748b;
    font-weight:500;
}

/* BALANCE SPECIAL */
.balance{
    font-size:18px;
    font-weight:bold;
    color:#16a34a;
}

/* HOVER EFFECT */
.profile-left:hover,
.profile-right:hover{
    transform:translateY(-3px);
    transition:0.3s;
}
.profile-card{
    animation:fadeIn 0.4s ease;
}

.card{
    width:90%;
    margin:30px auto;
    padding:25px;
    background:white;
    border-radius:12px;
    box-shadow:0 5px 20px rgba(0,0,0,0.1);
    text-align:center;
}

.card input, .card select{
    padding:10px;
    margin:10px;
    border-radius:5px;
    border:1px solid #ccc;
}

.btn-blue{
    background:#2563eb;
    color:white;
    padding:10px 20px;
    border:none;
    border-radius:5px;
}
.info{
    display:flex;
    justify-content:space-between;
    padding:10px 0;
    border-bottom:1px dashed #ddd;
}
.modal input[type="password"]{
    width:100%;
    padding:8px;
    margin-top:8px;
    border-radius:5px;
    border:1px solid #ccc;
}


</style>
</body>
</html>