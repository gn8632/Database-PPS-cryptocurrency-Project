<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" href="stylesheet2.css">

</head>
<body>


<h1>PLEASE REGISTER</h1></br></br>

<div class="register">

	<form action="SignUp" method ="POST">
	
		
	 <label for="firstname"><b>First Name:</b></label>
      <input type="text" placeholder="Enter First Name" name="firstname" required>&emsp;&emsp;
      
       <label for="lastname"><b>Last Name:</b></label>
      <input type="text" placeholder="Enter Last Name" name="lastname" required><br/><br/>
      
       <label for="birthday"><b>Birth Date:&nbsp;</b></label>
      <input type="date" name="birthday" required> &emsp;&emsp;&nbsp;&emsp;
      
       <label for="address"><b>Address:&emsp;&nbsp;</b></label>
      <input type="text" placeholder="Enter Address" name="address" required><br/><br/>
      
       <label for="city"><b>City:</b></label>&emsp;&emsp;&emsp;
      <input type="text" placeholder="Enter City" name="city" required>&emsp;&emsp;
      
       <label for="state"><b>State:&emsp;&emsp;&nbsp;</b></label>
      <input type="text" placeholder="Enter State" name="state" required><br/><br/>
      
      <label for="zipcode"><b>ZIP Code:&emsp;</b></label>
      <input type="text" placeholder="ZIP Code" name="zipcode" required>&emsp;&emsp;
      
	
      <label for="emailaddress"><b>E-mail:&emsp;&nbsp;</b></label>
      <input type="email" placeholder="Enter E-mail" name="emailaddress" required><br/><br/>

      <label for="password"><b>Password:</b></label>&ensp;&nbsp;
      <input type="password" placeholder="Enter Password" name="password" required>&emsp;&emsp;
      
      <label for="repeatpassword"><b>Password:&nbsp;</b></label>
      <input type="password" placeholder="Repeat Password" name="repeatpassword" required><br><br>
        
      <button type="submit">Register</button>
                  
       <button type="reset">Clear</button>
       
       
                
      </form>
      <br/>
      <a style="color:yellow;text-align:center;" href="login.jsp">Login Now</a>
      
       </div>
       <h2 style="color:Red;">${message }</h2>
       <h2 style="color:Red;">${message2 }</h2>

 
    

</body>
</html>