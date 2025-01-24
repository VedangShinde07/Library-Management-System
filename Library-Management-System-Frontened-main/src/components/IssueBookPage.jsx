import React, { useState } from 'react';
import axios from 'axios';
import './IssueBookPage.css'; // Optional: Add your styles here

const IssueBookPage = () => {
  const [userId, setUserId] = useState('');
  const [bookId, setBookId] = useState('');
  const [borrowedDate, setBorrowedDate] = useState('');
  const [dueDate, setDueDate] = useState(''); // State for due date
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validate form fields
    if (!userId || !bookId || !dueDate) { // Due date is now required
      setError('User ID, Book ID, and Due Date are required.');
      return;
    }

    const requestData = {
      userId: parseInt(userId),
      bookId: parseInt(bookId),
      borrowedDate: borrowedDate || null, // If empty, let backend set the current date
      dueDate: dueDate, // Add due date here
      fine: 0.0, // Default fine
    };

    // Get the token from localStorage
    const token = localStorage.getItem('token'); // Assuming the token is stored in localStorage

    if (!token) {
      setError('Authorization token not found.');
      return;
    }

    try {
      const response = await axios.post('http://127.0.0.1:8080/borrowed-books/borrow', requestData, {
        headers: {
          'Authorization': `Bearer ${token}`, // Include the Authorization token
          'Content-Type': 'application/json',
        },
      });

      setMessage(`Book issued successfully! Borrowed Book ID: ${response.data.id}`);
      setError('');
    } catch (err) {
      console.error(err);
      setError(err.response?.data?.message || 'Failed to issue the book.');
      setMessage('');
    }
  };

  return (
    <div className="issue-book-container">
      <h1>Issue Book</h1>
      {message && <div className="success-message">{message}</div>}
      {error && <div className="error-message">{error}</div>}
      <form onSubmit={handleSubmit} className="issue-book-form">
        <div className="form-group">
          <label htmlFor="userId">User ID</label>
          <input
            type="number"
            id="userId"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="bookId">Book ID</label>
          <input
            type="number"
            id="bookId"
            value={bookId}
            onChange={(e) => setBookId(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="borrowedDate">Borrowed Date</label>
          <input
            type="date"
            id="borrowedDate"
            value={borrowedDate}
            onChange={(e) => setBorrowedDate(e.target.value)}
          />
        </div>
        <div className="form-group">
          <label htmlFor="dueDate">Due Date</label> {/* New Due Date input */}
          <input
            type="date"
            id="dueDate"
            value={dueDate}
            onChange={(e) => setDueDate(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="submit-button">
          Issue Book
        </button>
      </form>
    </div>
  );
};

export default IssueBookPage;
