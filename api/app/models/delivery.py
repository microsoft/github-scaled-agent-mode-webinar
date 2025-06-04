from pydantic import BaseModel

class Delivery(BaseModel):
    deliveryId: int
    supplierId: int
    deliveryDate: str
    name: str
    description: str
    status: str