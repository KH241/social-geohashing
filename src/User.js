import './User.css';
import './App.css';
import { useEffect, useState } from 'react';


function User(props) {

    const [edit, setEdit] = useState(false);
    const [register, setRegister] = useState(false);

    const [user, setUser] = useState(props.id);
    const [name, setName] = useState("");
    const [pos_x, setPosX] = useState(null);
    const [pos_y, setPosY] = useState(null);

    useEffect(() => {
        fetch("/api/users/" + user).then(response => response.json()).then(data => (
            setName(data.name),
            setPosX(data.pos_x),
            setPosY(data.pos_y)
        ));

        console.log(user);

        setRegister(user < 0);
    }, [user])

    const deleteUser = () => {
        fetch("/api/users/" + user, {
            method: "DELETE"
        })

        logout();
    }

    const patchUser = () => {
        let data = new FormData();
        data.append("name", name)
        data.append("pos_x", pos_x)
        data.append("pos_y", pos_y)
        fetch("/api/users/" + user, {
            method: "PUT",
            body: data
        }).then(res => console.log(res))

        setEdit(false);
    }

    const createUser = () => {
        let data = new FormData();
        data.append("name", name)
        data.append("pos_x", pos_x)
        data.append("pos_y", pos_y)
        fetch("/api/users", {
            method: "POST",
            body: data
        }).then(response => response.json()).then(data => {
            setUser(data.id)
            props.setUser(data.id)
        });
        setRegister(false);
    }

    const login = () => {
        fetch("/api/users/name/" + name).then(response => response.json()).then(data => {
            setUser(data.id)
            props.setUser(data.id)
        });
        setRegister(false);
    }

    const logout = () => {
        setUser(-1)
        props.setUser(-1)
        setName("");
        setPosX("");
        setPosY("");
        setRegister(true);
        setEdit(false);
    }

    return (
        <form className='User'>
            <input className="button" id="name" type="text" placeholder="Name" value={name} onChange={e => setName(e.target.value)} disabled={!edit && !register}/>
            <input className="button" id="latInput" type="number" placeholder="Latitude" step="0.001" value={pos_x} onChange={e => setPosX(e.target.value)} disabled={!edit && !register}/>
            <input className="button" id="longInput" type="number" placeholder="Longitude" step="0.001" value={pos_y} onChange={e => setPosY(e.target.value)} disabled={!edit && !register}/>
            {!register && !edit && <button className="button" id="edit" type="button" onClick={() => setEdit(true)}>Edit</button>}
            {!register && <button className="button" id="delete" type="button" onClick={() => deleteUser()}>Delete</button>}
            {!register && edit && <button className="button" id="save" type="button" onClick={() => patchUser()}>Save</button>}
            {register && <button className="button" id="create" type="button" onClick={() => createUser()}>Register</button>}
            {register && <button className="button" id="login" type="button" onClick={() => login()}>Login</button>}
            {!register && <button className="button" id="logout" type="button" onClick={() => logout()}>Logout</button>}
        </form>
    );
}

export default User;
