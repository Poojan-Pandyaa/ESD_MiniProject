import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { studentService } from '../services/studentService';
import { authService } from '../services/authService';
import '../styles/PlacementHistory.css';

function PlacementHistory() {
    const [allStudents, setAllStudents] = useState([]);
    const [displayedStudents, setDisplayedStudents] = useState([]);

    // Filters
    const [searchKeyword, setSearchKeyword] = useState('');
    const [filters, setFilters] = useState({
        status: 'all',
        year: 'all',
        domain: 'all',
        org: 'all'
    });

    // Filter Options
    const [options, setOptions] = useState({
        years: [],
        domains: [],
        orgs: []
    });

    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');
    const [sortOrder, setSortOrder] = useState('asc');

    // Modal State
    const [selectedStudent, setSelectedStudent] = useState(null);


    const [userRole, setUserRole] = useState(null);

    const navigate = useNavigate();

    useEffect(() => {
        const role = authService.getRole();
        setUserRole(role);
    }, []);

    // Load Data Function
    const loadData = React.useCallback(async () => {
        try {
            setLoading(true);
            const data = await studentService.fetchFilteredStudents('');
            setAllStudents(data);
            setDisplayedStudents(data);
            extractOptions(data);
        } catch (err) {
            console.error("Fetch error:", err);

            // Check for auth errors (401 Unauthorized or 403 Forbidden)
            if (err.response && (err.response.status === 401 || err.response.status === 403)) {
                localStorage.removeItem("user"); // Clear invalid token
                navigate('/login');
                return;
            }

            const errorMsg = err.response?.data?.message || err.message || 'Failed to load placement records.';
            setError(errorMsg);
        } finally {
            setLoading(false);
        }
    }, [navigate]);

    useEffect(() => {
        loadData();
    }, [loadData]);

    const extractOptions = (data) => {
        const years = new Set();
        const domains = new Set();
        const orgs = new Set();

        data.forEach(s => {
            if (s[5]) years.add(s[5]); // Year
            if (s[2]) domains.add(s[2]); // Domain
            if (s[3]) orgs.add(s[3]); // Placement Org
            if (s[4]) orgs.add(s[4]); // Alumni Org
        });

        setOptions({
            years: Array.from(years).sort((a, b) => b - a),
            domains: Array.from(domains).sort(),
            orgs: Array.from(orgs).sort()
        });
    };

    // Filter Logic
    useEffect(() => {
        let result = [...allStudents];

        // Search
        if (searchKeyword.trim()) {
            const lowerKey = searchKeyword.toLowerCase();
            result = result.filter(s =>
                (s[0] + ' ' + s[1]).toLowerCase().includes(lowerKey) || // Name
                (s[3] || '').toLowerCase().includes(lowerKey) || // Org
                (s[2] || '').toLowerCase().includes(lowerKey) // Domain
            );
        }

        // Dropdown Filters
        if (filters.status !== 'all') {
            result = result.filter(s =>
                filters.status === 'placed' ? s[7] === 'Placed' : s[7] === 'Unplaced'
            );
        }
        if (filters.year !== 'all') {
            result = result.filter(s => String(s[5]) === String(filters.year));
        }
        if (filters.domain !== 'all') {
            result = result.filter(s => s[2] === filters.domain);
        }
        if (filters.org !== 'all') {
            result = result.filter(s => s[3] === filters.org || s[4] === filters.org);
        }

        // Filter out unplaced students if sorting by CTC
        if (sortOrder.includes('ctc')) {
            result = result.filter(s => s[7] === 'Placed');
        }

        // Sorting
        result.sort((a, b) => {
            if (sortOrder === 'ctc-desc') return (parseFloat(b[8]) || 0) - (parseFloat(a[8]) || 0);
            if (sortOrder === 'ctc-asc') return (parseFloat(a[8]) || 0) - (parseFloat(b[8]) || 0);

            const nameA = (a[0] + ' ' + a[1]).toLowerCase();
            const nameB = (b[0] + ' ' + b[1]).toLowerCase();
            return sortOrder === 'asc' ? nameA.localeCompare(nameB) : nameB.localeCompare(nameA);
        });

        setDisplayedStudents(result);
    }, [searchKeyword, filters, sortOrder, allStudents]);

    const handleFilterChange = (key, value) => {
        setFilters(prev => ({ ...prev, [key]: value }));
    };



    const handleRowClick = (student) => {
        if (student[3]) { // Only if placed/has org
            setSelectedStudent({
                name: `${student[0]} ${student[1]}`,
                org: student[3],
                ctc: student[8],
                role: 'SDE' // Placeholder or derived if available
            });
        }
    };

    if (loading) return <div className="loading-state">Loading records...</div>;
    if (error) return <div className="error-state">{error}</div>;

    return (
        <div className="ph-container">
            <div className="ph-header">
                <div>
                    <h1>Placement History</h1>
                    <p>Track student placements and alumni network</p>
                </div>
                {userRole === 'placement' && (
                    <div className="ph-stats-mini">
                        <div className="stat-pill">
                            <span className="label">Total Students</span>
                            <span className="value">{allStudents.length}</span>
                        </div>
                        <div className="stat-pill success">
                            <span className="label">Placed</span>
                            <span className="value">{allStudents.filter(s => s[7] === 'Placed').length}</span>
                        </div>
                    </div>
                )}

            </div>



            {
                userRole === 'placement' ? (
                    <>
                        <div className="ph-controls">
                            <div className="search-bar">
                                <i className="search-icon">🔍</i>
                                <input
                                    type="text"
                                    placeholder="Search students, companies..."
                                    value={searchKeyword}
                                    onChange={e => setSearchKeyword(e.target.value)}
                                />
                            </div>

                            <div className="filters-row">
                                <select value={filters.year} onChange={e => handleFilterChange('year', e.target.value)}>
                                    <option value="all">Year: All</option>
                                    {options.years.map(y => <option key={y} value={y}>{y}</option>)}
                                </select>

                                <select value={filters.domain} onChange={e => handleFilterChange('domain', e.target.value)}>
                                    <option value="all">Domain: All</option>
                                    {options.domains.map(d => <option key={d} value={d}>{d}</option>)}
                                </select>

                                <select value={filters.org} onChange={e => handleFilterChange('org', e.target.value)}>
                                    <option value="all">Org: All</option>
                                    {options.orgs.map(o => <option key={o} value={o}>{o}</option>)}
                                </select>

                                <select value={filters.status} onChange={e => handleFilterChange('status', e.target.value)}>
                                    <option value="all">Status: All</option>
                                    <option value="placed">Placed</option>
                                    <option value="unplaced">Unplaced</option>
                                </select>

                                <select value={sortOrder} onChange={e => setSortOrder(e.target.value)} className="sort-select">
                                    <option value="asc">Sort: Name (A-Z)</option>
                                    <option value="desc">Sort: Name (Z-A)</option>
                                    <option value="ctc-desc">Sort: CTC (High-Low)</option>
                                </select>
                            </div>
                        </div>

                        <div className="ph-table-wrapper">
                            <table className="ph-table">
                                <thead>
                                    <tr>
                                        <th>Student Name</th>
                                        <th>Program</th>
                                        <th>Organization</th>
                                        <th>Year</th>
                                        <th>Status</th>
                                        <th>CTC</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {displayedStudents.length > 0 ? (
                                        displayedStudents.map((s, idx) => (
                                            <tr key={idx} onClick={() => handleRowClick(s)} className={s[3] ? 'clickable' : ''}>
                                                <td className="name-cell">
                                                    <div className="avatar">{s[0][0]}</div>
                                                    <div>
                                                        <div className="name">{s[0]} {s[1]}</div>
                                                        <div className="sub-text">{s[6] === 'Yes' ? 'Alumni' : 'Student'}</div>
                                                    </div>
                                                </td>
                                                <td><span className="badge-domain">{s[2]}</span></td>
                                                <td className="org-cell">{s[3] || s[4] || '-'}</td>
                                                <td>{s[5]}</td>
                                                <td>
                                                    <span className={`status-dot ${s[7] === 'Placed' ? 'placed' : 'unplaced'}`}></span>
                                                    {s[7]}
                                                </td>
                                                <td className="ctc-cell">
                                                    {s[8] && parseFloat(s[8]) > 0 ? `${s[8]} LPA` : '-'}
                                                </td>
                                            </tr>
                                        ))
                                    ) : (
                                        <tr>
                                            <td colSpan="6" className="no-results">No records found.</td>
                                        </tr>
                                    )}
                                </tbody>
                            </table>
                        </div>
                    </>
                ) : (
                    <div style={{ textAlign: 'center', padding: '4rem 2rem', background: 'white', borderRadius: '12px', marginTop: '2rem', boxShadow: '0 2px 4px rgba(0,0,0,0.05)' }}>
                        <div style={{ fontSize: '3rem', marginBottom: '1rem' }}>🔒</div>
                        <h2 style={{ color: '#374151', marginBottom: '0.5rem' }}>Restricted Access</h2>
                        <p style={{ color: '#6b7280', maxWidth: '500px', margin: '0 auto' }}>
                            Detailed placement records are only available to Placement Coordinators.
                            Please contact the administration if you believe this is an error.
                        </p>
                    </div>
                )
            }

            {
                selectedStudent && (
                    <div className="modal-overlay" onClick={() => setSelectedStudent(null)}>
                        <div className="modal-card" onClick={e => e.stopPropagation()}>
                            <div className="modal-header">
                                <h3>Placement Details</h3>
                                <button onClick={() => setSelectedStudent(null)}>×</button>
                            </div>
                            <div className="modal-content">
                                <div className="student-info">
                                    <h2>{selectedStudent.name}</h2>
                                    <p className="org-name">{selectedStudent.org}</p>
                                </div>
                                <div className="ctc-box">
                                    <label>Package Offered</label>
                                    <div className="amount">{selectedStudent.ctc} LPA</div>
                                </div>
                            </div>
                        </div>
                    </div>
                )
            }
        </div >
    );
}

export default PlacementHistory;
