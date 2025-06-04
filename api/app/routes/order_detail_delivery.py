from fastapi import APIRouter, HTTPException
from typing import List
from app.models.order_detail_delivery import OrderDetailDelivery
from app.seed_data import order_detail_deliveries as seed_order_detail_deliveries

router = APIRouter()
order_detail_deliveries = list(seed_order_detail_deliveries)

@router.get("/", response_model=List[OrderDetailDelivery])
async def get_all_order_detail_deliveries():
    return order_detail_deliveries

@router.post("/", response_model=OrderDetailDelivery, status_code=201)
async def create_order_detail_delivery(order_detail_delivery: OrderDetailDelivery):
    order_detail_deliveries.append(order_detail_delivery)
    return order_detail_delivery

@router.get("/{id}", response_model=OrderDetailDelivery)
async def get_order_detail_delivery(id: int):
    odd = next((odd for odd in order_detail_deliveries if odd.orderDetailDeliveryId == id), None)
    if odd:
        return odd
    raise HTTPException(status_code=404, detail="Order detail delivery not found")

@router.put("/{id}", response_model=OrderDetailDelivery)
async def update_order_detail_delivery(id: int, order_detail_delivery: OrderDetailDelivery):
    index = next((i for i, odd in enumerate(order_detail_deliveries) if odd.orderDetailDeliveryId == id), -1)
    if index != -1:
        order_detail_deliveries[index] = order_detail_delivery
        return order_detail_delivery
    raise HTTPException(status_code=404, detail="Order detail delivery not found")

@router.delete("/{id}", status_code=204)
async def delete_order_detail_delivery(id: int):
    index = next((i for i, odd in enumerate(order_detail_deliveries) if odd.orderDetailDeliveryId == id), -1)
    if index != -1:
        order_detail_deliveries.pop(index)
        return
    raise HTTPException(status_code=404, detail="Order detail delivery not found")