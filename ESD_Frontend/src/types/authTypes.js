import axios from 'axios';

const BASE_URL = 'http://localhost:9191/api/v1/auth';

export const authService = {
  login: async (email, password) => {
    try {
      const response = await axios.post(`${BASE_URL}/login`, {
        email,
        password,
      });
      return response.data;
    } catch (err) {
      throw err.response?.data?.message || 'Failed to login. Please try again.';
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
  }
};