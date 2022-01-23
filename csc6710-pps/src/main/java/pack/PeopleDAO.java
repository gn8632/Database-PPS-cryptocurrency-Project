package pack;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/PeopleDAO")
public class PeopleDAO {     
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public PeopleDAO() {

    }
	       

    protected void connect_func() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://localhost/","root","root");
        }
    }
    
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
         
    public void Initialize() throws SQLException {
    	String f0 = "SET FOREIGN_KEY_CHECKS = 0;";
        String f1 = "SET FOREIGN_KEY_CHECKS = 1;";
        
        String sql1="DROP DATABASE IF EXISTS testdb;";
        String sql1_2="CREATE DATABASE testdb;";
        String sql1_3="Use testdb;";
        
        String user="DROP TABLE IF EXISTS `USER`;";
        String follow="DROP TABLE IF EXISTS `FOLLOW`;";
        String transation="DROP TABLE IF EXISTS TRANSATIONS;";
        String pps="DROP TABLE IF EXISTS PPS;";
	
		String userTable="create table `USER`(\r\n"
				+ "`UserID` varchar(20) not null,\r\n"
				+ "`Password` varchar(20),\r\n"
				+ "`FirstName` varchar(10),\r\n"
				+ "`LastName` varchar(10),\r\n"
				+ "`DateOfBirth` date,\r\n"
				+ "`Address` varchar(100),\r\n"
				+ "`DollarBalance` double,\r\n"
				+ "`PPSBalance` double,\r\n"
				+ "primary key (`UserID`)\r\n"
				+ ");";	
		
		String followTable = "create table FOLLOW(\r\n"
				+ "FollowingEmail varchar(20),\r\n"
				+ "FollowerEmail varchar(20),\r\n"
				+ "primary key (`FollowingEmail`, `FollowerEmail`),\r\n"
				+ "foreign key (`FollowingEmail`) references `USER` (`UserID`),\r\n"
				+ "foreign key (`FollowerEmail`) references  `USER` (`UserID`)\r\n"
				+ ");";
		
		String transationTable="create table TRANSATIONS(\r\n"
				+ "TransID int not null auto_increment,\r\n"
				+ "TransTime datetime,\r\n"
				+ "`From` varchar(30),\r\n"
				+ "`To` varchar(30),\r\n"
				+ "Transtype varchar(40),\r\n"
				+ "dollaramount double,\r\n"
				+ "ppsamount double,\r\n"
				+ "price double,\r\n"
				+ "primary key(TransID),\r\n"
				+ "foreign key (`From`) references `USER` (`UserID`),\r\n"
				+ "foreign key (`To`) references  `USER` (`UserID`)\r\n"
				+ ")AUTO_INCREMENT = 1000;";
		
		String ppsTable="create table PPS (\r\n"
				+ "price double\r\n"
				+ ");";
		
		connect_func();
		statement=connect.createStatement();
		statement.executeUpdate(sql1);
		statement.executeUpdate(sql1_2);
		statement.executeUpdate(sql1_3);
		
		//drop tables.
		statement.executeUpdate(f0);
		
	    statement.executeUpdate(user);
	    statement.executeUpdate(follow);
	    statement.executeUpdate(transation);
	    statement.executeUpdate(pps);
	   
	    statement.executeUpdate(f1);
	    
	    //Create Tables
	    statement.executeUpdate(userTable);
	    statement.executeUpdate(followTable);
	    statement.executeUpdate(transationTable);
	    statement.executeUpdate(ppsTable);
	    
	    //Insert root User information
	    preparedStatement = connect
	  	          .prepareStatement("insert into  `USER` values (?,?,?,?,?,?,?,?)");
	  	      preparedStatement.setString(1,"root" );
	  	      preparedStatement.setString(2, "root123");
	  	      preparedStatement.setString(3, "Kakashi");
	  	      preparedStatement.setString(4, "Hatake");
	  	      preparedStatement.setString(5, "1995-08-21");	    	
	  	      preparedStatement.setString(6, "345 Database Road, Troy, MI 48083");
	  	      preparedStatement.setDouble(7, 0);
	  	      preparedStatement.setDouble(8, 1000000000000.00);
	  	      preparedStatement.executeUpdate();
	  	      
	  	    preparedStatement = connect
		  	          .prepareStatement("insert into  `USER` values (?,?,?,?,?,?,?,?)");
		  	      preparedStatement.setString(1,"chaseBank" );
		  	      preparedStatement.setString(2, "12345");
		  	      preparedStatement.setString(3, "Chase");
		  	      preparedStatement.setString(4, "Bank");
		  	      preparedStatement.setString(5, "0000-00-00");	    	
		  	      preparedStatement.setString(6, "3450 USA Road, Troy, MI 48083");
		  	      preparedStatement.setDouble(7, 0);
		  	      preparedStatement.setDouble(8, 0);
		  	      preparedStatement.executeUpdate();
	  	      
	    preparedStatement = connect
	    	          .prepareStatement("insert into PPS values (?)");
	    	  preparedStatement.setDouble(1, 1.00);	 
	    	  preparedStatement.executeUpdate(); 
	
	    statement.close();   	  
	    disconnect();
	    
    }
    
    public void registerUser(People people) throws SQLException {
    	String email= people.getEmail();
    	String password=people.getPassword();
    	String firstname= people.getFirstname();
    	String lastname=people.getLastname();
    	String dob=people.getDateOfBirth();
    	String address=people.getAddress();
    	
    	connect_func();
    	
    	preparedStatement = connect
    	          .prepareStatement("insert into  testdb.`USER` values (?,?,?,?,?,?,?,?)");
    	      preparedStatement.setString(1,email );
    	      preparedStatement.setString(2, password);
    	      preparedStatement.setString(3, firstname);
    	      preparedStatement.setString(4, lastname);
    	      preparedStatement.setString(5, dob);	    	
    	      preparedStatement.setString(6, address);
    	      preparedStatement.setDouble(7, 0.0);
    	      preparedStatement.setDouble(8, 0.0);
    	      preparedStatement.executeUpdate();
    	
    	statement.close();     	
    	disconnect();
    	
    }
    
    public boolean isEmailExists(String email) throws SQLException {
    	
    	connect_func();
    	statement = connect.createStatement();
    	resultSet = statement
    	          .executeQuery("SELECT COUNT(UserID) as count \r\n"
    	          		+ "FROM testdb.user \r\n"
    	          		+ "where userid='"+email+"';");
    	resultSet.next();
    	int total = resultSet.getInt(1);
	
    	resultSet.close();
        statement.close();  
    	disconnect();
    	return (total==1);	  	
    }
    
    public boolean isPasswordEmailEqual(String Email, String pass) throws SQLException {
    	connect_func();
    	statement = connect.createStatement();
    	resultSet = statement
    	          .executeQuery("SELECT COUNT(UserID) as number \r\n"
    	          		+ "FROM testdb.user \r\n"
    	          		+ "where userid='"+Email +"' and Password='"+pass+"';");
    	resultSet.next();
    	int total = resultSet.getInt(1);
    	
    	resultSet.close();
        statement.close();  
    	disconnect();
    	return (total==1);	 
    	
    }
    public List<People> listAllPeople(String name, String email2) throws SQLException {
    	
        List<People> listPeople = new ArrayList<People>(); 
        String [] firstLastName=name.split("\\s+");
        String firstname=firstLastName[0];
        String lastname=firstLastName[firstLastName.length-1];
        String sql="SELECT * FROM testdb.user where ( FirstName like '"+firstname+"%' or\r\n"
        		+ " LastName like '"+lastname+"%'\r\n"
        		+ " ) and (UserID <> 'chaseBank' and UserID <> 'root' and UserID <> '"+email2+"');";
        
        connect_func(); 
        statement = connect.createStatement();
        resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            
            String fname = resultSet.getString(3);
            String lname = resultSet.getString(4);
            String email = resultSet.getString(1);
             
            People people = new People(email, fname, lname);
            listPeople.add(people);
        }        
        resultSet.close();
        statement.close();         
        disconnect();        
        return listPeople;
    }
    
    public boolean isUserFollowing(String followingemail, String followeremail) throws SQLException {
    	
    	String sql="SELECT COUNT(FollowingEmail and FollowerEmail) as number     \r\n"
    			+ "FROM testdb.follow  \r\n"
    			+ " where FollowingEmail='"+followingemail+"' and  FollowerEmail='"+followeremail+"';";
    	connect_func(); 
        statement = connect.createStatement();
        resultSet = statement.executeQuery(sql);
        resultSet.next();
        int total = resultSet.getInt(1);
        resultSet.close();
        statement.close();
        disconnect(); 
    
		return (total==1);
    	
    }
    public void addFollower(String followingemail, String followeremail) throws SQLException {
		connect_func();
		String addF ="insert into  testdb.follow(FollowingEmail,FollowerEmail) values "
				+ "('"+followingemail+"','"+followeremail+"');";
		statement = connect.createStatement();
		statement.executeUpdate(addF);
		statement.close();
		disconnect();	   
	}
	
	public void removeFollower(String followingemail, String followeremail) throws SQLException {
		connect_func();
		String removeF ="Delete from testdb.follow where FollowingEmail='"+followingemail+"' and "
				+ "FollowerEmail='"+followeremail+"';";
		statement = connect.createStatement();
		statement.executeUpdate(removeF);
		statement.close();
		disconnect();
	}
	
	public void depositToPpsAccount(double amount, String email) throws SQLException {
		String f0 = "SET FOREIGN_KEY_CHECKS = 0;";
        String f1 = "SET FOREIGN_KEY_CHECKS = 1;";
        String damount="SELECT * FROM testdb.user where UserID='"+email+"';";
		connect_func();
		statement = connect.createStatement();
		
		resultSet = statement.executeQuery(damount);
        resultSet.next();
        double total = resultSet.getDouble(7);
        
        String Transation ="insert into  testdb.transations(TransTime,`From`,`To`,Transtype,dollaramount)"
				+ " values (CURTIME(),'chaseBank','"+email+"','deposit',"+amount+");";
		String update="update testdb.user set DollarBalance="+(amount+total)+" where UserID='"+email+"';";
        
		statement.executeUpdate(f0);
		statement.executeUpdate(Transation);
		statement.executeUpdate(update);
		statement.executeUpdate(f1);
		
		resultSet.close();
		statement.close();
		disconnect();	
	}
	public boolean withdrawToBank(double amount, String email) throws SQLException {
		boolean flag=false;
		
		String f0 = "SET FOREIGN_KEY_CHECKS = 0;";
        String f1 = "SET FOREIGN_KEY_CHECKS = 1;";
        String damount="SELECT * FROM testdb.user where UserID='"+email+"';";
        
        connect_func();
		statement = connect.createStatement();
		
		resultSet = statement.executeQuery(damount);
        resultSet.next();
        double total = resultSet.getDouble(7);
        if(total >= amount) {
        	String Transation ="insert into  testdb.transations(TransTime,`To`,`From`,Transtype,dollaramount)"
    				+ " values (CURTIME(),'chaseBank','"+email+"','withdraw',"+amount+");";
    		String update="update testdb.user set DollarBalance="+(total-amount)+" where UserID='"+email+"';";
            
    		statement.executeUpdate(f0);
    		statement.executeUpdate(Transation);
    		statement.executeUpdate(update);
    		statement.executeUpdate(f1);
    		flag=true;
        }
        resultSet.close();
		statement.close();
		disconnect();	
		
		return flag;
		
	}
	
public boolean buyPPs(String email, double amount) throws SQLException {
		
		boolean flag = false;
		
		String f0 = "SET FOREIGN_KEY_CHECKS = 0;";
        String f1 = "SET FOREIGN_KEY_CHECKS = 1;";
        String root="SELECT * FROM testdb.user where UserID='root';";
        String ppsPrice="SELECT * FROM testdb.pps;";
        String user="SELECT * FROM testdb.user where UserID='"+email+"';";
        
              
        connect_func();
		statement = connect.createStatement();
		
		//from root
		resultSet = statement.executeQuery(root);
        resultSet.next();
        double totalp = resultSet.getDouble(8);
        double rootd=resultSet.getDouble(7);
        resultSet.close();
        
        //from price table
        resultSet = statement.executeQuery(ppsPrice);
        resultSet.next();
        double price = resultSet.getDouble(1);
        resultSet.close();
        
        //from user dollar amount
        resultSet = statement.executeQuery(user);
        resultSet.next();
        double damount = resultSet.getDouble(7);
        double pamount = resultSet.getDouble(8);
        
        resultSet.close();
        
        double dollarSpentOnPPS=((price/1000000)*amount);
    	double currentPPSprice= ((1.0/999999)+price);
  
        
        if(totalp >= amount && dollarSpentOnPPS <= damount ) {
        	
        	String Transation ="insert into  testdb.transations(TransTime,`From`,`To`,Transtype,ppsamount, price)"
    				+ " values (CURTIME(),'root','"+email+"','buy',"+amount+","+(dollarSpentOnPPS)+");";
    		String updateUser="update testdb.user set DollarBalance="+(damount-dollarSpentOnPPS)+",PPSBalance="+(pamount+amount)+" where UserID='"+email+"';";
    		String updateRoot="update testdb.user set DollarBalance="+(rootd+dollarSpentOnPPS)+",PPSBalance="+(totalp-amount)+" where UserID='root';";
    		String updatePrice="update testdb.pps set price="+currentPPSprice+";";
    		
    		statement.executeUpdate(f0);
    		statement.executeUpdate(Transation);
    		statement.executeUpdate(updateUser);
    		statement.executeUpdate(updateRoot);
    		statement.executeUpdate(updatePrice);
    		statement.executeUpdate(f1);
    		flag=true;
        }
		statement.close();
		disconnect();
		return flag;
		
	}
public boolean sellPPs(String email, double amount) throws SQLException {
	
	boolean flag = false;
	
	String f0 = "SET FOREIGN_KEY_CHECKS = 0;";
    String f1 = "SET FOREIGN_KEY_CHECKS = 1;";
    String root="SELECT * FROM testdb.user where UserID='root';";
    String ppsPrice="SELECT * FROM testdb.pps;";
    String user="SELECT * FROM testdb.user where UserID='"+email+"';";
    
    connect_func();
	statement = connect.createStatement();
    
    	//from root
  		resultSet = statement.executeQuery(root);
          resultSet.next();
          double totalp = resultSet.getDouble(8);
          double rootd=resultSet.getDouble(7);
          resultSet.close();
          
          //from price table
          resultSet = statement.executeQuery(ppsPrice);
          resultSet.next();
          double price = resultSet.getDouble(1);
          resultSet.close();
          
          //from user dollar amount
          resultSet = statement.executeQuery(user);
          resultSet.next();
          double damount = resultSet.getDouble(7);
          double pamount = resultSet.getDouble(8);
          
          resultSet.close();
          
          double dollarSpentOnPPS= ((price/1_000_000)*amount);
      	double currentPPSprice= ((price-(1/(1_000_001))));
      	
      	
      	if(pamount >= amount && rootd >= dollarSpentOnPPS ) {
        	
        	String Transation ="insert into  testdb.transations(TransTime,`To`,`From`,Transtype,ppsamount, price)"
    				+ " values (CURTIME(),'root','"+email+"','sell',"+amount+","+(dollarSpentOnPPS)+");";
    		String updateUser="update testdb.user set DollarBalance="+(damount+dollarSpentOnPPS)+",PPSBalance="+(pamount-amount)+" where UserID='"+email+"';";
    		String updateRoot="update testdb.user set DollarBalance="+(rootd-dollarSpentOnPPS)+",PPSBalance="+(totalp+amount)+" where UserID='root';";
    		String updatePrice="update testdb.pps set price="+currentPPSprice+";";
    		
    		statement.executeUpdate(f0);
    		statement.executeUpdate(Transation);
    		statement.executeUpdate(updateUser);
    		statement.executeUpdate(updateRoot);
    		statement.executeUpdate(updatePrice);
    		statement.executeUpdate(f1);
    		flag=true;
        }
      	
		statement.close();
		disconnect();
		return flag;
	
	
}

public boolean transferPPS(String from, String to, double amount) throws SQLException {
	
	boolean flag=false;

	String f0 = "SET FOREIGN_KEY_CHECKS = 0;";
    String f1 = "SET FOREIGN_KEY_CHECKS = 1;";
	String fromUser="SELECT * FROM testdb.user where UserID='"+from+"';";
	String toUser="SELECT * FROM testdb.user where UserID='"+to+"';";
	
	 connect_func();
     statement = connect.createStatement();
	
	//user from
	resultSet = statement.executeQuery(fromUser);
    resultSet.next();
    double fromUserPPS = resultSet.getDouble(8);
    System.out.println(fromUserPPS);
    resultSet.close();
	
	//user to
    resultSet = statement.executeQuery(toUser);
    resultSet.next();
    double toUserPPS = resultSet.getDouble(8);
    System.out.println(toUserPPS);
    resultSet.close();
	
	if(fromUserPPS >= amount) {
		String Transation ="insert into  testdb.transations(TransTime,`From`,`To`,Transtype,ppsamount)"
				+ " values (CURTIME(),'"+from+"','"+to+"','transfer',"+amount+");";
		String updateUserFrom="update testdb.user set PPSBalance="+(fromUserPPS- amount)+" where UserID='"+from+"';";
		String updateUserTo="update testdb.user set PPSBalance="+(toUserPPS+amount)+" where UserID='"+to+"';";
		statement.executeUpdate(f0);
		statement.executeUpdate(Transation);
		statement.executeUpdate(updateUserFrom);
		statement.executeUpdate(updateUserTo);
		statement.executeUpdate(f1);
		flag=true;
	}
	
	statement.close();
	disconnect();
	return flag;
	
}
public ArrayList<String> Frequent_buyers() throws SQLException    {
    ArrayList<String> emailsOfFrequentBuyers = new ArrayList<String>();

   String db="use testdb;";
   String dropview="DROP VIEW IF EXISTS Frequent_buyers;";
   String view="CREATE VIEW Frequent_buyers AS\r\n"
   		+ "SELECT A.TO, count(A.ppsamount) as total\r\n"
   		+ "FROM transations as A\r\n"
   		+ "WHERE Transtype='buy'\r\n"
   		+ "group by A.TO;\r\n"
   		+ "";
   String fbuyer="SELECT A.To, total\r\n"
   		+ "from Frequent_buyers as A\r\n"
   		+ "group by A.To\r\n"
   		+ "having  A.total=( select max(total) from Frequent_buyers);\r\n"
   		+ "";
   connect_func();
   statement = connect.createStatement();
   statement.executeUpdate(db);
   statement.executeUpdate(dropview);
   statement.executeUpdate(view);
   resultSet = statement.executeQuery(fbuyer);
   while(resultSet.next()) {
	   emailsOfFrequentBuyers.add(resultSet.getString(1)+"   "+resultSet.getInt(2));
   }
   resultSet.close();
   statement.close();
   disconnect();
   return emailsOfFrequentBuyers;
   
   
    
}

public ArrayList<String> BiggestBuyAndBuyers(int option) throws SQLException    {
    ArrayList<String> biggest = new ArrayList<String>();
    String db="use testdb;";
    String dropview="DROP VIEW IF EXISTS Biggest_buy ;";
   String view="CREATE VIEW Biggest_buy AS\r\n"
   		+ "SELECT u.UserID, U.FirstName, u.LastName, \r\n"
   		+ "sum(A.ppsamount) as totalPPS,A.TransTime, max(A.ppsamount) as Biggestbuy\r\n"
   		+ "FROM transations as A , user as U\r\n"
   		+ "WHERE A.Transtype='buy' and A.to=u.UserID \r\n"
   		+ "group by A.TO\r\n"
   		+ "having max(A.ppsamount)\r\n"
   		+ ";\r\n"
   		+ "";
   String Bbuy="select F.UserID,F.FirstName, F.LastName, F.TransTime, F.Biggestbuy\r\n"
   		+ "from biggest_buy as F\r\n"
   		+ "group by F.UserID\r\n"
   		+ "having F.Biggestbuy=( select max(Biggestbuy) from biggest_buy);\r\n"
   		+ "";
   String Bbuyer="select F.UserID,F.FirstName, F.LastName,F.totalPPS\r\n"
   		+ "from biggest_buy as F\r\n"
   		+ "group by F.UserID\r\n"
   		+ "having F.totalPPS=( select max(totalPPS) from biggest_buy);\r\n"
   		+ "";
   connect_func();
   statement = connect.createStatement();
   statement.executeUpdate(db);
   statement.executeUpdate(dropview);
   statement.executeUpdate(view);
   
   if(option ==1) {
	   resultSet = statement.executeQuery(Bbuy);
	   while(resultSet.next()) {
		   biggest.add(resultSet.getString(1)
				   +" "+resultSet.getString(2)+" "+resultSet.getString(3)
				   +" "+resultSet.getString(4)+" "+resultSet.getString(5));
	   }
   }
   else if(option==2) {
	   resultSet = statement.executeQuery(Bbuyer);
	   while(resultSet.next()) {
		   biggest.add(resultSet.getString(1)
				   +" "+resultSet.getString(2)+" "+resultSet.getString(3)
				   +" "+resultSet.getString(4));
	   }
	   
   }
   resultSet.close();
   statement.close();
   disconnect();
 
   return biggest;
    
}
public ArrayList<String> Popular_users() throws SQLException    {
    ArrayList<String> popularUser = new ArrayList<String>();
   String db="use testdb;";
   String Puser="select U.UserID,U.FirstName, U.LastName, count(F.FollowerEmail) as followers\r\n"
   		+ "from User as U, follow as F\r\n"
   		+ "where u.UserID=F.FollowerEmail\r\n"
   		+ "group by F.FollowerEmail\r\n"
   		+ "having followers >= 5\r\n"
   		+ "";
   connect_func();
   statement = connect.createStatement();
   statement.executeUpdate(db);
   resultSet = statement.executeQuery(Puser);
 
   
   while(resultSet.next()) {
	   popularUser.add(resultSet.getString(1)
			   +" "+resultSet.getString(2)+" "+resultSet.getString(3)
			   +" "+resultSet.getString(4));
   }
   resultSet.close();
   statement.close();
   disconnect();
   return popularUser;
    
}

public ArrayList<String> commonUsers (String email1, String email2) throws SQLException    {
    ArrayList<String> commUsers = new ArrayList<String>();
   String db="use testdb;";
   String cUser="select u.UserID, u.FirstName, u.LastName\r\n"
   		+ "from user as u, follow as a, follow as b\r\n"
   		+ "where u.UserID=a.FollowerEmail and a.FollowerEmail=b.FollowerEmail\r\n"
   		+ "and a.FollowingEmail='"+email1+"' and b.FollowingEmail='"+email2+"';    \r\n"
   		+ "";
   connect_func();
   statement = connect.createStatement();
   statement.executeUpdate(db);
   resultSet = statement.executeQuery(cUser);
 
   
   while(resultSet.next()) {
	   commUsers.add(resultSet.getString(1)
			   +" "+resultSet.getString(2)+" "+resultSet.getString(3));
			   
   }
   resultSet.close();
   statement.close();
   disconnect();
   return commUsers;
    
}

public ArrayList<String> neverbuyUsers () throws SQLException    {
    ArrayList<String> nbuyUsers = new ArrayList<String>();
   String db="use testdb;";
   String nBuy="SELECT distinct u.userid, u.firstname, u.lastname\r\n"
   		+ "From user as u, transations as T\r\n"
   		+ "Where u.userid= t.to and T.Transtype = 'transfer' and T.`to` not in\r\n"
   		+ "(SELECT U.`to` \r\n"
   		+ "From  transations as U\r\n"
   		+ "Where U.Transtype = 'buy')\r\n"
   		+ "";
   connect_func();
   statement = connect.createStatement();
   statement.executeUpdate(db);
   resultSet = statement.executeQuery(nBuy);
 
   
   while(resultSet.next()) {
	   nbuyUsers.add(resultSet.getString(1)
			   +" "+resultSet.getString(2)+" "+resultSet.getString(3));
			   
   }
   resultSet.close();
   statement.close();
   disconnect();
   return nbuyUsers;
    
}

public ArrayList<String> neversellUsers () throws SQLException    {
    ArrayList<String> nsellUsers = new ArrayList<String>();
   String db="use testdb;";
   String nSell="SELECT distinct u.userid, u.firstname, u.lastname\r\n"
   		+ "From user as u, transations as T\r\n"
   		+ "Where u.userid= t.to and T.Transtype = 'buy' and T.to not in\r\n"
   		+ "(SELECT U.from\r\n"
   		+ "From  transations as U\r\n"
   		+ "Where U.Transtype = 'sell')\r\n"
   		+ "";
   connect_func();
   statement = connect.createStatement();
   statement.executeUpdate(db);
   resultSet = statement.executeQuery(nSell);
 
   
   while(resultSet.next()) {
	   nsellUsers.add(resultSet.getString(1)
			   +" "+resultSet.getString(2)+" "+resultSet.getString(3));
			   
   }
   resultSet.close();
   statement.close();
   disconnect();
   return nsellUsers;
    
}

public ArrayList<String> lucky_Users () throws SQLException    {
    ArrayList<String> luckyUsers = new ArrayList<String>();
   String db="use testdb;";
   String lUsers ="SELECT distinct u.userid, u.firstname, u.lastname\r\n"
   		+ "From user as u\r\n"
   		+ "Where u.userid in (\r\n"
   		+ "select distinct f.FollowerEmail\r\n"
   		+ "from follow as f\r\n"
   		+ "group by f.FollowerEmail\r\n"
   		+ "having count(f.FollowerEmail) = (\r\n"
   		+ "	 select count(distinct T.from)\r\n"
   		+ "	 from transations as T \r\n"
   		+ "	 where f.FollowerEmail=t.to and t.to not in(\r\n"
   		+ "			SELECT  x.to\r\n"
   		+ "			from transations as x   \r\n"
   		+ "			where Transtype <>'transfer'\r\n"
   		+ "			union \r\n"
   		+ "			SELECT  y.from\r\n"
   		+ "			from transations as y)\r\n"
   		+ "	 )\r\n"
   		+ ")\r\n"
   		+ "";
   connect_func();
   statement = connect.createStatement();
   statement.executeUpdate(db);
   resultSet = statement.executeQuery(lUsers);
 
   
   while(resultSet.next()) {
	   luckyUsers.add(resultSet.getString(1)
			   +" "+resultSet.getString(2)+" "+resultSet.getString(3));
			   
   }
   resultSet.close();
   statement.close();
   disconnect();
   return luckyUsers;
    
}

public ArrayList<String> Inactive_users () throws SQLException    {
    ArrayList<String> inactiveUsers = new ArrayList<String>();
   String db="use testdb;";
   String iUsers ="SELECT distinct u.userid, u.firstname, u.lastname\r\n"
   		+ "from user as u\r\n"
   		+ "where u.userid not in \r\n"
   		+ "(\r\n"
   		+ "		SELECT  x.to\r\n"
   		+ "		from transations as x   \r\n"
   		+ "        where Transtype <>'transfer'\r\n"
   		+ "		union \r\n"
   		+ "		SELECT  y.from\r\n"
   		+ "		from transations as y\r\n"
   		+ ")\r\n"
   		+ "";
   connect_func();
   statement = connect.createStatement();
   statement.executeUpdate(db);
   resultSet = statement.executeQuery(iUsers);
 
   
   while(resultSet.next()) {
	   inactiveUsers.add(resultSet.getString(1)
			   +" "+resultSet.getString(2)+" "+resultSet.getString(3));
			   
   }
   resultSet.close();
   statement.close();
   disconnect();
   return inactiveUsers;
    
}

public ArrayList<String> Statistics () throws SQLException    {
    ArrayList<String> stat = new ArrayList<String>();
   String db="use testdb;";
   String sta ="SELECT Transtype, count(Transtype) as count\r\n"
   		+ "from transations \r\n"
   		+ "group by Transtype\r\n"
   		+ "";
   connect_func();
   statement = connect.createStatement();
   statement.executeUpdate(db);
   resultSet = statement.executeQuery(sta);
 
   
   while(resultSet.next()) {
	   stat.add(resultSet.getString(1)
			   +" "+resultSet.getString(2));
			   
   }
   resultSet.close();
   statement.close();
   disconnect();
   return stat;
    
}

	
      
}
