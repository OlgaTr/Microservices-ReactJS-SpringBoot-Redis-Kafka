import React, {useState} from "react";
import {useLocation, useNavigate} from "react-router-dom";
import CustomAlert from "../../utils/components/CustomAlert";
import {payForOrder} from "../api/OrderAPI";
import {useSelector} from "react-redux";

export default function Payment() {
    const location = useLocation();
    const navigate = useNavigate();
    const username = useSelector(state => state.justCoffee.user.username);
    const password = useSelector(state => state.justCoffee.user.password);
    const orderId = location.state.orderId;
    const total = location.state.total;
    const [renderAlert, setRenderAlert] = useState();

    function handlePay() {
        payForOrder(username, password, orderId)
            .then(response => {
                if (response.data) {
                    navigate('/confirmation', {state: {orderId: orderId}});
                } else {
                    setRenderAlert(true);
                }
            })
            .catch(error => console.log(error));
    }

    return (
        <>
            <div className='payment-container'>
                <p>Total: {total}</p>
                <button onClick={() => handlePay()}>Pay</button>
            </div>
            <CustomAlert renderAlert={renderAlert} closeAlert={() => setRenderAlert(false)}>
                Payment cannot be processed
            </CustomAlert>
        </>
    )
}