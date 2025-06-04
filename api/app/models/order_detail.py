from pydantic import BaseModel

class OrderDetail(BaseModel):
    orderDetailId: int
    orderId: int
    productId: int
    quantity: int
    unitPrice: float
    notes: str