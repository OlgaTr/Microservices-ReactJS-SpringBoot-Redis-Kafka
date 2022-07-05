import React from "react";
import {useDispatch, useSelector} from "react-redux";
import {logOut} from "../../app/userSlice";
import {useNavigate} from "react-router-dom";
import {clearAll} from "../../app/coffeeDrinksSlice";
import dandelion from '../../images/dandelion.png';

export default function NavigationBar() {

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const isAuthenticated = useSelector(state => state.justCoffee.user.isAuthenticated);
    // const username = useSelector(state => state.justCoffee.user.username);

    function handleLogOut() {
        dispatch(logOut());
        dispatch(clearAll());
        navigate('/');
    }

    const menu =
        <li className='dropdown'><span>Menu ▾</span>
            <ul className='sub-menu'>
                <li><a href='/'>Coffee</a></li>
            </ul>
        </li>

    const image = <image><img src={dandelion} alt='image' width="50px" height="50px"/></image>;

    if (!isAuthenticated) {
        return (
            <nav>
                <ul>
                    {menu}
                    <li><a href='/register'>Join now</a></li>
                    <li><a href='/login'>Sign in</a> </li>
                </ul>
                {image}
            </nav>
        );
    } else {
        return (
            <nav>
                <ul>
                    {menu}
                    <li>
                        <button onClick={() => handleLogOut()}>Log out</button>
                    </li>
                </ul>
                {image}
            </nav>
        );
    }
}