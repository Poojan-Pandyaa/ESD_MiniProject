import axios from 'axios';
import { authService } from './authService';

export const studentService = {
  fetchStudents: async () => {
    const token = authService.getToken();
    if (!token) {
      throw new Error('Unauthorized: No token found');
    }

    try {
      const response = await axios.get('http://localhost:9191/student/', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      return response.data;
    } catch (err) {
      throw err.response?.data?.message || 'Failed to fetch students';
    }
  },

  fetchFilteredStudents: async (filterKeyword) => {
    const token = authService.getToken();
    if (!token) {
      throw new Error('Unauthorized: No token found');
    }

    try {
      const response = await axios.get(`http://localhost:9191/student/${filterKeyword}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      return response.data;
    } catch (err) {
      throw err;
    }
  }
};