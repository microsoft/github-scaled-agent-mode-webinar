from pydantic import BaseModel, EmailStr

class Headquarters(BaseModel):
    headquartersId: int
    name: str
    description: str
    address: str
    contactPerson: str
    email: str
    phone: str