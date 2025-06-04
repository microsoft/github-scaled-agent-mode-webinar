from fastapi import APIRouter, HTTPException
from typing import List
from app.models.supplier import Supplier
from app.seed_data import suppliers as seed_suppliers

router = APIRouter()
suppliers = list(seed_suppliers)

@router.get("/", response_model=List[Supplier])
async def get_all_suppliers():
    return suppliers

@router.post("/", response_model=Supplier, status_code=201)
async def create_supplier(supplier: Supplier):
    suppliers.append(supplier)
    return supplier

@router.get("/{id}", response_model=Supplier)
async def get_supplier(id: int):
    supplier = next((s for s in suppliers if s.supplierId == id), None)
    if supplier:
        return supplier
    raise HTTPException(status_code=404, detail="Supplier not found")

@router.put("/{id}", response_model=Supplier)
async def update_supplier(id: int, supplier: Supplier):
    index = next((i for i, s in enumerate(suppliers) if s.supplierId == id), -1)
    if index != -1:
        suppliers[index] = supplier
        return supplier
    raise HTTPException(status_code=404, detail="Supplier not found")

@router.delete("/{id}", status_code=204)
async def delete_supplier(id: int):
    index = next((i for i, s in enumerate(suppliers) if s.supplierId == id), -1)
    if index != -1:
        suppliers.pop(index)
        return
    raise HTTPException(status_code=404, detail="Supplier not found")