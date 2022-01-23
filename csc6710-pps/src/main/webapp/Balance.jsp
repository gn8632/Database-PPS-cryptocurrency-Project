<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
 <%@ page import="pack.*" %>
 
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Balance</title>
<link rel="stylesheet" href="stylesheet4.css">
<style>
body{
	background-color:gray;
}
h1{

color:white;
background-color:#11253E;
margin-right:53%;


}

h3{
color:white;
}

</style>
</head>
<body>
<% 
if(session.getAttribute("UserID")==null){
	response.sendRedirect("login.jsp");
} 
%>
<div class="top-nav">

<form action="Logout" method ="post">
<button id="right"><a href="Activity.jsp">Activity</a></button>
<button id="shade"><a href="Balance.jsp">Balance</a></button>
<button><a href="Follow.jsp">Follow</a></button>
<button><a href="Transactions.jsp">Transactions</a></button>
<button type="submit">Logout</button>
</form>


</div>
<div class="DepositANDwithdraw">

<h1 class="ok">Deposit money to your PPS account</h1>
<form action="Deposit" method="post">
  <h3> Enter deposit amount: 
  <input type="text" placeholder="$" name="deposit"> <h3>
  <button type="submit">Submit</button>
</form>


<h1 class="ok">Withdraw money from your PPS account</h1>
<form action="Withdraw" method="post">
  <h3> Enter withdraw amount: 
  <input type="text" placeholder="$" name="withdraw"> </h3>
  <button type="submit">Submit</button>
</form>

</div>

<h2 class="withdraw" style="color:blue;text-align:center;">${withdraw}</h2>
<h2 class="deposit" style="color:blue;text-align:center;">${deposit}</h2>

</html>