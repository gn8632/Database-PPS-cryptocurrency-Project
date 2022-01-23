<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
 <%@ page import="pack.*" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Transaction History</title>
<link rel="stylesheet" href="stylesheet6.css">
</head>
<body>
<% 
String para = (String)session.getAttribute("UserID");
if(session.getAttribute("UserID")==null){
	response.sendRedirect("login.jsp");
} 
%>
<div class="top-nav">

<form action="Logout" method ="post">
<button id="right"><a href="Activity.jsp">Activity</a></button>
<button><a href="Balance.jsp">Balance</a></button>
<button><a href="Follow.jsp">Follow</a></button>
<button id="shade3"><a href="Transactions.jsp">Transactions</a></button>
<button type="submit">Logout</button>
</form>

</div>

 <sql:setDataSource
        var="DB"
        driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/testdb"
        user="root" password="root"
    />
   
    <c:set var="para" value="<%=para %>" scope="session"  />
    
    <sql:query var="transation"   dataSource="${DB}">
        SELECT * FROM testdb.transations where 
            (`To`="${para}" and Transtype='deposit') 
            or (`From`="${para}" and Transtype='withdraw') 
            or (`To`="${para}" and Transtype='buy')
            or (`From`="${para}" and Transtype='sell')
            or (`From`="${para}" and Transtype='transfer')
            or (`To`="${para}" and Transtype='transfer')
            order by TransTime desc
            limit 15

    </sql:query>
    
    <sql:query var="user"   dataSource="${DB}">
        SELECT * FROM testdb.user where UserID="${para}"    
    </sql:query>
    
    <div class ="transation "align="center">

           <c:forEach var="u" items="${user.rows}">
            <p > Dollar Balance:${u.DollarBalance} <br>
                 PPS Balance:${u.PPSBalance}
             </p>
            </c:forEach>
        <table border="1" cellpadding="5">
            <tr> 
                <td colspan="8" align="center"> <b> All Transactions</b> </td>
            </tr>
            <tr>
                <th>transaction ID</th>
                <th>Time </th>
                <th>From</th>
                <th>To</th>
                <th>transaction Type</th>
                <th>Dollar Amount</th>
                <th>PPS Amount</th>
                <th>Price</th>
            </tr>
            <c:forEach var="user" items="${transation.rows}">
                <tr>
                    <td><c:out value="${user.TransID}" /></td>
                    <td><c:out value="${user.TransTime}" /></td>
                    <td><c:out value="${user.From}" /></td>
                    <td><c:out value="${user.To}" /></td>
                    <td><c:out value="${user.Transtype}" /></td>
                    <td><c:out value="${user.dollaramount}" /></td>
                    <td><c:out value="${user.ppsamount}" /></td>
                    <td><c:out value="${user.price}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    
    


</html>