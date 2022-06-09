import React, {useEffect, useState} from 'react';
import {useNavigate} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {AiOutlineClose} from 'react-icons/ai';
import {MdOutlineCancel} from 'react-icons/md';
import {deleteCoffee} from "../../app/coffeeDrinksSlice";
import {getCoffeeDrinksById} from "../../coffeeOrders/api/CoffeeOrderAPI";
import {createOrder} from "../api/OrderAPI";

function LoginAlert({show, closeAlert}) {
    if (show) {
        return (
            <div>
                <p>To place an order, please <a href="/register">join now</a> or <a href='/login'>sign in</a>
                    <AiOutlineClose onClick={() => closeAlert()}/>
                </p>
            </div>
        )
    }
}

export default function Order() {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const coffeeDrinksId = useSelector(state => state.coffeeDrinks.coffeeDrinks);
    const isAuthenticated = useSelector(state => state.user.isAuthenticated);
    const username = useSelector(state => state.user.username);
    const password = useSelector(state => state.user.password);
    const [coffeeOrders, setCoffeeOrders] = useState([]);
    let [show, setShow] = useState(false);
    const [render, performRerender] = useState({});

    useEffect(() => {
        getCoffeeDrinksById(coffeeDrinksId).then(response => setCoffeeOrders(response.data));
    }, [render])

    function handleDelete(coffeeOrderId) {
        dispatch(deleteCoffee(coffeeOrderId));
        performRerender({...render});
    }

    function handleOrder() {
        if (!isAuthenticated) {
            setShow(true);
        } else {
            createOrder(username, password, coffeeDrinksId)
                .then(() => navigate('/confirmation'));
        }
    }

    const tableRows = coffeeOrders.map(coffeeOrder =>
        <tr key={coffeeOrder.id}>
            <td>{coffeeOrder.description}</td>
            <td>{coffeeOrder.price}</td>
            <td><MdOutlineCancel onClick={() => handleDelete(coffeeOrder.id)}/></td>
        </tr>);

    return (
            <>
                <div>
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
                <button onClick={() => handleOrder()} className="button checkout">Proceed to Checkout</button>
                <LoginAlert show={show} closeAlert={() => setShow(false)}/>
            </>
    )
}