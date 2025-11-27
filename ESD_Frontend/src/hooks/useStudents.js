import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { studentService } from '../services/studentService';

export const useStudentList = () => {
  const [students, setStudents] = useState([]);
  const [error, setError] = useState('');
  const [filterKeyword, setFilterKeyword] = useState('');
  const [filteredStudents, setFilteredStudents] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetchStudents();
  }, []);

  const fetchStudents = async () => {
    try {
      const studentData = await studentService.fetchStudents();
      setStudents(studentData);
    } catch (err) {
      console.error('Error fetching students:', err);
      setError(err);
      if (err.includes('Unauthorized')) {
        navigate('/login');
      }
    }
  };

  const fetchFilteredStudents = async () => {
    try {
      const filteredData = await studentService.fetchFilteredStudents(filterKeyword);
      setFilteredStudents(filteredData);
      setError('');
    } catch (err) {
      console.error('Error fetching filtered students:', err);
      setError(err);
      if (err.includes('Unauthorized')) {
        navigate('/login');
      }
    }
  };

  return {
    students,
    filteredStudents,
    error,
    filterKeyword,
    setFilterKeyword,
    fetchFilteredStudents
  };
};