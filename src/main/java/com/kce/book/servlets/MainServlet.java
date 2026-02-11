package com.kce.book.servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.kce.book.bean.BookBean;
import com.kce.book.dao.AuthorDAO;
import com.kce.book.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation=request.getParameter("operation");
		if(operation.equals("AddBook"))
		{
			String result=addBook(request);
			if(result.equals("SUCCESS"))
			{
				response.sendRedirect("Menu.html");
			}
			else if(result.equals("INVALID"))
			{
				response.sendRedirect("Invalid.html");
			}
			else
			{
				response.sendRedirect("Failure.html");
			}
		}
		else if(operation.equals("Search"))
		{
			String isbn=request.getParameter("isbn");
			BookBean bookBean=viewBook(isbn);
			if(bookBean == null)
			{
				response.sendRedirect("Invalid.html");
			}
			else
			{
				HttpSession session=request.getSession();
				session.setAttribute("book", bookBean);
				RequestDispatcher rd=request.getRequestDispatcher("ViewServlet");
				rd.forward(request, response);
			}
			
		}
	}
	public String addBook(HttpServletRequest request)
	{
		//get form data from request
		String isbn=request.getParameter("isbn");
		String bookName=request.getParameter("bookName");
		String bookType=request.getParameter("bookType");
		String author=request.getParameter("authorName");
		String cost=request.getParameter("cost");
		BookBean book=new BookBean();
		book.setIsbn(isbn);
		book.setBookName(bookName);
		book.setBookType(bookType.charAt(0));
		book.setCost(Float.parseFloat(cost));
		book.setAuthor(new AuthorDAO().getAuthor(author));
		String result=new Administrator().addBook(book);
		return result;
	}
	public BookBean viewBook(String isbn)
	{
		return new Administrator().viewBook(isbn);
	}
	
}
