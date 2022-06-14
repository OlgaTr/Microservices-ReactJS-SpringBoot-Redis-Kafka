import React, {useEffect, useState} from 'react';
import {useNavigate} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {MdOutlineCancel} from 'react-icons/md';
import {clearAll, deleteCoffee} from "../../app/coffeeDrinksSlice";
import {getCoffeeDrinksById} from "../../coffeeOrders/api/CoffeeOrderAPI";
import {createOrder} from "../api/OrderAPI";
import CustomAlert from "../../utils/components/CustomAlert";

export default function Order() {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const coffeeDrinksId = useSelector(state => state.justCoffee.coffeeDrinks);
    const isAuthenticated = useSelector(state => state.justCoffee.user.isAuthenticated);
    const username = useSelector(state => state.justCoffee.user.username);
    const password = useSelector(state => state.justCoffee.user.password);
    const [coffeeOrders, setCoffeeOrders] = useState([]);
    let [renderAlert, setRenderAlert] = useState(false);
    const [render, performRerender] = useState({});
    const message = <p>To place an order, please <a href="/register">join now</a> or <a href="/login">sign in</a></p>;
    // let filter = useSelector(state => {
    //     console.log('State: ', state);
    //     return state.pieChart.filter;
    // });

    useEffect(() => {
        getCoffeeDrinksById(coffeeDrinksId).then(response => setCoffeeOrders(response.data));
    }, [render])

    function handleDelete(coffeeOrderId) {
        dispatch(deleteCoffee(coffeeOrderId));
        performRerender({...render});
    }

    function handleOrder() {
        if (!isAuthenticated) {
            setRenderAlert(true);
        } else {
            createOrder(username, password, coffeeDrinksId)
                .then(() => {
                    dispatch(clearAll());
                    navigate('/confirmation');
                });
        }
    }

    const tableRows = coffeeOrders.map(coffeeOrder =>
        <tr key={coffeeOrder.id}>
            <td>{coffeeOrder.description}</td>
            <td>{coffeeOrder.price}</td>
            <td><MdOutlineCancel onClick={() => handleDelete(coffeeOrder.id)} className='icon'/></td>
        </tr>);

    return (
            <>
                <div className='header-container'>
                    <p>Delicious Order</p>
                </div>
                <div className='content-container'>
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
                </div>
                <div className='coffeePage-floor'>
                    <button onClick={() => handleOrder()}>Proceed to Checkout</button>
                    <CustomAlert renderAlert={renderAlert}
                                 closeAlert={() => setRenderAlert(false)}
                                 message={message}/>
                </div>
            </>
    )
}