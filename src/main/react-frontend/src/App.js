import React, {useState, useEffect} from 'react';
import logo from './logo.svg';
import './App.css';
import axios from 'axios';

const UserProfiles = () => {
  const fetchUserProfiles = () => {
      axios.get("http://localhost:8080/api/v1/user-profile").then(response => {
        console.log("Response from API:", response);
      });
  }

  useEffect(() => {
    fetchUserProfiles();
  }, []);
  return "Hello from UserProfiles";
}

function App() {
  return (
    <div className="App">
      <UserProfiles />
    </div>
  );
}

export default App;
