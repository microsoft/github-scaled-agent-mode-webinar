from pydantic import BaseModel

class Product(BaseModel):
    productId: int
    supplierId: int
    name: str
    description: str
    price: float
    sku: str
    unit: str
    imgName: str