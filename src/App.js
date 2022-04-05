import { useState, useEffect } from 'react';
import './App.css';
import Hashpost from './Hashpost';

function App() {

  const[posts, setPosts] = useState([])

  useEffect(() => {
    fetch("/api/hashpost")
        .then(response => response.json())
        .then(data => setPosts(data));
  }, [])


  return (
    <div className="App">
      {posts.map(post => (
        <Hashpost props={post}/>
      ))}
    </div>
  );
}

export default App;
