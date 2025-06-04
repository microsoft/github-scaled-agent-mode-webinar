from fastapi import APIRouter, HTTPException
from typing import List
from app.models.headquarters import Headquarters
from app.seed_data import headquarters as seed_headquarters

router = APIRouter()
headquarters = list(seed_headquarters)

@router.get("/", response_model=List[Headquarters])
async def get_all_headquarters():
    return headquarters

@router.post("/", response_model=Headquarters, status_code=201)
async def create_headquarters(hq: Headquarters):
    headquarters.append(hq)
    return hq

@router.get("/{id}", response_model=Headquarters)
async def get_headquarters(id: int):
    hq = next((h for h in headquarters if h.headquartersId == id), None)
    if hq:
        return hq
    raise HTTPException(status_code=404, detail="Headquarters not found")

@router.put("/{id}", response_model=Headquarters)
async def update_headquarters(id: int, hq: Headquarters):
    index = next((i for i, h in enumerate(headquarters) if h.headquartersId == id), -1)
    if index != -1:
        headquarters[index] = hq
        return hq
    raise HTTPException(status_code=404, detail="Headquarters not found")

@router.delete("/{id}", status_code=204)
async def delete_headquarters(id: int):
    index = next((i for i, h in enumerate(headquarters) if h.headquartersId == id), -1)
    if index != -1:
        headquarters.pop(index)
        return
    raise HTTPException(status_code=404, detail="Headquarters not found")