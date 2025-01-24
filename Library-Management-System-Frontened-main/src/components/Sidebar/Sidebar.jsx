import React from 'react';
import { Link } from 'react-router-dom'; // Import Link from React Router
import './Sidebar.css'; // Importing the CSS file

const Sidebar = () => {
  return (
    <div className="sidebar">
      <ul className="sidebar-menu">
        <li className="menu-item">
          <Link to="/profile" className="menu-link">Profile</Link>
        </li>
        <li className="menu-item">
          <Link to="/issue-book" className="menu-link">Issue Book</Link>
        </li>
        <li className="menu-item">
          <Link to="/return-book" className="menu-link">Return Book</Link>
        </li>
        <li className="menu-item">
          <Link to="/logout" className="menu-link">Logout</Link>
        </li>
      </ul>
    </div>
  );
};

export default Sidebar;
