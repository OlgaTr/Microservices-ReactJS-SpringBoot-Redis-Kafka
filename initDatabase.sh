#!/bin/bash

curl -X DELETE http://localhost:8080/coffee
curl -X DELETE http://localhost:8080/toppings

curl -d '{"type":"Espresso", "price":0.99}' -H "Content-Type: application/json" \
     -X POST http://localhost:8080/coffee
curl -d '{"type":"Double espresso", "price":1.10}' -H "Content-Type: application/json" \
     -X POST http://localhost:8080/coffee
curl -d '{"type":"Americano", "price":1.12}' -H "Content-Type: application/json" \
     -X POST http://localhost:8080/coffee
curl -d '{"type":"Cappuccino", "price":1.15}' -H "Content-Type: application/json" \
     -X POST http://localhost:8080/coffee
curl -d '{"type":"Latte", "price":1.15}' -H "Content-Type: application/json" \
     -X POST http://localhost:8080/coffee
curl -d '{"type":"Caramel Syrup", "price":0.30}' -H "Content-Type: application/json" \
     -X POST http://localhost:8080/toppings
curl -d '{"type":"Hazelnut Syrup", "price":0.30}' -H "Content-Type: application/json" \
     -X POST http://localhost:8080/toppings
curl -d '{"type":"Whipped Cream", "price":0.40}' -H "Content-Type: application/json" \
     -X POST http://localhost:8080/toppings
curl -d '{"type":"Ice Cream", "price":0.40}' -H "Content-Type: application/json" \
     -X POST http://localhost:8080/toppings
curl -d '{"type":"Extra Milk", "price":0.30}' -H "Content-Type: application/json" \
     -X POST http://localhost:8080/toppings
curl -d '{"type":"Whiskey", "price":0.60}' -H "Content-Type: application/json" \
     -X POST http://localhost:8080/toppings
curl -d '{"type":"Ice", "price":0.00}' -H "Content-Type: application/json" \
     -X POST http://localhost:8080/toppings
curl -d '{"size":"Short, 200 ml", "price":0.00}' -H "Content-Type: application/json" \
     -X POST http://localhost:8080/coffeeCups
curl -d '{"size":"Tall, 350 ml", "price":0.2}' -H "Content-Type: application/json" \
     -X POST http://localhost:8080/coffeeCups
curl -d '{"size":"Grande, 500 ml", "price":0.3}' -H "Content-Type: application/json" \
     -X POST http://localhost:8080/coffeeCups
curl -d '{"size":"Venti, 600 ml", "price":0.4}' -H "Content-Type: application/json" \
     -X POST http://localhost:8080/coffeeCups