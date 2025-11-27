const styles = {
  backgroundContainer: {
    position: 'relative',
    minHeight: '100vh', 
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundImage: 'url("https://upload.wikimedia.org/wikipedia/en/thumb/f/f8/IIIT_Bangalore_Logo.svg/1200px-IIIT_Bangalore_Logo.svg.png")',
    backgroundSize: 'cover',
    backgroundPosition: 'center',
    backgroundAttachment: 'fixed',
    zIndex: 0, // Make sure this stays behind other elements
  },
  overlay: {
    position: 'absolute',
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
    backgroundColor: 'rgba(0, 0, 0, 0.5)', // Darker overlay for contrast
    backdropFilter: 'blur(8px)', // Blur effect
    zIndex: 1, // Ensures overlay is behind form but above the background image
  },
  container: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    width: '100%',
    height: '100vh', // Full viewport height
    padding: '20px', // Padding to prevent form from touching screen edges
    position: 'absolute', // Absolute position to cover the entire screen
    zIndex: 2, // Ensures form appears above the overlay
  },
  form: {
    backgroundColor: 'rgba(255, 255, 255, 0.9)', // Light background for form
    borderRadius: '8px',
    padding: '40px',
    boxShadow: '0 8px 16px rgba(0, 0, 0, 0.2)',
    width: '100%',
    maxWidth: '400px', // Fixed form width for readability
    textAlign: 'center',
  },
  heading: {
    color: '#1A5F7A',
    fontSize: '24px',
    marginBottom: '20px',
    fontWeight: 'bold',
    textTransform: 'uppercase',
    letterSpacing: '2px',
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
  successMessage: {
    color: '#4BB543',
    backgroundColor: '#DFF2BF',
    border: '1px solid #4BB543',
    padding: '10px',
    marginBottom: '20px',
    borderRadius: '5px',
    textAlign: 'center',
  },
  inputContainer: {
    marginBottom: '20px',
  },
  input: {
    width: '100%',
    padding: '12px',
    margin: '8px 0',
    border: '1px solid #ccc',
    borderRadius: '4px',
    fontSize: '16px',
    transition: 'border-color 0.3s ease',
  },
  button: {
    width: '100%',
    padding: '12px',
    backgroundColor: '#1A5F7A',
    color: 'white',
    border: 'none',
    borderRadius: '4px',
    fontSize: '16px',
    cursor: 'pointer',
    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.2)',
    transition: 'background-color 0.3s ease, transform 0.2s ease',
  },
  buttonHover: {
    backgroundColor: '#155f6e', // Darker shade of button color
    transform: 'scale(1.05)',
  },
  buttonActive: {
    backgroundColor: '#134c56', // Even darker shade on click
  },
};

export default styles;
