import React from "react";

export default function NavigationBar() {
    return (
        <div className='navbar-container'>
            <div className='nav-menu'>
                <a href='/'>Menu</a>
            </div>
            <div className='nav-login'>
                <a href='/register'>Join in</a>
            </div>
            <div className='nav-login'>
                <a href='/login'>Sign in</a>
            </div>
        </div>
    )
}