/**
 * @swagger
 * tags:
 *   name: Headquarters
 *   description: API endpoints for managing headquarters locations
 */

/**
 * @swagger
 * /api/headquarters:
 *   get:
 *     summary: Returns all headquarters
 *     tags: [Headquarters]
 *     responses:
 *       200:
 *         description: List of all headquarters
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 $ref: '#/components/schemas/Headquarters'
 *   post:
 *     summary: Create a new headquarters
 *     tags: [Headquarters]
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/Headquarters'
 *     responses:
 *       201:
 *         description: Headquarters created successfully
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Headquarters'
 * 
 * /api/headquarters/{id}:
 *   get:
 *     summary: Get a headquarters by ID
 *     tags: [Headquarters]
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *         description: Headquarters ID
 *     responses:
 *       200:
 *         description: Headquarters found
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Headquarters'
 *       404:
 *         description: Headquarters not found
 *   put:
 *     summary: Update a headquarters
 *     tags: [Headquarters]
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *         description: Headquarters ID
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/Headquarters'
 *     responses:
 *       200:
 *         description: Headquarters updated successfully
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Headquarters'
 *       404:
 *         description: Headquarters not found
 *   delete:
 *     summary: Delete a headquarters
 *     tags: [Headquarters]
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *         description: Headquarters ID
 *     responses:
 *       204:
 *         description: Headquarters deleted successfully
 *       404:
 *         description: Headquarters not found
 */

import express from 'express';
import { Headquarters } from '../models/headquarters';
import { headquarters as seedHeadquarters } from '../seedData';

const router = express.Router();

let headquartersList: Headquarters[] = [...seedHeadquarters];

// Create a new headquarters
router.post('/', (req, res) => {
  const newHeadquarters: Headquarters = req.body;
  headquartersList.push(newHeadquarters);
  res.status(201).json(newHeadquarters);
});

// Get all headquarters
router.get('/', (req, res) => {
  res.json(headquartersList);
});

// Get a headquarters by ID
router.get('/:id', (req, res) => {
  const headquarters = headquartersList.find(h => h.headquartersId === parseInt(req.params.id));
  if (headquarters) {
    res.json(headquarters);
  } else {
    res.status(404).send('Headquarters not found');
  }
});

// Update a headquarters by ID
router.put('/:id', (req, res) => {
  const index = headquartersList.findIndex(h => h.headquartersId === parseInt(req.params.id));
  if (index !== -1) {
    headquartersList[index] = req.body;
    res.json(headquartersList[index]);
  } else {
    res.status(404).send('Headquarters not found');
  }
});

// Delete a headquarters by ID
router.delete('/:id', (req, res) => {
  const index = headquartersList.findIndex(h => h.headquartersId === parseInt(req.params.id));
  if (index !== -1) {
    headquartersList.splice(index, 1);
    res.status(204).send();
  } else {
    res.status(404).send('Headquarters not found');
  }
});

export default router;
