package com.kce.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kce.book.bean.BookBean;
import com.kce.book.util.Dbutil;

public class BookDAO {
	Connection con=Dbutil.getDBConnection();
	public int createBook(BookBean bookBean)
	{
		String query="insert into BOOK_TBL values(?,?,?,?,?)";
		try
		{
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, bookBean.getIsbn());
			ps.setString(2, bookBean.getBookName());
			ps.setString(3, String.valueOf(bookBean.getBookType()));
			ps.setInt(4, bookBean.getAuthor().getAuthorCode());
			ps.setDouble(5, bookBean.getCost());
			int rows=ps.executeUpdate();
			if(rows>0)
			{
				return 1;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	public BookBean fetchBook(String isbn)
	{
		BookBean book=new BookBean();
		String query="select * from BOOK_TBL where ISBN=?";
		try
		{
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,isbn);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				book.setIsbn(rs.getString(1));
				book.setBookName(rs.getString(2));
				book.setBookType(rs.getString(3).charAt(0));
				book.setAuthor(new AuthorDAO().getAuthor(rs.getInt(4)));
				book.setCost(rs.getFloat(5));
				return book;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
