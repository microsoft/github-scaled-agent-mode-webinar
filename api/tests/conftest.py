import pytest
from fastapi.testclient import TestClient
from app.main import app
from app.routes import branch  # Import the branch module to access its state

@pytest.fixture(autouse=True)
def reset_branch_data():
    # Reset branch data before each test
    branch.branches = branch.branches.copy()
    yield
    # Reset after test
    branch.branches = list(branch.seed_branches)

@pytest.fixture
def client():
    return TestClient(app)

@pytest.fixture
def test_branch():
    return {
        "branchId": 999,
        "headquartersId": 1,
        "name": "Test Branch",
        "description": "Test branch for unit tests",
        "address": "123 Test St",
        "contactPerson": "Test Person",
        "email": "test@example.com",
        "phone": "555-0123"
    }