import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { authService } from '../services/authService';

export const useAuth = () => {
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const navigate = useNavigate();

  const login = async (email, password) => {
    setError('');
    setSuccess('');

    try {
      const response = await authService.login(email, password);
      // Token is already saved in authService.login, just navigate
      setSuccess('Login successful!');
      setTimeout(() => {
        navigate('/placement');
      }, 500);
    } catch (err) {
      // Parse error message to make it user-friendly
      let errorMessage = 'Login failed. Please check your credentials and try again.';

      if (typeof err === 'string') {
        errorMessage = err;
      } else if (err.response?.data?.message) {
        errorMessage = err.response.data.message;
      }

      setError(errorMessage);
    }
  };

  return { login, error, success };
};