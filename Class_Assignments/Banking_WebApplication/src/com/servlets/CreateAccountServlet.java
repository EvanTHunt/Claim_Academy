package com.servlets;

import java.io.IOException;

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
 * Servlet implementation class CreateAccountServlet
 */
@WebServlet("/CreateAccount")
public class CreateAccountServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccountServlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		// retrieve session an get user from session
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");
		
		// reroute back to createaccount page if user already has account with the given ID, else add new account
		if(user.isAccountPresent(request.getParameter("Id")))
		{
			RequestDispatcher rs = request.getRequestDispatcher("createaccount.jsp?message=Account%20already%20exists");
			rs.forward(request,response);
		}
		else
		{
			// create new account object and populate with user input
			Account account = new Account();
			account.setId(request.getParameter("Id"));
			account.setBalance(Double.parseDouble(request.getParameter("initBalance")));
			
			// add new account object to user and update user file
			user.addAccount(account);
			user.writeUserToFile();
			
			// write new account to its own file
			account.writeAccountToFile(user.getEmail());
			
			// save user back to session
			session.setAttribute("user", user);
			
			// send to home page
			RequestDispatcher rs = request.getRequestDispatcher("home.jsp");
			rs.forward(request,response);
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
