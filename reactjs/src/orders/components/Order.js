import React, {useEffect, useState} from 'react';
import {useLocation, useNavigate} from "react-router-dom";
import {getCoffeeDrinksByOrderId} from "../api/OrderAPI";

function Order() {
    const location = useLocation();
    const navigate = useNavigate();
    // const orderId = location.state.id;
    const [coffeeOrders, setCoffeeOrders] = useState([]);

    useEffect(() => {
        getCoffeeDrinksByOrderId(location.state.id).then(response => setCoffeeOrders(response.data));
    }, [])

    const tableRows = coffeeOrders.map(coffeeOrder =>
        <tr key={coffeeOrder.id}>
            <td>{coffeeOrder.description}</td>
            <td>{coffeeOrder.price}</td>
        </tr>);

    return (
        <div>
            <h3>Delicious Order</h3>
            <table>
                <thead>
                <tr>
                    <th>Coffee drink</th>
                    <th>Price</th>
                    <th />
                </tr>
                </thead>
                <tbody>
                {tableRows}
                </tbody>
            </table>
        </div>
    );
}

export default Order;