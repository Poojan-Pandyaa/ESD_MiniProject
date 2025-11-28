import axios from 'axios';

const BASE_URL = 'http://localhost:9191/api/v1/auth';

export const authService = {
  login: async (email, password) => {
    try {
      const response = await axios.post('http://localhost:9191/api/auth/login', {
        email,
        password,
      });

      // Save token from response
      if (response.data.token) {
        localStorage.setItem('user', response.data.token);
        console.log('Login successful! Token:', response.data.token);
        console.log('Role:', response.data.role);
      }

      return response.data;
    } catch (err) {
      // Extract user-friendly error message
      if (err.response?.data?.message) {
        throw err.response.data.message;
      } else if (err.response?.status === 404 || err.response?.status === 401) {
        throw 'Invalid email or password. Please try again.';
      } else if (err.response?.status === 400) {
        throw 'Please enter a valid email and password.';
      } else if (err.message) {
        throw err.message;
      } else {
        throw 'Failed to login. Please check your connection and try again.';
      }
    }
  },

  saveToken: (token) => {
    localStorage.setItem('user', token);
  },

  removeToken: () => {
    localStorage.removeItem('user');
  },


  getToken: () => {
    return localStorage.getItem('user');
  },

  getRole: () => {
    const token = localStorage.getItem('user');
    if (!token) return null;
    try {
      const base64Url = token.split('.')[1];
      const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
      const jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
      }).join(''));
      const payload = JSON.parse(jsonPayload);
      return payload.role;
    } catch (e) {
      return null;
    }
  },

  initiateGoogleLogin: () => {
    // Redirect to backend OAuth2 endpoint which will redirect to Google
    window.location.href = 'http://localhost:9191/oauth2/authorization/google';
  },

  handleOAuthCallback: () => {
    // Extract token from URL query parameters (set by OAuth2LoginSuccessHandler)
    const params = new URLSearchParams(window.location.search);
    const token = params.get('token');
    if (token) {
      localStorage.setItem('user', token);
      return true;
    }
    return false;
  }
};