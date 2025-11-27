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
      const token = await authService.login(email, password);
      authService.saveToken(token);
      setSuccess('Login successful!');
      navigate('/dashboard');
    } catch (err) {
      // Parse error message to make it user-friendly
      let errorMessage = 'Login failed. Please check your credentials and try again.';

      if (typeof err === 'string') {
        // If it's a simple string error
        if (err.includes('not found') || err.includes('Invalid')) {
          errorMessage = 'Invalid email or password. Please try again.';
        } else if (err.includes('Validation')) {
          errorMessage = 'Please enter a valid email and password.';
        } else {
          errorMessage = err;
        }
      } else if (err.response?.data?.message) {
        // If it's an axios error with a message
        errorMessage = err.response.data.message;
      }

      setError(errorMessage);
    }
  };

  return { login, error, success };
};