import { useState, useEffect } from 'react';
import './App.css';
import Hashpost from './Hashpost';
import Geohash from './Geohash';
import User from './User';

function App() {

    const [posts, setPosts] = useState([])
    const [geohash, setGeohash] = useState([])
    const [user, setUser] = useState(-1)
    const [pos, setPos] = useState([])



    useEffect(() => {
        fetch("/api/hashpost").then(response => response.json()).then(data => setPosts(data));
        
        console.log(user);
        var test;
        if(user < 0){
            navigator.geolocation.getCurrentPosition(function(position) {
                setPos([position.coords.latitude, position.coords.longitude]);
                test = position.coords.latitude;
            });
        }else{
            fetch("/api/users/" + user).then(response => response.json()).then(data => setPos([data.pos_x, data.pos_y]));
        }
    }, [user])

    useEffect(() => {
        if (pos[0]){
            var isoDate = new Date().toISOString().substring(0,10);
            fetch("/api/geohash/" + isoDate + "/" + Math.trunc(parseFloat(pos[0])) + "/" + Math.trunc(parseFloat(pos[1]))).then(response => response.json()).then(data => setGeohash(data));
        }
    }, [pos])


    return (
        <div className="App">
            <div className='ContainerHashPost'>
                {posts.map(post => (
                    <Hashpost props={post} />
                ))}
            </div>
            <Geohash hash={geohash} user={user}/>
            <User id={user} setUser={setUser}/>
        </div>
    );
}

export default App;
//