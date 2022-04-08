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
        <div className='User'>
            <form>
                <input type="text" value={name} onChange={e => setName(e.target.value)} disabled={!edit && !register}/>
                <input type="number" step="0.001" value={pos_x} onChange={e => setPosX(e.target.value)} disabled={!edit && !register}/>
                <input type="number" step="0.001" value={pos_y} onChange={e => setPosY(e.target.value)} disabled={!edit && !register}/>
                {!register && <button type="button" onClick={() => setEdit(true)} disabled={edit}>Edit</button>}
                {!register && <button type="button" onClick={() => deleteUser()}>Delete</button>}
                {!register && <button type="button" onClick={() => patchUser()} disabled={!edit}>Save</button>}
                {register && <button type="button" onClick={() => createUser()}>Register</button>}
                {register && <button type="button" onClick={() => login()}>Login</button>}
                {!register && <button type="button" onClick={() => logout()}>Logout</button>}
            </form>
        </div>
    );
}

export default User;
