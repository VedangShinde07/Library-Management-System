import React, { useState } from 'react';
import axios from 'axios';
import './DeleteBookPage.css';

const DeleteBookPage = () => {
  const [bookId, setBookId] = useState('');
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const handleDelete = async (e) => {
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
      const response = await axios.delete(
        `http://127.0.0.1:8080/books/delete/${bookId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setMessage(response.data);
      setError('');
    } catch (err) {
      console.error(err);
      setError(err.response?.data?.message || 'Failed to delete the book.');
      setMessage('');
    }
  };

  return (
    <div className="delete-book-container">
      <h1>Delete Book</h1>
      {message && <div className="success-message">{message}</div>}
      {error && <div className="error-message">{error}</div>}
      <form onSubmit={handleDelete} className="delete-book-form">
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
        <button type="submit" className="delete-button">Delete Book</button>
      </form>
    </div>
  );
};

export default DeleteBookPage;
