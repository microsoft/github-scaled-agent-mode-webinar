import express from 'express';
import swaggerJsdoc from 'swagger-jsdoc';
import swaggerUi from 'swagger-ui-express';
import cors from 'cors';
import deliveryRoutes from './routes/delivery';
import orderDetailDeliveryRoutes from './routes/orderDetailDelivery';
import productRoutes from './routes/product';
import orderDetailRoutes from './routes/orderDetail';
import orderRoutes from './routes/order';
import branchRoutes from './routes/branch';
import headquartersRoutes from './routes/headquarters';
import supplierRoutes from './routes/supplier';

const app = express();
const port = process.env.PORT || 3000;

// Parse CORS origins from environment variable if available
const corsOrigins = process.env.API_CORS_ORIGINS 
  ? process.env.API_CORS_ORIGINS.split(',')
  : [
      'http://localhost:5137', 
      'http://localhost:3001',
      // Allow all Codespace domains
      /^https:\/\/.*\.app\.github\.dev$/
    ];

console.log('Configured CORS origins:', corsOrigins);

// Enable CORS for the frontend
app.use(cors({
  origin: corsOrigins,
  methods: ['GET', 'POST', 'PUT', 'DELETE', 'OPTIONS'],
  allowedHeaders: ['Content-Type', 'Authorization'],
  credentials: true // Allow credentials
}));

const swaggerOptions = {
  definition: {
    openapi: '3.0.0',
    info: {
      title: 'Express API with Swagger',
      version: '1.0.0',
      description: 'REST API documentation using Swagger/OpenAPI',
    },
    servers: [
      {
        url: `http://localhost:${port}`,
        description: 'Development server (HTTP)',
      },
      {
        url: `https://localhost:${port}`,
        description: 'Development server (HTTPS)',
      }
    ],
  },
  apis: ['./src/models/*.ts', './src/routes/*.ts'],
};

const swaggerDocs = swaggerJsdoc(swaggerOptions);
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerDocs));

app.get('/api-docs.json', (req, res) => {
  res.setHeader('Content-Type', 'application/json');
  res.send(swaggerDocs);
});

app.use(express.json());

app.use('/api/deliveries', deliveryRoutes);
app.use('/api/order-detail-deliveries', orderDetailDeliveryRoutes);
app.use('/api/products', productRoutes);
app.use('/api/order-details', orderDetailRoutes);
app.use('/api/orders', orderRoutes);
app.use('/api/branches', branchRoutes);
app.use('/api/headquarters', headquartersRoutes);
app.use('/api/suppliers', supplierRoutes);

app.get('/', (req, res) => {
  res.send('Hello, world!');
});

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
  console.log(`API documentation is available at http://localhost:${port}/api-docs`);
});
