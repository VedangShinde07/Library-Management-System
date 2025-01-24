import axios from 'axios';

// Create an instance of axios with your base API URL
const api = axios.create({
  baseURL: 'http://localhost:8080/',  // Replace with your backend URL
});

// Interceptor to add the token to the headers of every request
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');  // Retrieve token from localStorage
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;  // Attach token if present
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default api;
