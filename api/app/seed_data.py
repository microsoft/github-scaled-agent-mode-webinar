from datetime import datetime, timedelta
from app.models.supplier import Supplier
from app.models.product import Product
from app.models.headquarters import Headquarters
from app.models.branch import Branch
from app.models.order import Order
from app.models.order_detail import OrderDetail
from app.models.delivery import Delivery
from app.models.order_detail_delivery import OrderDetailDelivery

# Suppliers
suppliers = [
    Supplier(
        supplierId=1,
        name="CircuitCore Labs",
        description="Leading technology supplier",
        contactPerson="John Smith",
        email="john@circuitcorelabs.co",
        phone="555-0101"
    ),
    Supplier(
        supplierId=2,
        name="ConnectSphere",
        description="Advanced tech products supplier",
        contactPerson="Jane Doe",
        email="jane@connectsphere.com",
        phone="555-0102"
    )
]

# Products
products = [
    Product(
        productId=1,
        supplierId=1,
        name="PowerTool Pro X1",
        description="High-performance powertool",
        price=1299.99,
        sku="PWR-001",
        unit="piece",
        imgName="powertool.png"
    ),
    Product(
        productId=2,
        supplierId=1,
        name="Webcam Pro",
        description="Ergonomic webcam",
        price=49.99,
        sku="WEB-001",
        unit="piece",
        imgName="webcam.png"
    ),
    Product(
        productId=3,
        supplierId=2,
        name="Rugged Hard Drive",
        description="Advanced storage solution",
        price=99.99,
        sku="HDD-001",
        unit="piece",
        imgName="harddrive.png"
    ),
    Product(
        productId=4,
        supplierId=2,
        name="Mic Pro",
        description="Advanced audio mic",
        price=29.99,
        sku="MIC-001",
        unit="piece",
        imgName="mic.png"
    )
]

# Headquarters
headquarters = [
    Headquarters(
        headquartersId=1,
        name="Main Office",
        description="Corporate headquarters",
        address="123 Main St, Business District",
        contactPerson="Michael Johnson",
        email="mjohnson@octo.com",
        phone="555-0001"
    )
]

# Branches
branches = [
    Branch(
        branchId=1,
        headquartersId=1,
        name="Downtown Branch",
        description="Main downtown location",
        address="456 Market St",
        contactPerson="Sarah Wilson",
        email="swilson@octo.com",
        phone="555-0201"
    ),
    Branch(
        branchId=2,
        headquartersId=1,
        name="Westside Branch",
        description="Western district branch",
        address="789 West Ave",
        contactPerson="Robert Brown",
        email="rbrown@octo.com",
        phone="555-0202"
    )
]

# Orders
orders = [
    Order(
        orderId=1,
        branchId=1,
        orderDate=datetime.now().isoformat(),
        name="Q1 Tech Supply",
        description="Quarterly technology refresh",
        status="pending"
    ),
    Order(
        orderId=2,
        branchId=2,
        orderDate=datetime.now().isoformat(),
        name="Office Supplies Restock",
        description="Monthly office supplies restock",
        status="processing"
    )
]

# Order Details
order_details = [
    OrderDetail(
        orderDetailId=1,
        orderId=1,
        productId=2,
        quantity=5,
        unitPrice=49.99,
        notes="Webcams for new hires"
    ),
    OrderDetail(
        orderDetailId=2,
        orderId=1,
        productId=3,
        quantity=5,
        unitPrice=99.99,
        notes="Harddrives for new field agents"
    ),
    OrderDetail(
        orderDetailId=3,
        orderId=2,
        productId=4,
        quantity=20,
        unitPrice=29.99,
        notes="Mics for conference rooms"
    )
]

# Deliveries
deliveries = [
    Delivery(
        deliveryId=1,
        supplierId=1,
        deliveryDate=(datetime.now() + timedelta(days=7)).isoformat(),
        name="Tech Equipment Delivery",
        description="Delivery of tech equipment",
        status="pending"
    ),
    Delivery(
        deliveryId=2,
        supplierId=2,
        deliveryDate=(datetime.now() + timedelta(days=2)).isoformat(),
        name="Office Supplies Delivery",
        description="Regular office tech supplies delivery",
        status="in-transit"
    )
]

# Order Detail Deliveries
order_detail_deliveries = [
    OrderDetailDelivery(
        orderDetailDeliveryId=1,
        orderDetailId=1,
        deliveryId=1,
        quantity=5,
        notes="Delivery batch"
    ),
    OrderDetailDelivery(
        orderDetailDeliveryId=2,
        orderDetailId=2,
        deliveryId=1,
        quantity=5,
        notes="Delivery batch"
    ),
    OrderDetailDelivery(
        orderDetailDeliveryId=3,
        orderDetailId=3,
        deliveryId=2,
        quantity=20,
        notes="Delivery"
    )
]