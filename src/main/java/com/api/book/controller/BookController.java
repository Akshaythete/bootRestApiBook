package com.api.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.entities.Book;
import com.api.book.services.BookServices;

@RestController
public class BookController {
	
	@Autowired
	private BookServices bookServices;
	
	@GetMapping("/book")
	public ResponseEntity< List<Book>> getBook()
	{
		List<Book> b= this.bookServices.getAllBook();
		System.out.println(b);
		if(b.size()<=0)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(b));
		
		
	}
	@GetMapping("/book/{id}")
	public ResponseEntity<Book>  getBooks(@PathVariable("id") int id)
	{
		Book book=this.bookServices.getBookById(id);
		if(book==null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.of(Optional.of(book));
	}
	
	@PostMapping("/book")
	public ResponseEntity<Book> addBook(@RequestBody Book book)
	{
		Book books=null;
		try
		{
		 books= this.bookServices.addBook(book);
		 System.out.println(book);
		return ResponseEntity.status(HttpStatus.CREATED).build();
		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
		
		
		
		
	}
	@DeleteMapping("/book/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable("id") int id)
	{
		try 
		{
		      this.bookServices.deleteBook(id);
		      System.out.println(id);
		      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@PutMapping("/book/{id}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book ,@PathVariable("id") int id)
	{
		
		try 
		{
		  this.bookServices.updateBook( book, id);
	       System.out.println(book);
	       return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	

}
