import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './LoginPage.css';

const LoginPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate(); // To navigate after login

  // Handle login form submission
  const handleLogin = async (e) => {
    e.preventDefault(); // Prevent form from reloading the page

    try {
      const response = await axios.post('http://localhost:8080/api/authenticate/login', {
        username,
        password,
      });

      // Save the token to localStorage
      localStorage.setItem('token', response.data.accessToken);

      // Redirect to home page after successful login
      navigate('/home');
    } catch (err) {
      setError('Invalid credentials. Please try again.'); // Show error message
    }
  };

  return (
    <div className="login-container">
      <form onSubmit={handleLogin} className="login-form">
        <h2>Login</h2>
        <div className="form-group">
          <label>Username:</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        {error && <p className="error">{error}</p>}
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default LoginPage;
