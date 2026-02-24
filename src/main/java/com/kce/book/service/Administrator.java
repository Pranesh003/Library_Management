package com.kce.book.service;

import com.kce.book.bean.AuthorBean;
import com.kce.book.bean.BookBean;
import com.kce.book.dao.BookDAO;

public class Administrator {
	public String addBook(BookBean bookBean) {
		if (bookBean == null || bookBean.getBookName() == null || bookBean.getBookName().isBlank() || bookBean.getIsbn() == null
				|| bookBean.getIsbn().isBlank() || bookBean.getCost() <= 0 || isInvalidAuthor(bookBean.getAuthor())
				|| (bookBean.getBookType() != 'G' && bookBean.getBookType() != 'T')) {
			return "INVALID";
		}
		int result = new BookDAO().createBook(bookBean);
		if (result == 1) {
			return "SUCCESS";
		}
		return "FAILURE";
	}

	private boolean isInvalidAuthor(AuthorBean author) {
		return author == null || author.getAuthorName() == null || author.getAuthorName().isBlank();
	}

	public BookBean viewBook(String isbn) {
		BookDAO bookDao = new BookDAO();
		return bookDao.fetchBook(isbn);
	}
}
