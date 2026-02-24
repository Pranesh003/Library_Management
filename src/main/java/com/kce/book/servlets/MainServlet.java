package com.kce.book.servlets;

import java.io.IOException;

import com.kce.book.bean.BookBean;
import com.kce.book.dao.AuthorDAO;
import com.kce.book.service.Administrator;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final String OPERATION_ADD_BOOK = "AddBook";
	private static final String OPERATION_SEARCH = "Search";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if (OPERATION_ADD_BOOK.equals(operation)) {
			handleAddBook(request, response);
		} else if (OPERATION_SEARCH.equals(operation)) {
			handleSearch(request, response);
		} else {
			response.sendRedirect("Invalid.html");
		}
	}

	private void handleAddBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String result = addBook(request);
		if ("SUCCESS".equals(result)) {
			response.sendRedirect("Menu.html");
		} else if ("INVALID".equals(result)) {
			response.sendRedirect("Invalid.html");
		} else {
			response.sendRedirect("Failure.html");
		}
	}

	private void handleSearch(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String isbn = request.getParameter("isbn");
		BookBean bookBean = viewBook(isbn);
		if (bookBean == null) {
			response.sendRedirect("Invalid.html");
			return;
		}

		HttpSession session = request.getSession();
		session.setAttribute("book", bookBean);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ViewServlet");
		dispatcher.forward(request, response);
	}

	public String addBook(HttpServletRequest request) {
		String isbn = request.getParameter("isbn");
		String bookName = request.getParameter("bookName");
		String bookType = request.getParameter("bookType");
		String authorName = request.getParameter("authorName");
		String cost = request.getParameter("cost");

		BookBean book = new BookBean();
		book.setIsbn(isbn);
		book.setBookName(bookName);
		book.setBookType(bookType.charAt(0));
		book.setCost(Float.parseFloat(cost));
		book.setAuthor(new AuthorDAO().getAuthor(authorName));
		return new Administrator().addBook(book);
	}

	public BookBean viewBook(String isbn) {
		return new Administrator().viewBook(isbn);
	}
}
