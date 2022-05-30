import React, {useEffect, useState} from "react";
import {useLocation, useNavigate} from "react-router-dom";
import {listToppings} from "../../toppings/api/ToppingAPI";
import {addToppingsToCoffee} from "../../coffeeOrders/api/CoffeeOrderAPI";
import {Button} from "react-bootstrap";
import {addCoffeeOrderToOrder, createOrder} from "../../orders/api/OrderAPI";

function Coffee() {
    const location = useLocation();
    const navigate = useNavigate();
    const coffeeType = location.state.coffee.type;
    const coffeeOrderId = location.state.id;
    const [toppings, setToppings] = useState([]);
    const [price, setPrice] = useState(location.state.coffee.price);

    useEffect(() => {
        listToppings().then(response => setToppings(response));
    }, [])

    function handleChecked(event, topping) {
        topping.isChecked = event.target.checked;
        if (topping.isChecked) {
            setPrice(price => Math.round((price + topping.price) * 100)/100);
        } else {
            setPrice(price => Math.round((price - topping.price) * 100)/100);
        }
    }

    function handleClick() {
        let toppingsId = [];
        for (let topping of toppings) {
            if (topping.isChecked) {
                toppingsId.push(topping.id);
            }
        }
        let orderId;
        addToppingsToCoffee(coffeeOrderId, toppingsId)
            .then(createOrder()
                .then(response => {
                    orderId = response.data;
                    addCoffeeOrderToOrder(response.data, coffeeOrderId);
                    navigate('/order', {state: {id: orderId}})})
        );
    }

    const toppingsRows = toppings.map(topping =>
        <tr key={topping.id}>
            <td>{topping.type}</td>
            <td>{topping.price}</td>
            <td>
                <input
                    // name="checkbox"
                    type="checkbox"
                    onChange={event => handleChecked(event, topping)}/>
            </td>
        </tr>);

    return (
        <div>
            <h4>{coffeeType}</h4>
            <table>
                <thead>
                <tr>
                    <th>Toppings</th>
                    <th>Price</th>
                </tr>
                </thead>
                <tbody>
                {
                    toppingsRows
                }
                <tr>
                    <td>Price</td>
                    <td>{price}</td>
                </tr>
                </tbody>
            </table>
            <table>
                <tbody>

                </tbody>
            </table>
            <Button onClick={handleClick}>Order Coffee</Button>
        </div>
    )
}

export default Coffee;