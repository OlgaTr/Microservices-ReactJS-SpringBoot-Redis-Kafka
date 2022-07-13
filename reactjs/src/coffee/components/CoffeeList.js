import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {listCoffee} from "../api/CoffeeAPI";

import {BiCoffeeTogo} from 'react-icons/bi';

function CoffeeList() {
    const [coffeeList, setCoffeeList] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        try {
            fetchData();
        } catch (error) {
            alert(error);
        }
    }, [])

    async function fetchData() {
        let result = await listCoffee();
        setCoffeeList(result.data);
    }

    const tableRows = coffeeList.map(coffee =>
        <tr key={coffee.id}>
            <td>{coffee.type}</td>
            <td>{coffee.price}</td>
            <td><BiCoffeeTogo onClick={() => handleOrder(coffee)} className='icon'/> </td>
        </tr>
    );

    function handleOrder(coffee) {
        navigate('/coffeePage', {state: {id: coffee.id, coffee}});
    }

    return (
        <>
            <main>
                <div className="menu">
                    <table>
                        <thead>
                        <tr>
                            <th>Coffee</th>
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
            </main>
            <p className='coffee-list-citation'> A bad day with coffee is better than a good day without it. </p>
        </>
    )
}

export default CoffeeList;