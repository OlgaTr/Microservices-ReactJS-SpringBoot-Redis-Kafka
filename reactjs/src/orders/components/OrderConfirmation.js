import React, {useEffect, useState} from "react";
import {getCoffeeDrinksByOrderId, getOrderById} from "../api/OrderAPI";
import {useSelector} from "react-redux";
import {useLocation} from "react-router-dom";

export default function OrderConfirmation() {
    const location = useLocation();
    const username = useSelector(state => state.justCoffee.user.username);
    const password = useSelector(state => state.justCoffee.user.password);
    const [coffeeDrinks, setCoffeeDrinks] = useState([]);
    const orderId = location.state.orderId;

    useEffect(() => {
        getCoffeeDrinksByOrderId(username, password, orderId)
            .then(response => {
                setCoffeeDrinks(response.data)
                console.log(response.data)
            })}, []);

    const rows = coffeeDrinks.map(coffeeDrink =>
        <tr key={coffeeDrink.id}>
            <td>{coffeeDrink.description}</td>
        </tr>
    );

    return (
        <>
            <div className='order'>
                <div className='order-header'>
                    <p> Just Coffee</p>
                </div>
                <div className='order-note'>
                    <p>Thank you for your order!</p>
                </div>
                <div className='order-details'>
                    <div className='order-details-header'>
                        <p>Order details</p>
                    </div>
                    <table>
                        <tbody>{rows}</tbody>
                    </table>
                </div>
                <div className='order-citation'>
                    <p>Coffee is a hug in a mug.</p>
                </div>
            </div>
        </>
    )
};