package com.api.book.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.book.entities.Book;


@Service
public class BookServices {
	
	private static List<Book> list=new ArrayList<>();
	
	static {
		list.add(new Book(1,"bagwat gita","shri krishna"));
		list.add(new Book(2,"mahabharat","vyas"));
		list.add(new Book(3,"ramkatha","Ram"));
	}
	
	//get all book
	public List<Book> getAllBook()
	{
		return list;
	}
	
	//get single book by id
	public Book getBookById(int id)
	{
		Book book=null;
		try {
			book= list.stream().filter(e->e.getId()==id).findFirst().get();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return book;
	}

	//to add book
	public Book addBook(Book book)
	{
		list.add(book);
		return book;
		
	}

	
	//delete Book by id
	public void deleteBook(int id) {
		
		list=list.stream().filter(e-> e.getId()!=id).collect(Collectors.toList());
		
	}

	public void updateBook(Book book, int id) {
		
		list=list.stream().map(e->{
			if(e.getId()==id)
			{
			e.setTitle(book.getTitle());
			e.setAuthor(book.getAuthor());
			}
			return e;
			
		}).collect(Collectors.toList());
		
	}
}
