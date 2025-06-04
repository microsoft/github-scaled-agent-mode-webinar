from fastapi import APIRouter, HTTPException
from typing import List
from app.models.order import Order
from app.seed_data import orders as seed_orders

router = APIRouter()
orders = list(seed_orders)

@router.get("/", response_model=List[Order])
async def get_all_orders():
    return orders

@router.post("/", response_model=Order, status_code=201)
async def create_order(order: Order):
    orders.append(order)
    return order

@router.get("/{id}", response_model=Order)
async def get_order(id: int):
    order = next((o for o in orders if o.orderId == id), None)
    if order:
        return order
    raise HTTPException(status_code=404, detail="Order not found")

@router.put("/{id}", response_model=Order)
async def update_order(id: int, order: Order):
    index = next((i for i, o in enumerate(orders) if o.orderId == id), -1)
    if index != -1:
        orders[index] = order
        return order
    raise HTTPException(status_code=404, detail="Order not found")

@router.delete("/{id}", status_code=204)
async def delete_order(id: int):
    index = next((i for i, o in enumerate(orders) if o.orderId == id), -1)
    if index != -1:
        orders.pop(index)
        return
    raise HTTPException(status_code=404, detail="Order not found")