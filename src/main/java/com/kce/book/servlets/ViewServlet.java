package com.kce.book.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.kce.book.bean.BookBean;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		BookBean bookBean = session == null ? null : (BookBean) session.getAttribute("book");

		if (bookBean == null) {
			response.sendRedirect("Invalid.html");
			return;
		}

		out.print("<html><head><title>Book Details</title></head><body>");
		out.print("<h1>Book Details</h1>");
		out.print("<table border='1' cellpadding='8'>");
		out.print("<tr><th>Field</th><th>Value</th></tr>");
		out.print("<tr><td>Book Title</td><td>" + bookBean.getBookName() + "</td></tr>");
		out.print("<tr><td>Author Name</td><td>" + bookBean.getAuthor().getAuthorName() + "</td></tr>");
		out.print("<tr><td>Author Contact</td><td>" + bookBean.getAuthor().getContactNo() + "</td></tr>");
		out.print("<tr><td>Book Price</td><td>" + bookBean.getCost() + "</td></tr>");
		out.print("<tr><td>Book ISBN</td><td>" + bookBean.getIsbn() + "</td></tr>");
		out.print("</table>");
		out.print("</body></html>");
	}
}
