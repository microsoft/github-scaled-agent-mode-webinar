from pydantic import BaseModel, EmailStr

class Branch(BaseModel):
    branchId: int
    headquartersId: int
    name: str
    description: str
    address: str
    contactPerson: str
    email: str
    phone: str