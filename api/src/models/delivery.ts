/**
 * @swagger
 * components:
 *   schemas:
 *     Delivery:
 *       type: object
 *       required:
 *         - deliveryId
 *         - orderId
 *         - status
 *       properties:
 *         deliveryId:
 *           type: integer
 *           description: The unique identifier for the delivery
 *         orderId:
 *           type: integer
 *           description: The ID of the order being delivered
 *         status:
 *           type: string
 *           description: Current status of the delivery
 *           enum: [pending, in-transit, delivered, failed]
 *         scheduledDate:
 *           type: string
 *           format: date-time
 *           description: Scheduled delivery date and time
 *         actualDeliveryDate:
 *           type: string
 *           format: date-time
 *           description: Actual delivery date and time
 *         notes:
 *           type: string
 *           description: Additional notes about the delivery
 */
export interface Delivery {
    deliveryId: number;
    supplierId: number;
    deliveryDate: string;
    name: string;
    description: string;
    status: string;
}
