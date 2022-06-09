import React, {useState} from 'react';
import {registerNewUser} from '../api/UserAPI';
import {useNavigate} from "react-router-dom";
import {AiOutlineClose} from "react-icons/ai";
import {useDispatch} from "react-redux";
import {signIn} from "../../app/userSlice";

function WrongCredentials({wrongCredentials, closeAlert}) {
    if (wrongCredentials) {
        return (<>
                <AiOutlineClose onClick={() => closeAlert()}/>
                <p>Fields can not be empty!</p>
            </>
        )
    }
}

function RegistrationForm() {
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const [user, setUser] = useState({username: '', password: ''});
    const [wrongCredentials, setWrongCredentials] = useState();

    function validateInput(event) {
        event.preventDefault();
        if (user.username === '' || user.password === '') {
            setWrongCredentials(true);
        } else {
            dispatch(signIn(user));
            registerNewUser(user).then(navigate('/order'));
        }
    }

    return (
        <div>
            <h3 className='login-header'>Join now</h3>
            <form className='login-form'>
                <div className='form-row'>
                    <label>Name</label>
                    <input type='text' onChange={event => setUser({...user, username: event.target.value})}/>
                </div>
                <div className='form-row'>
                    <label>Password</label>
                    <input type='password' onChange={event => setUser({...user, password: event.target.value})}/>
                </div>
                <div className='form-row'>
                    <button onClick={event => validateInput(event)}>submit</button>
                </div>
            </form>
            <WrongCredentials wrongCredentials={wrongCredentials} closeAlert={() => setWrongCredentials(false)}/>
        </div>
    )
}

export default RegistrationForm;
