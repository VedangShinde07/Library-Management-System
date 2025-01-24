import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Sidebar from '../Sidebar/Sidebar'; // Import Sidebar component
import './HomePage.css'; // Importing the CSS file

const HomePage = () => {
  const [data, setData] = useState([]);
  const [error, setError] = useState('');
  const [selectedBook, setSelectedBook] = useState(null); // State to store the selected book

  useEffect(() => {
    const fetchData = async () => {
      try {
        const token = localStorage.getItem('token');
        const headers = {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        };
        
        const response = await axios.get('http://127.0.0.1:8080/books', { headers, withCredentials: true });
        setData(response.data);
      } catch (err) {
        console.error(err);
        setError('Failed to fetch data');
      }
    };

    fetchData();
  }, []);

  // Function to handle book click and toggle the book details visibility
  const handleBookClick = (book) => {
    setSelectedBook(book); // Set the selected book on click
    const detailsElement = document.querySelector(`.book-details-${book.id}`);
    detailsElement.classList.toggle('show'); // Toggle the 'show' class to expand/collapse details
  };

  return (
    <div className="home-container">
      <Sidebar /> {/* Sidebar Component */}
      <div className="home-page">
        <h1 className="home-title">Library Management System</h1>
        <h2 className="list-title">List of Books</h2>
        {error && <p className="error-message">{error}</p>}
        
        {/* Display books in a grid */}
        <div className="book-grid">
          {data.map(item => (
            <div 
              key={item.id} 
              className="book-box" 
              onClick={() => handleBookClick(item)} // Handle click on the book
            >
              <h3 className="book-title">{item.title}</h3>
              <p className="book-author">Author: {item.author}</p>
              <p className="book-edition">Edition: {item.edition}</p>
              <p className="book-genre">Genre: {item.genre}</p>
            </div>
          ))}
        </div>

        {/* Display selected book details with dynamic class for each book */}
        {data.map(item => (
          <div 
            key={item.id} 
            className={`book-details book-details-${item.id}`}
          >
            <h3>Book Details</h3>
            <p><strong>Title:</strong> {item.title}</p>
            <p><strong>Author:</strong> {item.author}</p>
            <p><strong>Edition:</strong> {item.edition}</p>
            <p><strong>Genre:</strong> {item.genre}</p>
            <p><strong>Language:</strong> {item.language}</p>
            <p><strong>Publisher:</strong> {item.publisher}</p>
            <p><strong>Cost:</strong> {item.cost}</p>
            <p><strong>ISBN:</strong> {item.isbn}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default HomePage;
