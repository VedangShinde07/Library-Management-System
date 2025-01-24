import React, { useState } from 'react';
import axios from 'axios';
import './AddBookPage.css'; // Optional: Add your custom styles

const AddBookPage = () => {
  const [title, setTitle] = useState('');
  const [author, setAuthor] = useState('');
  const [isbn, setIsbn] = useState('');
  const [publisher, setPublisher] = useState('');
  const [publicationDate, setPublicationDate] = useState('');
  const [edition, setEdition] = useState('');
  const [genre, setGenre] = useState('');
  const [description, setDescription] = useState('');
  const [language, setLanguage] = useState('');
  const [numberOfPages, setNumberOfPages] = useState('');
  const [cost, setCost] = useState('');
  const [available, setAvailable] = useState(true);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Get the token from localStorage
    const token = localStorage.getItem('token'); // Assuming the token is stored in localStorage

    if (!token) {
      setError('Authorization token not found.');
      return;
    }

    // Validate form fields
    if (!title || !author || !isbn || !numberOfPages || !cost) {
      setError('Please fill in all required fields.');
      return;
    }

    const bookData = {
      title,
      author,
      isbn,
      publisher,
      publicationDate,
      edition,
      genre,
      description,
      language,
      numberOfPages: parseInt(numberOfPages),
      cost: parseFloat(cost),
      available,
    };

    try {
      const response = await axios.post(
        'http://127.0.0.1:8080/books/add',
        bookData,
        {
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`, // Add token to the Authorization header
          },
        }
      );

      setMessage(`Book added successfully! Book ID: ${response.data.id}`);
      setError('');
      // Clear the form after successful submission
      setTitle('');
      setAuthor('');
      setIsbn('');
      setPublisher('');
      setPublicationDate('');
      setEdition('');
      setGenre('');
      setDescription('');
      setLanguage('');
      setNumberOfPages('');
      setCost('');
      setAvailable(true);
    } catch (err) {
      console.error(err);
      setError(err.response?.data?.message || 'Failed to add the book.');
      setMessage('');
    }
  };

  return (
    <div className="add-book-container">
      <h1>Add New Book</h1>
      {message && <div className="success-message">{message}</div>}
      {error && <div className="error-message">{error}</div>}
      <form onSubmit={handleSubmit} className="add-book-form">
        <div className="form-group">
          <label htmlFor="title">Title*</label>
          <input
            type="text"
            id="title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="author">Author*</label>
          <input
            type="text"
            id="author"
            value={author}
            onChange={(e) => setAuthor(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="isbn">ISBN*</label>
          <input
            type="text"
            id="isbn"
            value={isbn}
            onChange={(e) => setIsbn(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="publisher">Publisher</label>
          <input
            type="text"
            id="publisher"
            value={publisher}
            onChange={(e) => setPublisher(e.target.value)}
          />
        </div>
        <div className="form-group">
          <label htmlFor="publicationDate">Publication Date</label>
          <input
            type="date"
            id="publicationDate"
            value={publicationDate}
            onChange={(e) => setPublicationDate(e.target.value)}
          />
        </div>
        <div className="form-group">
          <label htmlFor="edition">Edition</label>
          <input
            type="text"
            id="edition"
            value={edition}
            onChange={(e) => setEdition(e.target.value)}
          />
        </div>
        <div className="form-group">
          <label htmlFor="genre">Genre</label>
          <input
            type="text"
            id="genre"
            value={genre}
            onChange={(e) => setGenre(e.target.value)}
          />
        </div>
        <div className="form-group">
          <label htmlFor="description">Description</label>
          <textarea
            id="description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />
        </div>
        <div className="form-group">
          <label htmlFor="language">Language</label>
          <input
            type="text"
            id="language"
            value={language}
            onChange={(e) => setLanguage(e.target.value)}
          />
        </div>
        <div className="form-group">
          <label htmlFor="numberOfPages">Number of Pages*</label>
          <input
            type="number"
            id="numberOfPages"
            value={numberOfPages}
            onChange={(e) => setNumberOfPages(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="cost">Cost*</label>
          <input
            type="number"
            id="cost"
            value={cost}
            onChange={(e) => setCost(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="available">Available</label>
          <input
            type="checkbox"
            id="available"
            checked={available}
            onChange={(e) => setAvailable(e.target.checked)}
          />
        </div>
        <button type="submit" className="submit-button">
          Add Book
        </button>
      </form>
    </div>
  );
};

export default AddBookPage;
