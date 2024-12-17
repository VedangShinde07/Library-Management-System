package com.hexaware.librarymanagement;

import com.hexaware.librarymanagement.entity.Book;
import com.hexaware.librarymanagement.entity.Role;
import com.hexaware.librarymanagement.repository.BookRepository;
import com.hexaware.librarymanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;

@SpringBootApplication
public class LibrarymanagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LibrarymanagementApplication.class, args);
	}

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		// Sample date format
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		// Creating sample Book objects
		//Book b1 = new Book("The Alchemist", "Paulo Coelho", "9780061122415", "HarperOne", dateFormat.parse("1988-05-01"), "First", "Fiction", "A journey of self-discovery.", "English", 208, 10.99, true);
		//Book b2 = new Book("1984", "George Orwell", "9780451524935", "Plume", dateFormat.parse("1949-06-08"), "First", "Dystopian", "A dystopian social science fiction novel.", "English", 328, 15.99, true);
		//Book b3 = new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084", "Harper Perennial", dateFormat.parse("1960-07-11"), "First", "Classic", "A novel about racial injustice.", "English", 336, 12.99, true);
		//Book b4 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", "Scribner", dateFormat.parse("1925-04-10"), "First", "Classic", "A critique of the American Dream.", "English", 180, 14.99, true);
		//Book b5 = new Book("Moby Dick", "Herman Melville", "9781503280786", "CreateSpace", dateFormat.parse("1851-11-14"), "First", "Adventure", "A sailor's narrative of the obsessive quest of Ahab.", "English", 720, 18.99, true);

		// Saving books to the repository
		//bookRepository.save(b1);
		//bookRepository.save(b2);
		//bookRepository.save(b3);
		//bookRepository.save(b4);
		//bookRepository.save(b5);

		//Role adminRole = new Role();
		//adminRole.setName("ADMIN");

		//Role userRole = new Role();
		//userRole.setName("USER");

		// Saving roles to the repository
		//roleRepository.save(adminRole);
		//roleRepository.save(userRole);

	}

}
