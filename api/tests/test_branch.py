import pytest
from fastapi import status
from app.seed_data import branches as seed_branches

def test_get_all_branches(client):
    response = client.get("/api/branches")
    assert response.status_code == status.HTTP_200_OK
    assert len(response.json()) == len(seed_branches)

def test_create_branch(client, test_branch):
    response = client.post("/api/branches", json=test_branch)
    assert response.status_code == status.HTTP_201_CREATED
    assert response.json() == test_branch

def test_get_branch(client):
    # Get first branch from seed data
    first_branch = seed_branches[0]
    response = client.get(f"/api/branches/{first_branch.branchId}")
    assert response.status_code == status.HTTP_200_OK
    assert response.json()["branchId"] == first_branch.branchId

def test_get_branch_not_found(client):
    response = client.get("/api/branches/99999")
    assert response.status_code == status.HTTP_404_NOT_FOUND

def test_update_branch(client, test_branch):
    # First create a branch
    create_response = client.post("/api/branches", json=test_branch)
    assert create_response.status_code == status.HTTP_201_CREATED
    
    # Update the branch
    updated_branch = test_branch.copy()
    updated_branch["name"] = "Updated Test Branch"
    response = client.put(f"/api/branches/{test_branch['branchId']}", json=updated_branch)
    assert response.status_code == status.HTTP_200_OK
    assert response.json()["name"] == "Updated Test Branch"

def test_update_branch_not_found(client, test_branch):
    response = client.put("/api/branches/99999", json=test_branch)
    assert response.status_code == status.HTTP_404_NOT_FOUND

def test_delete_branch(client, test_branch):
    # First create a branch
    create_response = client.post("/api/branches", json=test_branch)
    assert create_response.status_code == status.HTTP_201_CREATED
    
    # Delete the branch
    response = client.delete(f"/api/branches/{test_branch['branchId']}")
    assert response.status_code == status.HTTP_204_NO_CONTENT
    
    # Verify branch is deleted
    get_response = client.get(f"/api/branches/{test_branch['branchId']}")
    assert get_response.status_code == status.HTTP_404_NOT_FOUND

def test_delete_branch_not_found(client):
    response = client.delete("/api/branches/99999")
    assert response.status_code == status.HTTP_404_NOT_FOUND