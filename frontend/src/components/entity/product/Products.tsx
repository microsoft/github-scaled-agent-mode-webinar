import { useState } from 'react';
import axios from 'axios';
import { useQuery } from 'react-query';
import { api } from '../../../api/config';

interface Product {
  productId: number;
  name: string;
  description: string;
  price: number;
  imgName: string;
  sku: string;
  unit: string;
  supplierId: number;
}

const fetchProducts = async (): Promise<Product[]> => {
  const { data } = await axios.get(`${api.baseURL}${api.endpoints.products}`);
  return data;
};

export default function Products() {
  const [quantities, setQuantities] = useState<Record<number, number>>({});
  const { data: products, isLoading, error } = useQuery('products', fetchProducts);

  const handleQuantityChange = (productId: number, change: number) => {
    setQuantities(prev => ({
      ...prev,
      [productId]: Math.max(0, (prev[productId] || 0) + change)
    }));
  };

  const handleAddToCart = (productId: number) => {
    const quantity = quantities[productId] || 0;
    if (quantity > 0) {
      // TODO: Implement cart functionality
      alert(`Added ${quantity} items to cart`);
      setQuantities(prev => ({
        ...prev,
        [productId]: 0
      }));
    }
  };

  if (isLoading) {
    return (
      <div className="min-h-screen bg-dark pt-20 px-4">
        <div className="max-w-7xl mx-auto">
          <div className="flex justify-center items-center h-64">
            <div className="animate-spin rounded-full h-32 w-32 border-t-2 border-b-2 border-primary"></div>
          </div>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="min-h-screen bg-dark pt-20 px-4">
        <div className="max-w-7xl mx-auto">
          <div className="text-red-500 text-center">Failed to fetch products</div>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-dark pt-20 px-4">
      <div className="max-w-7xl mx-auto">
        <h1 className="text-3xl font-bold text-light mb-6">Products</h1>

        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
          {products?.map((product) => (
            <div key={product.productId} className="bg-gray-800 rounded-lg overflow-hidden shadow-lg transform transition-all duration-300 hover:scale-105 hover:shadow-[0_0_25px_rgba(118,184,82,0.3)]">
              <div className="relative">
                <img 
                  src={`/${product.imgName}`} 
                  alt={product.name}
                  className="w-full h-48 object-cover"
                />
                <div className="absolute top-8 left-0 bg-primary text-white px-3 py-1 -rotate-90 transform -translate-x-5 shadow-md">
                  25% OFF
                </div>
              </div>
              
              <div className="p-4">
                <h3 className="text-xl font-semibold text-light mb-2">{product.name}</h3>
                <p className="text-gray-400 mb-4">{product.description}</p>
                <div className="space-y-4">
                  <div className="flex justify-between items-center">
                    <span className="text-primary text-xl font-bold">${product.price}</span>
                  </div>
                  
                  <div className="flex items-center justify-between">
                    <div className="flex items-center space-x-3 bg-gray-700 rounded-lg p-1">
                      <button 
                        onClick={() => handleQuantityChange(product.productId, -1)}
                        className="w-8 h-8 flex items-center justify-center text-light hover:text-primary transition-colors"
                        aria-label={`Decrease quantity of ${product.name}`}
                        id={`decrease-qty-${product.productId}`}
                      >
                        <span aria-hidden="true">-</span>
                      </button>
                      <span 
                        className="text-light min-w-[2rem] text-center"
                        aria-label={`Quantity of ${product.name}`}
                        id={`qty-${product.productId}`}
                      >
                        {quantities[product.productId] || 0}
                      </span>
                      <button 
                        onClick={() => handleQuantityChange(product.productId, 1)}
                        className="w-8 h-8 flex items-center justify-center text-light hover:text-primary transition-colors"
                        aria-label={`Increase quantity of ${product.name}`}
                        id={`increase-qty-${product.productId}`}
                      >
                        <span aria-hidden="true">+</span>
                      </button>
                    </div>
                    <button 
                      onClick={() => handleAddToCart(product.productId)}
                      className={`px-4 py-2 rounded-lg transition-colors ${
                        quantities[product.productId] ? 'bg-primary hover:bg-accent text-white' : 'bg-gray-700 text-gray-400 cursor-not-allowed'
                      }`}
                      disabled={!quantities[product.productId]}
                      aria-label={`Add ${quantities[product.productId] || 0} ${product.name} to cart`}
                      id={`add-to-cart-${product.productId}`}
                    >
                      Add to Cart
                    </button>
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}