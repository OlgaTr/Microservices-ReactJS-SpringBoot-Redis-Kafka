#!/bin/bash

curl -d '{"type":"Espresso", "price":"0.99"}' -H "Content-Type: application/json" \
     -X POST http://localhost:8081/coffee
curl -d '{"type":"Double espresso", "price":"1.10"}' -H "Content-Type: application/json" \
     -X POST http://localhost:8081/coffee
curl -d '{"type":"Cappuccino", "price":"1.15"}' -H "Content-Type: application/json" \
     -X POST http://localhost:8081/coffee
curl -d '{"type":"Latte", "price":"1.15"}' -H "Content-Type: application/json" \
     -X POST http://localhost:8081/coffee