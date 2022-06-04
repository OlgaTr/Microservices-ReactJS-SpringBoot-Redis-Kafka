import React, {useEffect, useState} from 'react';
import {useNavigate} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {clearAll, deleteCoffee} from "../../app/coffeeDrinksSlice";
import {getCoffeeDrinksById} from "../../coffeeOrders/api/CoffeeOrderAPI";
import {Button} from "react-bootstrap";
import {createOrder} from "../api/OrderAPI";

function Order() {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const coffeeDrinksId = useSelector(state => state.coffeeDrinks);
    const [coffeeOrders, setCoffeeOrders] = useState([]);

    useEffect(() => {
        getCoffeeDrinksById(coffeeDrinksId).then(response => setCoffeeOrders(response.data));
    })

    function handleDelete(coffeeOrderId) {
        dispatch(deleteCoffee(coffeeOrderId));
    }

    function handleProceedToCheckout() {
        createOrder(coffeeDrinksId);
        dispatch(clearAll());
        navigate("/");
    }

    const tableRows = coffeeOrders.map(coffeeOrder =>
        <tr key={coffeeOrder.id}>
            <td>{coffeeOrder.description}</td>
            <td>{coffeeOrder.price}</td>
            <td><Button onClick={() => handleDelete(coffeeOrder.id)} className="button">Delete</Button></td>
        </tr>);

    return (
        <div className='general'>
            <div className='header-container'>
                <h2>Delicious Order</h2>
            </div>
            <table>
                <thead>
                <tr>
                    <th>Coffee drink</th>
                    <th>Price</th>
                    <th />
                    <th />
                </tr>
                </thead>
                <tbody>
                {tableRows}
                </tbody>
            </table>
            <Button onClick={() => navigate("/")} className="button">Back to Coffee Menu</Button>
            <Button onClick={() => handleProceedToCheckout()} className="button checkout">Proceed to Checkout</Button>
        </div>
    );
}

export default Order;