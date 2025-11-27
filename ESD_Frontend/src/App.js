import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Login from "./components/Login.jsx";
import PlacementHistory from "./components/PlacementHistory.jsx";
import Navbar from "./components/Navbar";

// Component to protect routes that require authentication
const PrivateRoute = ({ children }) => {
  // Check for OAuth callback token in URL first
  const params = new URLSearchParams(window.location.search);
  const tokenFromUrl = params.get('token');

  if (tokenFromUrl) {
    // Save token from OAuth callback
    localStorage.setItem('user', tokenFromUrl);
    // Remove token from URL and redirect to clean URL
    window.history.replaceState({}, document.title, window.location.pathname);
  }

  const token = localStorage.getItem("user"); // Check if token exists

  if (!token) {
    return <Navigate to="/login" />;
  }

  return (
    <>
      <Navbar />
      <div className="app-container">
        {children}
      </div>
    </>
  );
};

function App() {
  return (
    <Router>
      <Routes>
        {/* Public Route for Login */}
        <Route path="/login" element={<Login />} />

        {/* Protected Route for Placement History (Main View) */}
        <Route
          path="/placement"
          element={
            <PrivateRoute>
              <PlacementHistory />
            </PrivateRoute>
          }
        />

        {/* Redirect root to placement history */}
        <Route path="/" element={<Navigate to="/placement" />} />

        {/* Redirect unknown paths to placement if logged in, else login */}
        <Route path="*" element={<Navigate to="/placement" />} />
      </Routes>
    </Router>
  );
}

export default App;
