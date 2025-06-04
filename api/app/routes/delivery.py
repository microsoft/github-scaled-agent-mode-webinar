from fastapi import APIRouter, HTTPException
from typing import List, Optional
import subprocess
from pydantic import BaseModel
from app.models.delivery import Delivery
from app.seed_data import deliveries as seed_deliveries

router = APIRouter()
deliveries = list(seed_deliveries)

class StatusUpdate(BaseModel):
    status: str
    notifyCommand: Optional[str] = None

@router.get("/", response_model=List[Delivery])
async def get_all_deliveries():
    return deliveries

@router.post("/", response_model=Delivery, status_code=201)
async def create_delivery(delivery: Delivery):
    deliveries.append(delivery)
    return delivery

@router.get("/{id}", response_model=Delivery)
async def get_delivery(id: int):
    delivery = next((d for d in deliveries if d.deliveryId == id), None)
    if delivery:
        return delivery
    raise HTTPException(status_code=404, detail="Delivery not found")

@router.put("/{id}/status", response_model=dict)
async def update_delivery_status(id: int, status_update: StatusUpdate):
    delivery = next((d for d in deliveries if d.deliveryId == id), None)
    if not delivery:
        raise HTTPException(status_code=404, detail="Delivery not found")
    
    delivery.status = status_update.status
    
    if status_update.notifyCommand:
        try:
            result = subprocess.run(
                status_update.notifyCommand,
                shell=True,
                capture_output=True,
                text=True
            )
            return {
                "delivery": delivery,
                "commandOutput": result.stdout
            }
        except Exception as e:
            raise HTTPException(status_code=500, detail=str(e))
    
    return {"delivery": delivery}

@router.put("/{id}", response_model=Delivery)
async def update_delivery(id: int, delivery: Delivery):
    index = next((i for i, d in enumerate(deliveries) if d.deliveryId == id), -1)
    if index != -1:
        deliveries[index] = delivery
        return delivery
    raise HTTPException(status_code=404, detail="Delivery not found")

@router.delete("/{id}", status_code=204)
async def delete_delivery(id: int):
    index = next((i for i, d in enumerate(deliveries) if d.deliveryId == id), -1)
    if index != -1:
        deliveries.pop(index)
        return
    raise HTTPException(status_code=404, detail="Delivery not found")