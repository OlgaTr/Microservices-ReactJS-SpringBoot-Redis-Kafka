import React, {useState} from 'react';
import {useNavigate} from "react-router-dom";
import {authenticateUser} from "../api/UserAPI";
import {useDispatch} from "react-redux";
import {signIn} from "../../app/userSlice";
import CustomAlert from "../../utils/components/CustomAlert";

export default function SignInForm() {
    const [user, setUser] = useState({username: '', password: ''});
    const [wrongCredentials, setWrongCredentials] = useState();
    const navigate = useNavigate();
    const dispatch = useDispatch();

    function signInUser(event) {
        event.preventDefault();
        authenticateUser(user)
            .then(response => {
                if (response.data) {
                    dispatch(signIn(user));
                    navigate('/order');
                } else {
                    setWrongCredentials(true);
                }
            })
    }

    return (
        <div>
            <form className='login-form'>
                <h4 className='login-header'>Sign in</h4>
                <div className='form-row'>
                    <label>Name</label>
                    <input type='text' onChange={event => setUser({...user, username: event.target.value})}/>
                </div>
                <div className='form-row'>
                    <label>Password</label>
                    <input type='password' onChange={event => setUser({...user, password: event.target.value})}/>
                </div>
                <div className='form-row'>
                    <button onClick={event => signInUser(event)} className='custom-button'>submit</button>
                </div>
            </form>
            <CustomAlert renderAlert={wrongCredentials} closeAlert={() => setWrongCredentials(false)}>
                Wrong credentials.
            </CustomAlert>
        </div>
    )
}