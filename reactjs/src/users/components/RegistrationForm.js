import React, {useState} from 'react';
import {registerNewUser} from '../api/UserAPI';
import {useNavigate} from "react-router-dom";
import {useDispatch} from "react-redux";
import {signIn} from "../../app/userSlice";
import CustomAlert from "../../utils/components/CustomAlert";

function RegistrationForm() {
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const [user, setUser] = useState({username: '', password: ''});
    const [renderAlert, setRenderAlert] = useState();

    function validateInput(event) {
        event.preventDefault();
        if (user.username === '' || user.password === '') {
            setRenderAlert(true);
        } else {
            dispatch(signIn(user));
            registerNewUser(user).then(navigate('/order'));
        }
    }

    return (
        <>
            <form className='login-form'>
                <h4>Join now</h4>
                <div className='form-row'>
                    <label>Name</label>
                    <input type='text' onChange={event => setUser({...user, username: event.target.value})}/>
                </div>
                <div className='form-row'>
                    <label>Password</label>
                    <input type='password' onChange={event => setUser({...user, password: event.target.value})}/>
                </div>
                <div className='form-row'>
                    <button onClick={event => validateInput(event)} className='custom-button'>submit</button>
                </div>
            </form>
            <CustomAlert renderAlert={renderAlert} closeAlert={() => setRenderAlert(false)}>
                Fields cannot be empty.
            </CustomAlert>
        </>
    )
}

export default RegistrationForm;
