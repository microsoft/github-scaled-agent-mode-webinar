from pydantic import BaseModel, EmailStr

class Supplier(BaseModel):
    supplierId: int
    name: str
    description: str
    contactPerson: str
    email: str
    phone: str