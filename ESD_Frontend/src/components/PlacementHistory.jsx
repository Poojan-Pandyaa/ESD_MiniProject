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
            setAllStudents(data);//master copy, never filtered
            setDisplayedStudents(data);//filtered copy shown to user
            extractOptions(data);//to populate filter dropdowns
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
            if (s.graduation_year) years.add(s.graduation_year);
            if (s.program) domains.add(s.program);
            if (s.placement_org) orgs.add(s.placement_org);
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
                (s.first_name + ' ' + s.last_name).toLowerCase().includes(lowerKey) ||
                (s.placement_org || '').toLowerCase().includes(lowerKey) ||
                (s.program || '').toLowerCase().includes(lowerKey)
            );
        }

        // Dropdown Filters
        if (filters.status !== 'all') {
            result = result.filter(s =>
                filters.status === 'placed' ? s.placement_status === 'Placed' : s.placement_status === 'Unplaced'
            );
        }
        if (filters.year !== 'all') {
            result = result.filter(s => String(s.graduation_year) === String(filters.year));
        }
        if (filters.domain !== 'all') {
            result = result.filter(s => s.program === filters.domain);
        }
        if (filters.org !== 'all') {
            result = result.filter(s => s.placement_org === filters.org);
        }

        // Filter out unplaced students or students with no CTC if sorting by CTC
        if (sortOrder.includes('ctc')) {
            result = result.filter(s => s.placement_status === 'Placed' && s.ctc && parseFloat(s.ctc) > 0);
        }

        // Sorting
        result.sort((a, b) => {
            if (sortOrder === 'ctc-desc') return (parseFloat(b.ctc) || 0) - (parseFloat(a.ctc) || 0);
            if (sortOrder === 'ctc-asc') return (parseFloat(a.ctc) || 0) - (parseFloat(b.ctc) || 0);

            const nameA = (a.first_name + ' ' + a.last_name).toLowerCase();
            const nameB = (b.first_name + ' ' + b.last_name).toLowerCase();
            return sortOrder === 'asc' ? nameA.localeCompare(nameB) : nameB.localeCompare(nameA);
        });

        setDisplayedStudents(result);
    }, [searchKeyword, filters, sortOrder, allStudents]);

    const handleFilterChange = (key, value) => {
        setFilters(prev => ({ ...prev, [key]: value }));
    };



    const handleRowClick = (student) => {
        if (student.placement_org) { // Only if placed/has org
            setSelectedStudent({
                name: `${student.first_name} ${student.last_name}`,
                org: student.placement_org,
                ctc: student.ctc,
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
                            <span className="value">{allStudents.filter(s => s.placement_status === 'Placed').length}</span>
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
                                    <option value="ctc-asc">Sort: CTC (Low-High)</option>
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
                                            <tr key={idx} onClick={() => handleRowClick(s)} className={s.placement_org ? 'clickable' : ''}>
                                                <td className="name-cell">
                                                    <div className="avatar">{s.first_name[0]}</div>
                                                    <div>
                                                        <div className="name">{s.first_name} {s.last_name}</div>
                                                        <div className="sub-text">{s.is_alumni === 'Yes' ? 'Alumni' : 'Student'}</div>
                                                    </div>
                                                </td>
                                                <td><span className="badge-domain">{s.program}</span></td>
                                                <td className="org-cell">{s.placement_org || s.alumni_org || '-'}</td>
                                                <td>{s.graduation_year}</td>
                                                <td>
                                                    <span className={`status-dot ${s.placement_status === 'Placed' ? 'placed' : 'unplaced'}`}></span>
                                                    {s.placement_status}
                                                </td>
                                                <td className="ctc-cell">
                                                    {s.ctc && parseFloat(s.ctc) > 0 ? `${s.ctc} LPA` : '-'}
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
