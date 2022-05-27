import React, {useEffect, useState} from "react";
import {useLocation, useNavigate} from "react-router-dom";
import {listToppings} from "../../toppings/api/ToppingAPI";
import {addToppingToCoffee} from "../../orders/api/CoffeeOrderAPI";
import {Button} from "react-bootstrap";

function Coffee() {
    const location = useLocation();
    const navigate = useNavigate();
    const coffeeType = location.state.coffee.type;
    const [toppings, setToppings] = useState([]);

    useEffect(() => {
        listToppings().then(response => setToppings(response));
    }, [])

    function handleClick() {
        navigate('/coffeeOrder');
    }

    const toppingsRows = toppings.map(topping =>
        <tr key={topping.id}>
            <td>{topping.type}</td>
            <td>{topping.price}</td>
            <td><Button onClick={() => {
                addToppingToCoffee(location.state.id, topping.id);
            }}>Add</Button></td>
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
                </tbody>
            </table>
            <Button onClick={handleClick}>Order Coffee</Button>
        </div>
    )
}

export default Coffee;