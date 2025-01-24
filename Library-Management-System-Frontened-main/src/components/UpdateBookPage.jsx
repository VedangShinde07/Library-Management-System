import React, { useState } from 'react';
import axios from 'axios';
import './UpdateBookPage.css';

const UpdateBookPage = () => {
  const [bookId, setBookId] = useState('');
  const [bookDetails, setBookDetails] = useState({
    title: '',
    author: '',
    isbn: '',
    publisher: '',
    publicationDate: '',
    edition: '',
    genre: '',
    description: '',
    language: '',
    numberOfPages: '',
    cost: '',
    available: true,
  });
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setBookDetails((prevDetails) => ({
      ...prevDetails,
      [name]: type === 'checkbox' ? checked : value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const token = localStorage.getItem('token');
    if (!token) {
      setError('Authorization token not found.');
      return;
    }

    if (!bookId || parseInt(bookId) <= 0) {
      setError('Invalid Book ID. Please provide a valid ID.');
      return;
    }

    try {
      const response = await axios.put(
        `http://127.0.0.1:8080/books/update/${bookId}`,
        bookDetails,
        {
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setMessage(`Book updated successfully! Updated Book Title: ${response.data.title}`);
      setError('');
    } catch (err) {
      console.error(err);
      setError(err.response?.data?.message || 'Failed to update the book.');
      setMessage('');
    }
  };

  return (
    <div className="update-book-container">
      <h1>Update Book</h1>
      {message && <div className="success-message">{message}</div>}
      {error && <div className="error-message">{error}</div>}
      <form onSubmit={handleSubmit} className="update-book-form">
        <div className="form-group">
          <label htmlFor="bookId">Book ID*</label>
          <input
            type="number"
            id="bookId"
            value={bookId}
            onChange={(e) => setBookId(e.target.value)}
            required
          />
        </div>
        {/* Render form fields for updating book details */}
        {Object.keys(bookDetails).map((key) => (
          <div className="form-group" key={key}>
            <label htmlFor={key}>{key.charAt(0).toUpperCase() + key.slice(1)}</label>
            {typeof bookDetails[key] === 'boolean' ? (
              <input
                type="checkbox"
                id={key}
                name={key}
                checked={bookDetails[key]}
                onChange={handleChange}
              />
            ) : (
              <input
                type={key === 'publicationDate' ? 'date' : 'text'}
                id={key}
                name={key}
                value={bookDetails[key]}
                onChange={handleChange}
              />
            )}
          </div>
        ))}
        <button type="submit" className="submit-button">Update Book</button>
      </form>
    </div>
  );
};

export default UpdateBookPage;
