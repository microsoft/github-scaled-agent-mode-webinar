from fastapi import APIRouter, HTTPException
from typing import List
from app.models.branch import Branch
from app.seed_data import branches as seed_branches

router = APIRouter()
# Create a mutable copy of seed data that will be maintained between requests
branches = list(seed_branches)

@router.get("/", response_model=List[Branch])
async def get_all_branches():
    return branches

@router.post("/", response_model=Branch, status_code=201)
async def create_branch(branch: Branch):
    branches.append(branch)
    return branch

@router.get("/{id}", response_model=Branch)
async def get_branch(id: int):
    branch = next((b for b in branches if b.branchId == id), None)
    if branch:
        return branch
    raise HTTPException(status_code=404, detail="Branch not found")

@router.put("/{id}", response_model=Branch)
async def update_branch(id: int, branch: Branch):
    index = next((i for i, b in enumerate(branches) if b.branchId == id), -1)
    if index != -1:
        branches[index] = branch
        return branch
    raise HTTPException(status_code=404, detail="Branch not found")

@router.delete("/{id}", status_code=204)
async def delete_branch(id: int):
    global branches  # Ensure we're modifying the module-level list
    index = next((i for i, b in enumerate(branches) if b.branchId == id), -1)
    if index != -1:
        branches.pop(index)
        return
    raise HTTPException(status_code=404, detail="Branch not found")