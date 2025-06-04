from fastapi import APIRouter, HTTPException
from typing import List
from app.models.product import Product
from app.seed_data import products as seed_products

router = APIRouter()
products = list(seed_products)

@router.get("/", response_model=List[Product])
async def get_all_products():
    return products

@router.post("/", response_model=Product, status_code=201)
async def create_product(product: Product):
    products.append(product)
    return product

@router.get("/{id}", response_model=Product)
async def get_product(id: int):
    product = next((p for p in products if p.productId == id), None)
    if product:
        return product
    raise HTTPException(status_code=404, detail="Product not found")

@router.put("/{id}", response_model=Product)
async def update_product(id: int, product: Product):
    index = next((i for i, p in enumerate(products) if p.productId == id), -1)
    if index != -1:
        products[index] = product
        return product
    raise HTTPException(status_code=404, detail="Product not found")

@router.delete("/{id}", status_code=204)
async def delete_product(id: int):
    index = next((i for i, p in enumerate(products) if p.productId == id), -1)
    if index != -1:
        products.pop(index)
        return
    raise HTTPException(status_code=404, detail="Product not found")