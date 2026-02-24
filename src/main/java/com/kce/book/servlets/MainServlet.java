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
	private static final long serialVersionUID = 1L;
	private static final String OPERATION_ADD_BOOK = "AddBook";
	private static final String OPERATION_SEARCH = "Search";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("Menu.html");
	}

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
		if (isbn == null || bookName == null || bookType == null || authorName == null || cost == null || bookType.isBlank()) {
			return "INVALID";
		}

		float parsedCost;
		try {
			parsedCost = Float.parseFloat(cost);
		} catch (NumberFormatException ex) {
			return "INVALID";
		}

		BookBean book = new BookBean();
		book.setIsbn(isbn.trim());
		book.setBookName(bookName.trim());
		book.setBookType(Character.toUpperCase(bookType.trim().charAt(0)));
		book.setCost(parsedCost);
		book.setAuthor(new AuthorDAO().getAuthor(authorName.trim()));
		return new Administrator().addBook(book);
	}

	public BookBean viewBook(String isbn) {
		if (isbn == null || isbn.isBlank()) {
			return null;
		}
		return new Administrator().viewBook(isbn.trim());
	}
}
