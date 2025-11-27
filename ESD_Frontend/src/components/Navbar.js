import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

const Navbar = () => {
    const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem("user");
        navigate("/login");
    };



    return (
        <nav className="app-navbar">
            <Link to="/placement" className="nav-brand">
                <span style={{
                    fontSize: '1.4rem',
                    fontWeight: '700',
                    color: 'white',
                    letterSpacing: '-0.5px'
                }}>
                    Placement History
                </span>
            </Link>

            <div className="nav-menu">
                {/* Menu items removed as requested for simplicity, or keep if needed */}
            </div>

            <div style={{ display: 'flex', alignItems: 'center', gap: '15px' }}>
                <button
                    onClick={handleLogout}
                    style={{
                        background: '#ef4444',
                        border: 'none',
                        color: 'white',
                        cursor: 'pointer',
                        fontSize: '0.9rem',
                        padding: '8px 16px',
                        borderRadius: '6px',
                        fontWeight: '500',
                        transition: 'background-color 0.2s'
                    }}
                    onMouseOver={e => e.currentTarget.style.background = '#dc2626'}
                    onMouseOut={e => e.currentTarget.style.background = '#ef4444'}
                >
                    Logout
                </button>
            </div>
        </nav>
    );
};

export default Navbar;
