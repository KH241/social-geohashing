import './Geohash.css';
import './App.css';
import { useEffect, useState } from 'react';


function Post(props){

}

function Geohash(props) {

    
    const [trip, setTrip] = useState("")
    const [image, setImage] = useState(null)

    const send = (e) => {
        let data = new FormData();
        data.append("user", props.user);
        data.append("geohash", hash.g_id);
        data.append("text", trip);
        data.append("image", image);
        fetch("/api/hashpost", {
            method: "POST",
            body: data
        }).then(res => console.log(res))

        setImage(null);
        setTrip("");
    }

    const hash = props.hash;
    return (
        <form className='Geohash'>
            <div className='lat'>{"Lat: " + hash.pos_x.toFixed(3)}</div> 
            <div className='long'>{"Long: " + hash.pos_y.toFixed(3)}</div>
            <input disabled={props.user == -1} className='imageUpload' id="upload" type="file" accept="image/png" onChange={(e) => {setImage(e.target.files[0]); e.target.value = null; }}/>
            <label for="upload" className='imageContainer'>
                {image && props.user > 0 && <img className='image' src={URL.createObjectURL(image)}/>}
                {!image && props.user > 0 && "Upload an Image"}
                {!image && props.user == -1 && "Log in to Post"}
            </label>
            <textarea className='trip' disabled={props.user == -1} placeholder='Describe your trip ...' value={trip} onChange={e => setTrip(e.target.value)}/>
            <button className='post' disabled={props.user == -1} type="button" onClick={() => send()}>Upload</button>
        </form>
    );
}

export default Geohash;
