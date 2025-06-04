from pydantic import BaseModel

class OrderDetailDelivery(BaseModel):
    orderDetailDeliveryId: int
    orderDetailId: int
    deliveryId: int
    quantity: int
    notes: str