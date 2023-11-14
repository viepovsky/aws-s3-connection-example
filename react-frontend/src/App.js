import React, {useState, useEffect, useCallback} from 'react';
import {useDropzone} from 'react-dropzone'

import logo from './logo.svg';
import './App.css';
import axios from 'axios';

const CarProfiles = () => {
  const [carProfiles, setCarProfiles] = useState([]);

  const fetchCarProfiles = () => {
      axios.get("http://localhost:8080/api/v1/car-profile").then(response => {
        console.log("Response from API:", response);
        setCarProfiles(response.data);
      });
  }

  useEffect(() => {
    fetchCarProfiles();
  }, []);

  return carProfiles.map((carProfile, index) => {
    return (
      <div key={index}>

        <br/>
        <br/>
        <h1>{carProfile.brand} {carProfile.model}</h1>
        <p>{carProfile.carProfileId}</p>
        <Dropzone {...carProfile}/>
        <br/>
      </div>
      // mozna tez tak <Dropzone carProfileId={carProfile.carProfileId}/>
    )
  });
}

function Dropzone({carProfileId}) {
  const onDrop = useCallback(acceptedFiles => {
    const file = acceptedFiles[0];
    console.log(file);

    const formData = new FormData();
    formData.append("file", file);

    axios.post(`http://localhost:8080/api/v1/car-profile/${carProfileId}/image/upload`, 
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data"
        }
      }
    ).then(() => {
      console.log("Success uploading files");
    }).catch(error => {
      console.log(error);
    });
  }, []);

  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {
        isDragActive ?
          <p>Drop the image here ...</p> :
          <p>Drag 'n' drop car profile image, or click to select profile image</p>
      }
    </div>
  )
}

function App() {
  return (
    <div className="App">
      <CarProfiles />
    </div>
  );
}

export default App;
