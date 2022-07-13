import React, {useEffect, useState} from "react";
import {getOrderById} from "../api/OrderAPI";
import {useSelector} from "react-redux";
import {useLocation} from "react-router-dom";

export default function OrderConfirmation() {
    const location = useLocation();
    const username = useSelector(state => state.justCoffee.user.username);
    const password = useSelector(state => state.justCoffee.user.password);
    const [order, setOrder] = useState({orderItems: [], totalPrice: 0});
    const orderId = location.state.orderId;

    useEffect(() => {
        getOrderById(username, password, orderId)
            .then(response => {
                setOrder(response.data)
            })}, []);

    const rows = order.orderItems.map((orderItem, index) =>
        <tr key={index}>
            <td>{orderItem.description}</td>
            <td>{orderItem.price}</td>
        </tr>
    );

    return (
        <>
            <div className='order'>
                <div className='order-confirmation-header'>
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
                <div className='coffeePage-floor'>
                    <h4 className="coffee-drink">Total: {order.totalPrice}</h4>
                </div>
                <div className='order-citation'>
                    <p>Coffee is a hug in a mug.</p>
                </div>
            </div>
        </>
    )
};