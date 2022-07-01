import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {listCoffee} from "../api/CoffeeAPI";

import {BiCoffeeTogo} from 'react-icons/bi';

function CoffeeList() {
    const [coffeeList, setCoffeeList] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        listCoffee().then(response => setCoffeeList(response));
    }, [])

    function handleOrder(coffee) {
        navigate('/coffeePage', {state: {id: coffee.id, coffee}});
    }

    const tableRows = coffeeList.map(coffee =>
        <tr key={coffee.id}>
            <td>{coffee.type}</td>
            <td>{coffee.price}</td>
            <td><BiCoffeeTogo onClick={() => handleOrder(coffee)} className='icon'/> </td>
        </tr>
    );

    return (
        <>
            <header> Coffee Menu </header>
            <div className="content-container">
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
            <div className="floor-container">
                <p> A bad day with coffee is better than a good day without it. </p>
            </div>
        </>
    )
}

export default CoffeeList;