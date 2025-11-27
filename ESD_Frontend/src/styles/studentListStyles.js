export const studentListStyles = {
    backgroundContainer: {
      position: 'relative',
      minHeight: '100vh',
      backgroundImage: 'url("https://upload.wikimedia.org/wikipedia/en/thumb/f/f8/IIIT_Bangalore_Logo.svg/1200px-IIIT_Bangalore_Logo.svg.png")',
      backgroundSize: 'cover',
      backgroundPosition: 'center',
      backgroundAttachment: 'fixed',
    },
  
    overlay: {
      position: 'absolute',
      top: 0,
      left: 0,
      right: 0,
      bottom: 0,
      backgroundColor: 'rgba(0, 0, 0, 0.4)', // Darker overlay for readability
      backdropFilter: 'blur(10px)', // Blur effect to improve contrast
      zIndex: 1,
    },
  
    container: {
      display: 'flex',
      flexDirection: 'column',
      justifyContent: 'center',
      alignItems: 'center',
      width: '100%',
      height: '100vh',
      padding: '20px',
      position: 'absolute',
      zIndex: 2,
    },
  
    heading: {
      color: '#FFFFFF',
      fontSize: '32px',
      marginBottom: '30px',
      textAlign: 'center',
      fontWeight: 'bold',
      letterSpacing: '2px',
    },
  
    subHeading: {
      color: '#1A5F7A',
      fontSize: '24px',
      marginBottom: '20px',
      fontWeight: 'bold',
      textAlign: 'center',
    },
  
    errorMessage: {
      color: '#D8000C',
      backgroundColor: '#FFBABA',
      border: '1px solid #D8000C',
      padding: '10px',
      marginBottom: '20px',
      borderRadius: '5px',
      textAlign: 'center',
    },
  
    filterContainer: {
      marginBottom: '30px',
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
    },
  
    filterInput: {
      padding: '10px',
      marginRight: '10px',
      width: '300px',
      borderRadius: '4px',
      border: '1px solid #1A5F7A',
      fontSize: '16px',
    },
  
    filterButton: {
      padding: '10px 20px',
      backgroundColor: '#1A5F7A',
      color: 'white',
      border: 'none',
      borderRadius: '4px',
      cursor: 'pointer',
      transition: 'background-color 0.3s ease',
      fontSize: '16px',
    },
  
    table: {
      width: '100%',
      borderCollapse: 'collapse',
      backgroundColor: 'white',
      boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
      borderRadius: '8px',
      overflow: 'hidden',
    },
  
    tableHeader: {
      backgroundColor: '#1A5F7A',
      color: 'white',
    },
  
    tableHeaderCell: {
      padding: '12px',
      textAlign: 'center',
      fontWeight: 'bold',
    },
  
    tableRow: {
      borderBottom: '1px solid #ddd',
      transition: 'background-color 0.3s ease',
    },
  
    tableCell: {
      padding: '12px',
      textAlign: 'center',
    },
  
    noDataMessage: {
      color: '#666',
      backgroundColor: 'white',
      padding: '20px',
      borderRadius: '8px',
      textAlign: 'center',
      boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
    },
  };
  