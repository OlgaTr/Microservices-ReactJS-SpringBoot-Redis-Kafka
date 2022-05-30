import React, {useEffect, useState} from 'react';
import Button from 'react-bootstrap/Button';
import {useNavigate} from 'react-router-dom';
import {listCoffee} from "../api/CoffeeAPI";
import {addCoffeeToCoffeeOrder, createCoffeeOrder} from "../../coffeeOrders/api/CoffeeOrderAPI";

function CoffeeList() {
    const [coffeeList, setCoffeeList] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        listCoffee().then(response => setCoffeeList(response));
    }, [])

    function handleOrder(coffee) {
        let coffeeOrderId;
        createCoffeeOrder()
            .then(response => {
                coffeeOrderId = response.data;
                addCoffeeToCoffeeOrder(response.data, coffee.id)
                .then(() => navigate('/coffeePage', {state: {id: coffeeOrderId, coffee}}))});
    }

    const tableRows = coffeeList.map(coffee =>
        <tr key={coffee.id}>
            <td>{coffee.type}</td>
            <td>{coffee.price}</td>
            <td><Button onClick={() => handleOrder(coffee)}>Order</Button></td>
        </tr>
    );

    return (
        <div>
            <h3> Forget Love, Fall in Coffee</h3>
            <table>
                <thead>
                <tr>
                    <th>Coffee drink</th>
                    <th>Price</th>
                </tr>
                </thead>
                <tbody>
                {
                   tableRows
                }
                </tbody>
            </table>
        </div>
    )
}

export default CoffeeList;