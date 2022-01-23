
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page import="pack.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Follow</title>
<link rel="stylesheet" href="stylesheet5.css">
<style>

body{
	background-color:gray;
}

.pp{

color:white;
float:left;
margin-right:20%;
margin-top:12px;
padding-bottom:12px;


}


table,th,td
  {
    border:1px solid blue;
  }
  
  th
  {
    background-color:#11253E;
    color:yellow;
  }

tr
{
  background-color:green;
  color:white;
}

.find{
width:30%;
float:left;

}



.commonuser{
float:right;


}

p{
padding:15px;
color:white;
background-color:#11253E;
border:1px solid blue;

}

h4{
color:white;

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
<div class="top-nav">

<form action="Logout" method ="post">
<button id="right"><a href="Activity.jsp">Activity</a></button>
<button><a href="Balance.jsp">Balance</a></button>
<button id="shade2"><a href="Follow.jsp">Follow</a></button>
<button><a href="Transactions.jsp">Transactions</a></button>
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
    
     <sql:query var="follow"   dataSource="${DB}">
        SELECT * FROM testdb.follow where FollowingEmail="${para}"    
    </sql:query>
    
    <div class="pp" >
    <table  border="1" cellpadding="5">
    
    
    <tr>
    <th colspan="2"><h2>User You Following</h2></th>
    </tr>
    <tr>
    <th>User ID</th>
    <th>Status</th>
    </tr>
     <c:forEach var="user" items="${follow.rows}">
                <tr>
                    <td><c:out value="${user.FollowerEmail}" /></td>
                    <td>
                        <form action="Unfollow" method="post">
                                 <input type="hidden"  name="unfollowing" value="${user.FollowerEmail}">
                                 <input type="submit" value="unfollow" name="submit" >
                        </form>
                    </td>

                </tr>
            </c:forEach>
    
    </table>
    
  
    
    
    </div>
    
   
<div class="find">

<form action="Search" method="post">
  <input type="text" placeholder="Search people to follow" name="search">
  <button type="submit">Submit</button>
</form>

		<br/><br/>
        <table border="1" cellpadding="5"  position="center">
            
            <c:forEach var="people" items="${listPeople}">
                <tr>
                    <td>
                    <c:out value="${people.firstname}  " />
                   
                    <c:out value="${people.lastname}" />
                    </td>
              
                    <td>
                    &nbsp;&nbsp;&nbsp;
                    <c:out value="${people.email}" />
                    </td>
                    
                    <td>
                    &nbsp;&nbsp;&nbsp;
                    <c:set var = "email" value="${people.email}"/>
                    <%
                    pack.PeopleDAO dao = new pack.PeopleDAO(); 
                                        String followeremail=(String)pageContext.getAttribute("email");
                                        String followingemail=(String)(session.getAttribute("UserID"));
                                        if(!dao.isUserFollowing(followingemail, followeremail)){
                    %>
                    	<form action="Follow" method="post">
                                 <input type="hidden"  name="following" value="${people.email}">
                                 <input type="submit" value="follow" name="submit" >
                        </form>
                   <%  }
                    else{%>
                    	<form action="Unfollow" method="post">
                                 <input type="hidden"  name="unfollowing" value="${people.email}">
                                 <input type="submit" value="unfollow" name="submit" >
                        </form>
                   <%  }
                    %>
                             
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <br/><br/><br/></br>
        
    <h2 class="message" style="color:blue;text-align:left;">${message}</h2>
    <h2 class="follow" style="color:#012B22;text-align:left;">${Follow}</h2>
    <h2 class="unfollow" style="color:blue;text-align:left;">${Unfollow}</h2>
    <h2 class="emptysearch" style="color:blue;text-align:left;">${emptysearch}</h2>
      
    </div> 

     
 
    
    
      
  
    
   
    <div class="commonuser">
    
      <sql:query var="user"   dataSource="${DB}">
        SELECT * FROM testdb.user where UserID <> 'root' and UserID <> 'chaseBank';   
    </sql:query>
    
	    
	<form action="commonUser" method="post">
	<p>
	Select  user A:
	<select name="UserA" >
	            <c:forEach var="u" items="${user.rows}">
	            <option value="${u.UserID}"><c:out value="${u.UserID}" /></option>
	            </c:forEach>
	</select>
	<br><br>
	Select  user B:
	<select name="UserB" >
	            <c:forEach var="u" items="${user.rows}">
	            <option value="${u.UserID}"><c:out value="${u.UserID}" /></option>
	            </c:forEach>
	</select>
	<br/><br/>
	
	 <button type="submit">Compare</button>
	</p>

	
	</form>
	
    <h2 style="color:blue;
    text-decoration:underline;
	text-decoration-style: wavy;
	text-decoration-color: purple;">${common2}</h2>
	<h4>
   <c:forEach var="people" items="${common}">
     <c:out value="${people}  " />
     <br>
   </c:forEach>
   </h4>
   
   
    </div>
    
    
      

</html>