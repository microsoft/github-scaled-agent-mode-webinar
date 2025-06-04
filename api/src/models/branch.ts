/**
 * @swagger
 * components:
 *   schemas:
 *     Branch:
 *       type: object
 *       required:
 *         - branchId
 *         - name
 *         - headquartersId
 *       properties:
 *         branchId:
 *           type: integer
 *           description: The unique identifier for the branch
 *         headquartersId:
 *           type: integer
 *           description: The ID of the headquarters this branch belongs to
 *         name:
 *           type: string
 *           description: The name of the branch
 *         address:
 *           type: string
 *           description: Physical address of the branch
 *         phone:
 *           type: string
 *           description: Contact phone number for the branch
 *         email:
 *           type: string
 *           format: email
 *           description: Contact email for the branch
 */
export interface Branch {
    branchId: number;
    headquartersId: number;
    name: string;
    description: string;
    address: string;
    contactPerson: string;
    email: string;
    phone: string;
}
