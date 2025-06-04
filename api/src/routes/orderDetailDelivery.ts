/**
 * @swagger
 * tags:
 *   name: Order Detail Deliveries
 *   description: API endpoints for managing order detail deliveries
 */

/**
 * @swagger
 * /api/order-detail-deliveries:
 *   get:
 *     summary: Returns all order detail deliveries
 *     tags: [Order Detail Deliveries]
 *     responses:
 *       200:
 *         description: List of all order detail deliveries
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 $ref: '#/components/schemas/OrderDetailDelivery'
 *   post:
 *     summary: Create a new order detail delivery
 *     tags: [Order Detail Deliveries]
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/OrderDetailDelivery'
 *     responses:
 *       201:
 *         description: Order detail delivery created successfully
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/OrderDetailDelivery'
 * 
 * /api/order-detail-deliveries/{id}:
 *   get:
 *     summary: Get an order detail delivery by ID
 *     tags: [Order Detail Deliveries]
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *         description: Order detail delivery ID
 *     responses:
 *       200:
 *         description: Order detail delivery found
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/OrderDetailDelivery'
 *       404:
 *         description: Order detail delivery not found
 *   put:
 *     summary: Update an order detail delivery
 *     tags: [Order Detail Deliveries]
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *         description: Order detail delivery ID
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/OrderDetailDelivery'
 *     responses:
 *       200:
 *         description: Order detail delivery updated successfully
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/OrderDetailDelivery'
 *       404:
 *         description: Order detail delivery not found
 *   delete:
 *     summary: Delete an order detail delivery
 *     tags: [Order Detail Deliveries]
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *         description: Order detail delivery ID
 *     responses:
 *       204:
 *         description: Order detail delivery deleted successfully
 *       404:
 *         description: Order detail delivery not found
 */

import express from 'express';
import { OrderDetailDelivery } from '../models/orderDetailDelivery';
import { orderDetailDeliveries as seedOrderDetailDeliveries } from '../seedData';

const router = express.Router();

let orderDetailDeliveries: OrderDetailDelivery[] = [...seedOrderDetailDeliveries];

// Create a new order detail delivery
router.post('/', (req, res) => {
  const newOrderDetailDelivery: OrderDetailDelivery = req.body;
  orderDetailDeliveries.push(newOrderDetailDelivery);
  res.status(201).json(newOrderDetailDelivery);
});

// Get all order detail deliveries
router.get('/', (req, res) => {
  res.json(orderDetailDeliveries);
});

// Get an order detail delivery by ID
router.get('/:id', (req, res) => {
  const orderDetailDelivery = orderDetailDeliveries.find(odd => odd.deliveryId === parseInt(req.params.id));
  if (orderDetailDelivery) {
    res.json(orderDetailDelivery);
  } else {
    res.status(404).send('Order detail delivery not found');
  }
});

// Update an order detail delivery by ID
router.put('/:id', (req, res) => {
  const index = orderDetailDeliveries.findIndex(odd => odd.deliveryId === parseInt(req.params.id));
  if (index !== -1) {
    orderDetailDeliveries[index] = req.body;
    res.json(orderDetailDeliveries[index]);
  } else {
    res.status(404).send('Order detail delivery not found');
  }
});

// Delete an order detail delivery by ID
router.delete('/:id', (req, res) => {
  const index = orderDetailDeliveries.findIndex(odd => odd.deliveryId === parseInt(req.params.id));
  if (index !== -1) {
    orderDetailDeliveries.splice(index, 1);
    res.status(204).send();
  } else {
    res.status(404).send('Order detail delivery not found');
  }
});

export default router;
