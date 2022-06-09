import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {listCoffee} from "../api/CoffeeAPI";
import {useDispatch} from "react-redux";

function CoffeeList() {
    const [coffeeList, setCoffeeList] = useState([]);
    const navigate = useNavigate();
    const dispatch = useDispatch();


    useEffect(() => {
        // dispatch(clearAll());
        // dispatch(logOut());
        listCoffee().then(response => setCoffeeList(response));
    }, [])

    function handleOrder(coffee) {
        navigate('/coffeePage', {state: {id: coffee.id, coffee}});
    }

    const tableRows = coffeeList.map(coffee =>
        <tr key={coffee.id}>
            <td>{coffee.type}</td>
            <td>{coffee.price}</td>
            <td><button onClick={() => handleOrder(coffee)} className='button'>Order</button></td>
        </tr>
    );

    return (
        <div className="general">
            <div className="header-container">
                <h3 className="header"> Coffee Menu </h3>
            </div>
            <div className="menu-container">
                <div className="menu">
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
            </div>
            <div className="footer-container">
                <p className="footer"> A bad day with coffee is better than a good day without it. </p>
            </div>
        </div>
    )
}

export default CoffeeList;