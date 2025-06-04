from pydantic import BaseModel

class Order(BaseModel):
    orderId: int
    branchId: int
    orderDate: str
    name: str
    description: str
    status: str