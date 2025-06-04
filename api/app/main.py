from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from app.routes import (
    branch,
    delivery,
    headquarters,
    order,
    order_detail,
    order_detail_delivery,
    product,
    supplier
)

app = FastAPI(
    title="Express API with Swagger",
    description="REST API documentation using Swagger/OpenAPI",
    version="1.0.0",
    docs_url="/api-docs",  # Change Swagger UI endpoint to /api-docs
    redoc_url=None  # Disable ReDoc
)

# CORS configuration
app.add_middleware(
    CORSMiddleware,
    allow_origins=[
        "http://localhost:5137",
        "http://localhost:3001",
        "https://*.app.github.dev"
    ],
    allow_credentials=True,
    allow_methods=["GET", "POST", "PUT", "DELETE", "OPTIONS"],
    allow_headers=["Content-Type", "Authorization"],
)

# Mount all routers
app.include_router(branch.router, prefix="/api/branches", tags=["Branches"])
app.include_router(delivery.router, prefix="/api/deliveries", tags=["Deliveries"])
app.include_router(headquarters.router, prefix="/api/headquarters", tags=["Headquarters"])
app.include_router(order.router, prefix="/api/orders", tags=["Orders"])
app.include_router(order_detail.router, prefix="/api/order-details", tags=["Order Details"])
app.include_router(order_detail_delivery.router, prefix="/api/order-detail-deliveries", tags=["Order Detail Deliveries"])
app.include_router(product.router, prefix="/api/products", tags=["Products"])
app.include_router(supplier.router, prefix="/api/suppliers", tags=["Suppliers"])

@app.get("/")
async def root():
    return {"message": "Hello, world!"}