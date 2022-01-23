package pack;


import java.io.IOException;



import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;


@WebServlet("/ControlServlet")
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PeopleDAO dao;

	
	 
    public void init() {
        dao = new PeopleDAO(); 
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
        System.out.println(action);
        try {
            switch (action) {
            case "/Initialize": 
                System.out.println("The action is:"+action);
                Initialize(request, response);           	
                break;
            case "/SignUp":
            	System.out.println("The action is:"+action);
            	Register(request, response); 
            	break;
            case "/LogIn":
            	System.out.println("The action is:"+action);
            	SignIn(request, response); 
            	break;
            case "/Logout":
            	System.out.println("The action is:"+action);
            	Logout(request, response); 
            	break;
            case "/Search":
            	System.out.println("The action is:"+action);
            	Search(request, response); 
            	break;
            case "/Follow":
            	System.out.println("The action is:"+action);
            	Follow(request, response); 
            	break;
            case "/Unfollow":
            	System.out.println("The action is:"+action);
            	Unfollow(request, response); 
            	break;
            case "/Deposit":
            	System.out.println("The action is:"+action);
            	Deposit(request, response); 
            	break;
            case "/Withdraw":
            	System.out.println("The action is:"+action);
            	Withdraw(request, response); 
            	break;
            case "/buy":
            	System.out.println("The action is:"+action);
            	Buy(request, response); 
            	break;
            case "/sell":
            	System.out.println("The action is:"+action);
            	Sell(request, response); 
            	break;
            case "/transfer":
            	System.out.println("The action is:"+action);
            	Transfer(request, response); 
            	break;
            case "/partthree":
            	System.out.println("The action is:"+action);
            	PartThree(request, response); 
            	break;
            case "/commonUser":
            	System.out.println("The action is:"+action);
            	commonUser(request, response); 
            	break;
            default:
                System.out.println("Not sure which action, we will treat it as the list action");
                //listPeople(request, response);           	
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);

        }
 
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private void commonUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		String userA=request.getParameter("UserA");
		String userB=request.getParameter("UserB");
		
		ArrayList<String> commonUser=dao.commonUsers(userA, userB);
		request.setAttribute("common", commonUser);   
        request.setAttribute("common2", "The Common Users");  
        RequestDispatcher rd= request.getRequestDispatcher("Follow.jsp");
		rd.forward(request, response);
		
	}
	private void PartThree(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		ArrayList<String> FrequentBuyers=dao.Frequent_buyers();
		ArrayList<String> BiggestBuy=dao.BiggestBuyAndBuyers(1);
		ArrayList<String> BiggestBuyer=dao.BiggestBuyAndBuyers(2);
		ArrayList<String> PopularUsers=dao.Popular_users();
		ArrayList<String> Neverbuy=dao.neverbuyUsers();
		ArrayList<String> Neversell=dao.neversellUsers();
		ArrayList<String> LuckyUsers=dao.lucky_Users();
		ArrayList<String> InactiveUsers=dao.Inactive_users();
		ArrayList<String> Statistics=dao.Statistics();
		
        request.setAttribute("Fbuyer", FrequentBuyers);   
        request.setAttribute("Fbuyer2", "The Frequent Buyers");   
        
		
		request.setAttribute("Bbuy", BiggestBuy); 
		request.setAttribute("Bbuy2","The Biggest Buy");
		  
		request.setAttribute("Bbuyer", BiggestBuyer);											
		request.setAttribute("Bbuyer2", "The Biggest Buyer");
		  
		request.setAttribute("Pusers", PopularUsers);
		request.setAttribute("Pusers2", "The Popular Users");
		  
		request.setAttribute("Nsell", Neversell); 
		request.setAttribute("Nsell2","The Never Sell Users");
		  
		request.setAttribute("Nbuy", Neverbuy);
		request.setAttribute("Nbuy2", "The Never Buy Users");
		  
		request.setAttribute("Lusers", LuckyUsers);
		request.setAttribute("Lusers2","The Lucky Users");
		  
		request.setAttribute("Iusers", InactiveUsers);
		request.setAttribute("Iusers2", "The Inactive Users");
		  
		request.setAttribute("Stat", Statistics); request.setAttribute("Stat2",
		  "Total Transactions");
		 
        
        RequestDispatcher rd= request.getRequestDispatcher("part3.jsp");
		rd.forward(request, response);
		
	}
	
	private void Buy(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		
		HttpSession session = request.getSession();
		String email=(String)(session.getAttribute("UserID"));
		String amount=request.getParameter("ppsamount");
		
		if(isNumericPositive(amount)) {
			double d = Double.parseDouble(amount);
			if(dao.buyPPs(email, d)==true) {
				request.setAttribute("buypps", "A balance of $"+d+" is added to your account.");
			}
			else {
				request.setAttribute("buypps", "Your amount exceeds the meximum pps amount.");
			}
			
		}
		else {
			request.setAttribute("buypps", "Plese enter correct amount.");
		}
		
		
		RequestDispatcher rd= request.getRequestDispatcher("Activity.jsp");
		rd.forward(request, response);
		
		
	}
	
	
	private void Sell(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		
		HttpSession session = request.getSession();
		String email=(String)(session.getAttribute("UserID"));
		String amount=request.getParameter("sellamount");
		
		if(isNumericPositive(amount)) {
			double d = Double.parseDouble(amount);
			if(dao.sellPPs(email, d)==true) {
				request.setAttribute("sellpps", " PPS balance of "+d+" has been sold from your account.");
			}
			else {
				request.setAttribute("sellpps", "You don't have enough PPS to sell.");
			}
			
		}
		else {
			request.setAttribute("sellpps", "Plese enter correct amount.");
		}
		RequestDispatcher rd= request.getRequestDispatcher("Activity.jsp");
		rd.forward(request, response);
		
		
	}
	
	private void Transfer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		
		HttpSession session = request.getSession();
		String email=(String)(session.getAttribute("UserID"));
		String transferID=request.getParameter("UserA");
		String amount=request.getParameter("pps");
		
		System.out.println(email+"   "+transferID);
		
		if(isNumericPositive(amount)) {
			
			double d = Double.parseDouble(amount);
			if(dao.transferPPS(email, transferID, d)==true) {
				request.setAttribute("transfer", "A balance of $"+d+" is added to your account.");
			}
			else {
				request.setAttribute("transfer", "Transfer amount exceeds your pps balance.");
			}		
		}
		else {
			request.setAttribute("transfer", "Plese enter correct amount.");
		}
		RequestDispatcher rd= request.getRequestDispatcher("Activity.jsp");
		rd.forward(request, response);
				
	}
	
	
	private void Deposit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		String email=(String)(session.getAttribute("UserID"));
		String amount=request.getParameter("deposit");
		if(isNumericPositive(amount)) {
			double d = Double.parseDouble(amount);
			dao.depositToPpsAccount(d, email);
			request.setAttribute("deposit", "A balance of amount $"+d+" is deposited to your account.");
		}
		else {
			request.setAttribute("deposit", "Plese enter a positive real number.");
		}
		RequestDispatcher rd= request.getRequestDispatcher("Balance.jsp");
		rd.forward(request, response);
	}
	
	private void Withdraw(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		String email=(String)(session.getAttribute("UserID"));
		String amount=request.getParameter("withdraw");
		
		if(isNumericPositive(amount)) {
			double d = Double.parseDouble(amount);
			if(dao.withdrawToBank(d, email)==true) {
				request.setAttribute("withdraw", "A balance of $"+d+" is withdrawn to your Bank.");
			}
			else {
				request.setAttribute("withdraw", "You don't have enough balance.");
			}
			
		}
		else {
			request.setAttribute("withdraw", "Plese enter a positive real number.");
		}
		RequestDispatcher rd= request.getRequestDispatcher("Balance.jsp");
		rd.forward(request, response);
	}
	
	
	private void Follow(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		String followingemail=(String)(session.getAttribute("UserID"));
		String followeremail=request.getParameter("following");
		dao.addFollower(followingemail, followeremail);
		request.setAttribute("Follow", "User "+followeremail+" followed.");
		RequestDispatcher rd= request.getRequestDispatcher("Follow.jsp");
		rd.forward(request, response);
	}
	
	private void Unfollow(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		String followingemail=(String)(session.getAttribute("UserID"));
		String followeremail=request.getParameter("unfollowing");
		dao.removeFollower(followingemail, followeremail);
		request.setAttribute("Unfollow", "User "+followeremail+" removed from following.");
		RequestDispatcher rd= request.getRequestDispatcher("Follow.jsp");
		rd.forward(request, response);		
	}
	
	private void Search(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		String email=(String)(session.getAttribute("UserID"));
		String name = request.getParameter("search");
		
		if(name.length()==0) {
			request.setAttribute("message", "Please enter a name in search area.");
		}
		else {
			List<People> listPeople = dao.listAllPeople(name,email);
			if(listPeople.isEmpty()) {
				request.setAttribute("emptysearch", "No user found in search result.");
			}
			else {
	        request.setAttribute("listPeople", listPeople);       
			}
		}
	     RequestDispatcher dispatcher = request.getRequestDispatcher("Follow.jsp");       
	     dispatcher.forward(request, response);
	}
	
	private void Initialize(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		dao.Initialize();
		request.setAttribute("confermation", "The database is initialized!");
		RequestDispatcher rd= request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);	
	}
	private void Register(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		
		String email = request.getParameter("emailaddress");
        String password = request.getParameter("password");
        String password2= request.getParameter("repeatpassword");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String Date_of_Birth = request.getParameter("birthday");
        String houseadress= request.getParameter("address");
        String city= request.getParameter("city");
        String state= request.getParameter("state");
        String zipcode= request.getParameter("zipcode");
        String FullAddress = houseadress +", "+city+", "+ state+ " "+zipcode;
        
        if(dao.isEmailExists(email)) {
        	request.setAttribute("message", "The email is already taken. Try again!");
        	RequestDispatcher rd= request.getRequestDispatcher("register.jsp");
    		rd.forward(request, response);
        }
        if(!(password.equals(password2))) {
        	request.setAttribute("message2", "Password Do not match.");
        	RequestDispatcher rd= request.getRequestDispatcher("register.jsp");
    		rd.forward(request, response);
        }
        else {
        	People people = new People();
        	people.setEmail(email);
        	people.setPassword(password);
        	people.setFirstname(firstName);
        	people.setLastname(lastName);
        	people.setDateOfBirth(Date_of_Birth);
        	people.setAddress(FullAddress);
        	
        	dao.registerUser(people);
        	response.sendRedirect("login.jsp");
        }
        
	}
	private void SignIn(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		String email = request.getParameter("emailaddress");
        String password = request.getParameter("password");
        if(dao.isPasswordEmailEqual(email, password)) {
        	HttpSession session = request.getSession();
        	session.setAttribute("UserID", email);
        	response.sendRedirect("Activity.jsp");
        }
        else {
        	request.setAttribute("Warning", "Email or Password is incorrect");
        	RequestDispatcher rd= request.getRequestDispatcher("login.jsp");
    		rd.forward(request, response);
        }
        
	}
	private void Logout(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("UserID");
		session.invalidate();
		response.sendRedirect("login.jsp");
        
	}
	private boolean isNumericPositive(String strNum) {
		double d;
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        d = Double.parseDouble(strNum);
	    } catch (NumberFormatException e) {
	        return false;
	    }
	    if(d > 0)return true;
	    
	    return false;
	}
	
}
