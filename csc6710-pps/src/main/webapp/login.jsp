<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PPS Login</title>
<link rel="stylesheet" href="stylesheet1.css">
</head>
<body>

<h1>PPS LOGIN</h1>

<div class="login">
	<form action="LogIn" method="POST">
	
	
	
	   <label for="emailaddress"><b>E-mail:&emsp;</b></label>
      <input type="text" placeholder="Enter E-mail" name="emailaddress" required><br><br>

      <label for="password"><b>Password:</b></label>
      <input type="password" placeholder="Enter Password" name="password" required><br><br>
        
      <button type="submit" onclick="myFunction2()">Login</button>
      <button type="reset">Clear</button>
      
       
      
      </form>
      <br>
     
     <a style="color:yellow;text-align:center;" href="register.jsp">Register Now</a>
            
       </div>
       
     
<div class="initialization">
<form action="Initialize" method ="post">
<button type="submit" onclick="myFunction()">Initialize Database</button>
</form>
</div>

<h2 style="color:red;text-align:center;">${Warning}</h2>
<h2 style="color:red;text-align:center;">${confermation}</h2>



</body>
</html>