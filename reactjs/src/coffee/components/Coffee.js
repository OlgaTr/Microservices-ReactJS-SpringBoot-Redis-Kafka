import React, {useEffect, useState} from "react";
import {useLocation, useNavigate} from "react-router-dom";
import {listToppings} from "../../toppings/api/ToppingAPI";
import {createCoffeeOrder} from "../../coffeeOrders/api/CoffeeOrderAPI";
import {Button} from "react-bootstrap";
import {useDispatch, useSelector} from "react-redux";
import {add} from "../../app/coffeeDrinksSlice";

function Coffee() {
    const location = useLocation();
    const navigate = useNavigate();
    const coffeeType = location.state.coffee.type;
    const coffeeId = location.state.id;
    const [toppings, setToppings] = useState([]);
    const [price, setPrice] = useState(location.state.coffee.price);
    const dispatch = useDispatch();

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
        let coffeeDrinkId;
        createCoffeeOrder(coffeeId, toppingsId)
            .then(response => {
                coffeeDrinkId = response.data;
                dispatch(add(response.data));
                navigate('/order');
            })
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
        <div className="coffee">
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
                    <td><h4>{coffeeType}</h4></td>
                    <td><strong>{price}</strong></td>
                </tr>
                </tbody>
            </table>
            <Button onClick={handleClick} className="button">Order Coffee</Button>
        </div>
    )
}

export default Coffee;