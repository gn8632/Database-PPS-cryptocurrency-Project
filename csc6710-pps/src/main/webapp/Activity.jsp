<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
 <%@ page import="pack.*" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Activity</title>
<link rel="stylesheet" href="stylesheet3.css">

<style>
body{
	background-color:gray;
}


.top-nav{
	background-color:blue;
	margin-top:2%;
	margin-bottom:2%;
	
	padding:10px;
	
}


h1{

color:white;
background-color:#11253E;
margin-right:40%;


}

h3{
color:orange;

}

h4{
color:white;

}
.transactions{
		float:left;
		margin-right:5%;
}

.transactions a{
	color:red;
	background-color:yellow;
	text-decoration:none;
	font-size:24px;
}

.transactions a:hover {
	background-color:blue;
}

#confirmation{
margin-top:15%;

}

#confirmation h2{

color:blue;
font-size:35px;
font-family: "Times New Roman", Times, serif;


}



</style>


</head>
<body>
<% 
String para = (String)session.getAttribute("UserID");
if(session.getAttribute("UserID")==null){
	response.sendRedirect("login.jsp");
} 
%>

<c:set var="para" value="<%=para %>" scope="session"  />

    <sql:setDataSource
        var="DB"
        driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/testdb"
        user="root" password="root"
    />
     <sql:query var="user3"   dataSource="${DB}">
        SELECT * FROM testdb.follow where followingEmail="${para}"    
    </sql:query>
    
    <sql:query var="user"   dataSource="${DB}">
        SELECT * FROM testdb.pps;   
    </sql:query>

<div class="top-nav">

<form action="Logout" method ="post">
<button id="right"><a href="Activity.jsp">Activity</a></button>
<button><a href="Balance.jsp">Balance</a></button>
<button><a href="Follow.jsp">Follow</a></button>
<button><a href="Transactions.jsp">Transactions</a></button>
<button type="submit">Logout</button>
</form>

</div>
 
 
<div class="transactions">

 <c:forEach var="u" items="${user.rows}">
 <h3>Current PPS price = <c:out value="${u.price/1000000}" /></h3>           
 </c:forEach>
 

<form action="buy" method="post">
<h1>Buy PPS:</h1>
<input type="text" placeholder="PPS Amount" name="ppsamount">
<button type="submit">Buy PPS</button>
</form>


<form action="sell" method="post">
<h1>Sell PPS:</h1>
<input type="text" placeholder="PPS Amount" name="sellamount">
<button type="submit">Sell PPS</button>
 </form>
 
 
<form action="transfer" method="post">
<h1>Transfer PPS:</h1>
<h4>
Select a user:<br/>
<select name="UserA" >
            <c:forEach var="u" items="${user3.rows}">
            <option value="${u.FollowerEmail}"><c:out value="${u.FollowerEmail}" /></option>
            </c:forEach>
</select> <br><br>

Enter PPS amount: </br>
<input type="text" placeholder="Amount" name="pps">
</h4>
<button type="submit">Transfer</button>
</form>
 <br>
 
 
 <a href="partthree">Project Part Three</a>
  
 </div>




<div id="confirmation">

<h2>${buypps}</h2>
<h2>${sellpps}</h2>
<h2>${transfer}</h2>

</div>





</body>
</html>