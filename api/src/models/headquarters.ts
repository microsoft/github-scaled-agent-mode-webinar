/**
 * @swagger
 * components:
 *   schemas:
 *     Headquarters:
 *       type: object
 *       required:
 *         - headquartersId
 *         - name
 *       properties:
 *         headquartersId:
 *           type: integer
 *           description: The unique identifier for the headquarters
 *         name:
 *           type: string
 *           description: The name of the headquarters
 *         address:
 *           type: string
 *           description: Main office address of the headquarters
 *         phone:
 *           type: string
 *           description: Contact phone number for the headquarters
 *         email:
 *           type: string
 *           format: email
 *           description: Contact email for the headquarters
 *         description:
 *           type: string
 *           description: Additional details about the headquarters
 */
export interface Headquarters {
    headquartersId: number;
    name: string;
    description: string;
    address: string;
    contactPerson: string;
    email: string;
    phone: string;
}
