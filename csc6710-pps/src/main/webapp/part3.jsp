<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
 <%@ page import="pack.*" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="stylesheet7.css">
<style>

body{
background-color:gray;

}

h3{
color:blue;
text-align:left;
text-decoration:underline;
text-decoration-style: wavy;
text-decoration-color: purple;

}

p{

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
<a style="color:red;text-align:center;" href="Activity.jsp">Go to The Activity Page</a>
<div class= "part3">
  
  <h3>${Fbuyer2}</h3>
  <p>
   <c:forEach var="people" items="${Fbuyer}">
     <c:out value="${people}  " />
     <br>
   </c:forEach>
   </p>
   
   <h3>${Bbuy2}</h3>
   <p>
   <c:forEach var="people" items="${Bbuy}">
     <c:out value="${people}  " />
     <br>
   </c:forEach>
   </p>
   
   <h3>${Bbuyer2}</h3>
   <p>
   <c:forEach var="people" items="${Bbuyer}">
     <c:out value="${people}  " />
     <br>
   </c:forEach>
   </p>
   
   <h3 >${Pusers2}</h3>
   <p>
   <c:forEach var="people" items="${Pusers}">
     <c:out value="${people}  " />
     <br>
   </c:forEach>
   </p>
   
   <h3>${Nsell2}</h3>
   <p>
   <c:forEach var="people" items="${Nsell}">
     <c:out value="${people}  " />
     <br>
   </c:forEach>
   </p>
   
   <h3>${Nbuy2}</h3>
   <p>
   <c:forEach var="people" items="${Nbuy}">
     <c:out value="${people}  " />
     <br>
   </c:forEach>
   </p>
   
   <h3>${Lusers2}</h3>
   <p>
   <c:forEach var="people" items="${Lusers}">
     <c:out value="${people}  " />
     <br>
   </c:forEach>
   </p>
   
   <h3>${Iusers2}</h3>
   <p>
   <c:forEach var="people" items="${Iusers}">
     <c:out value="${people}  " />
     <br>
   </c:forEach>
   </p>
   
   <h3>${Stat2}</h3>
   <p>
   <c:forEach var="people" items="${Stat}">
     <c:out value="${people}  " />
     <br>
   </c:forEach>
   </p>

</div>
</body>
</html>