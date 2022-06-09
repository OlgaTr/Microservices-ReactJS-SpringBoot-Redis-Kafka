import React, {useState} from 'react';
import {Button} from "react-bootstrap";
import '../../styles/styles.css'
import {useNavigate} from "react-router-dom";

function SignInForm() {
    const [user, setUser] = useState({username: '', password: ''});
    const navigate = useNavigate();

    function handleSubmit(event) {
        navigate('/confirmation');
        event.preventDefault();
    }

    return (
        <div>
            <h3 className='login-header'>Sign in</h3>
            <form onSubmit={() => handleSubmit()} className='login-form'>
                <div className='form-row'>
                    <label>Name</label>
                    <input type='text'/>
                </div>
                <div className='form-row'>
                    <label>Password</label>
                    <input type='password'/>
                </div>
                <div className='form-row'>
                    <Button variant='Primary'>submit</Button>
                </div>
            </form>
        </div>
    )
}

export default SignInForm;