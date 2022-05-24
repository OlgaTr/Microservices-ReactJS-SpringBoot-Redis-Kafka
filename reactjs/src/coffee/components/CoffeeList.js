import React, {useEffect, useState} from 'react';
import Button from 'react-bootstrap';
import {useNavigate} from 'react-router-dom';
import {listCoffee} from "../api/CoffeeAPI";

function CoffeeList() {
    const [coffeeList, setCoffeeList] = useState([]);

    useEffect(() => {
        listCoffee().then(response => setCoffeeList(response));
    }, [])

    const tableRows = coffeeList.map((coffee) =>
        <tr key={coffee.id}>
            <td>{coffee.type}</td>
            <td>{coffee.price}</td>
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