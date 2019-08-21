package com.servlets;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.Account;
import com.user.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		// get session
		HttpSession session = request.getSession(true);
		
		// create file object from user given email
		File file = new File(User.getPath() + request.getParameter("email") + ".txt");
		
		// reroute back to login page if user given email is not present in the file system
		if(!file.exists())
		{
			RequestDispatcher rs = request.getRequestDispatcher("index.jsp?message=Email%20is%20not%20registered%20in%20system");
			rs.forward(request,response);
		}
		// check password and reroute if incorrect, else create new user object and populate variables from file system and set to the session
		else
		{
			// read user profile and store parsed string in variable
			Scanner scanner = new Scanner(file);
			String[] parsedProfile = scanner.nextLine().split(", ");
			scanner.close();
			
			// reroute to login page if password is incorrect
			if(!request.getParameter("password").equals(parsedProfile[3]))
			{
				RequestDispatcher rs = request.getRequestDispatcher("index.jsp?message=Password%20is%20incorrect");
				rs.forward(request,response);
			}
			// create user object and store user in the session if there are no accounts associated with the user profile
			else if(parsedProfile.length == 4)
			{
				// read user file and store new user object in 'user' variable, then set in session
				User user = User.readFile(request.getParameter("email"));				
				session.setAttribute("user", user);
				
				RequestDispatcher rs = request.getRequestDispatcher("home.jsp");
				rs.forward(request,response);
			}
			// create user object and populate/add account objects to user accounts list, then store in session
			else
			{
				// read user file and store new user object in 'user' variable
				User user = User.readFile(request.getParameter("email"));
					
				// for each account associated with the user, populate new account objects and store them in users Accounts list
				for(int i = 4; i < parsedProfile.length; i++)
				{
					Account account = Account.readFile(user.getEmail(), parsedProfile[i]);
					user.addAccount(account);
				}
				
				// add user to the session
				session.setAttribute("user", user);
				
				// send to home page
				RequestDispatcher rs = request.getRequestDispatcher("home.jsp");
				rs.forward(request,response);
			}
					
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
