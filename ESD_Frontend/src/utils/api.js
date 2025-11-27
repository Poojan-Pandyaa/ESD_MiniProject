import axios from 'axios';

// Base configuration for Axios
const apiClient = axios.create({
  baseURL: 'http://localhost:9191', // Base URL for the API
  headers: { 'Content-Type': 'application/json' },
});

// Helper to fetch students
export const fetchStudentsApi = async (token) => {
  const response = await apiClient.get('/student/', {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
};

// Helper to fetch filtered students
export const fetchFilteredStudentsApi = async (token, keyword) => {
  const response = await apiClient.get(`/student/${keyword}`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
};

// Helper for login
export const loginApi = async (email, password) => {
  const response = await apiClient.post('/api/v1/auth/login', { email, password });
  return response.data;
};
