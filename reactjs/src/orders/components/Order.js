import React, {useState} from 'react';
import {useNavigate} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {MdOutlineCancel} from 'react-icons/md';
import {clearAll, deleteCoffee} from "../../app/coffeeDrinksSlice";
import {createOrder} from "../api/OrderAPI";
import CustomAlert from "../../utils/components/CustomAlert";

export default function Order() {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const customCoffees = useSelector(state => state.justCoffee.coffeeDrinks);
    const isAuthenticated = useSelector(state => state.justCoffee.user.isAuthenticated);
    const username = useSelector(state => state.justCoffee.user.username);
    const password = useSelector(state => state.justCoffee.user.password);
    const [renderAlert, setRenderAlert] = useState(false);
    const [render, performRerender] = useState({});
    // let filter = useSelector(state => {
    //     console.log('State: ', state);
    //     return state.pieChart.filter;
    // });

    function calculateTotalPrice() {
        let totalPrice = 0;
        for (let customCoffee of customCoffees) {
            totalPrice += Number(customCoffee.price);
        }
        return totalPrice.toFixed(2);
    }

    function handleDelete(index) {
        dispatch(deleteCoffee(index));
        calculateTotalPrice();
        performRerender({...render});
    }

    function handleOrder() {
        if (!isAuthenticated) {
            setRenderAlert(true);
        } else {
            createOrder(username, password, customCoffees)
                .then(response => {
                    dispatch(clearAll());
                    navigate('/confirmation', {state: {orderId: response.data}});
                });
        }
    }

    const tableRows = customCoffees.map((customCoffee, index) =>
        <tr key={index}>
            <td>{customCoffee.description}</td>
            <td>{customCoffee.price}</td>
            <td><MdOutlineCancel onClick={() => {
                handleDelete(index);
            }} className='icon'/></td>
        </tr>);

    return (
            <>
                <header>Delicious Order</header>
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
                    <h4 className="coffee-drink">Total: {calculateTotalPrice()}</h4>
                </div>
                <div className='coffeePage-floor'>
                    <button onClick={() => handleOrder()} className='custom-button'>Proceed to Checkout</button>
                    <CustomAlert renderAlert={renderAlert} closeAlert={() => setRenderAlert(false)}>
                        To place an order, please <a href="/register">join now</a> or <a href="/login">sign in</a>
                    </CustomAlert>
                </div>
            </>
    )
}