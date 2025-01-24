import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './BooksList.css'; // Custom CSS for styling

const BooksList = () => {
  const [books, setBooks] = useState([]);  // State to store books
  const [error, setError] = useState('');  // State to store error message

  // Fetch books after component mounts
  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const token = localStorage.getItem('token');
        const response = await axios.get('http://localhost:8080/books', {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setBooks(response.data);
      } catch (error) {
        setError('Error fetching books.');
        console.error('Error fetching books:', error);
      }
    };

    fetchBooks();
  }, []);  // Empty array to ensure this runs only once when component mounts

  return (
    <div className="books-list">
      {error && <p className="error">{error}</p>} {/* Show error if there's one */}
      {books.length > 0 ? (
        books.map((book) => (
          <div key={book.id} className="book-card">
            <h4>{book.title}</h4>
            <p>Author: {book.author}</p>
            <p>Published: {book.publishedDate}</p>
          </div>
        ))
      ) : (
        <p>No books available in the library.</p>
      )}
    </div>
  );
};

export default BooksList;
