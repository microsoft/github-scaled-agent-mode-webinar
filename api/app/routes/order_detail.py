from fastapi import APIRouter, HTTPException
from typing import List
from app.models.order_detail import OrderDetail
from app.seed_data import order_details as seed_order_details

router = APIRouter()
order_details = list(seed_order_details)

@router.get("/", response_model=List[OrderDetail])
async def get_all_order_details():
    return order_details

@router.post("/", response_model=OrderDetail, status_code=201)
async def create_order_detail(order_detail: OrderDetail):
    order_details.append(order_detail)
    return order_detail

@router.get("/{id}", response_model=OrderDetail)
async def get_order_detail(id: int):
    order_detail = next((od for od in order_details if od.orderDetailId == id), None)
    if order_detail:
        return order_detail
    raise HTTPException(status_code=404, detail="Order detail not found")

@router.put("/{id}", response_model=OrderDetail)
async def update_order_detail(id: int, order_detail: OrderDetail):
    index = next((i for i, od in enumerate(order_details) if od.orderDetailId == id), -1)
    if index != -1:
        order_details[index] = order_detail
        return order_detail
    raise HTTPException(status_code=404, detail="Order detail not found")

@router.delete("/{id}", status_code=204)
async def delete_order_detail(id: int):
    index = next((i for i, od in enumerate(order_details) if od.orderDetailId == id), -1)
    if index != -1:
        order_details.pop(index)
        return
    raise HTTPException(status_code=404, detail="Order detail not found")