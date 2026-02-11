package com.kce.book.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.kce.book.bean.BookBean;

@WebServlet("/VeiwServlet")
public class VeiwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		BookBean bookBean=(BookBean)session.getAttribute("book");
		out.print("<html><body>");
		out.print("Book title: "+bookBean.getBookName());
		out.print("Author Name: "+bookBean.getAuthor().getAuthorName());
		out.print("Author Contact: "+bookBean.getAuthor().getContactNo());
		out.print("Book Price: "+bookBean.getCost());
		out.print("Book ISBN:"+bookBean.getIsbn());
		out.print("</body></html>");
	}

}
