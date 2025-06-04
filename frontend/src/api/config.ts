const getBaseUrl = () => {
    if (import.meta.env.VITE_API_URL) {
        return import.meta.env.VITE_API_URL;
    }
    
    // Check if we're in a Codespace
    const codespaceName = process.env.CODESPACE_NAME;
    if (codespaceName) {
        // Format: https://CODESPACE_NAME-3000.app.github.dev
        return `https://${codespaceName}-3000.app.github.dev`;
    }
    
    // Local development fallback
    return 'http://localhost:3000';
};

export const API_BASE_URL = getBaseUrl();

export const api = {
    baseURL: API_BASE_URL,
    endpoints: {
        products: '/api/products',
        suppliers: '/api/suppliers',
        orders: '/api/orders',
        branches: '/api/branches',
        headquarters: '/api/headquarters',
        deliveries: '/api/deliveries',
        orderDetails: '/api/order-details',
        orderDetailDeliveries: '/api/order-detail-deliveries'
    }
};