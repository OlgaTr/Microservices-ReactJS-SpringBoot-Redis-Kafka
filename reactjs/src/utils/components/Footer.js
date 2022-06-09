import React from 'react';
import {RiShoppingBasketLine} from 'react-icons/ri';

export default function Footer() {
    return (
        <div className='basket'>
            <a href='/order'><RiShoppingBasketLine/></a>
        </div>
    )
}