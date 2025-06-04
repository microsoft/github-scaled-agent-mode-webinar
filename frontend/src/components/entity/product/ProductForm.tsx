import { useState } from 'react';
import axios from 'axios';
import { api } from '../../../api/config';

interface Supplier {
  supplierId: number;
  name: string;
}

interface Product {
  productId: number;
  supplierId: number;
  name: string;
  description: string;
  price: number;
  sku: string;
  unit: string;
  imgName: string;
}

interface ProductFormProps {
  product?: Product;
  suppliers: Supplier[];
  onClose: () => void;
  onSave: () => void;
}

export default function ProductForm({ product, suppliers, onClose, onSave }: ProductFormProps) {
  const [formData, setFormData] = useState<Partial<Product>>(
    product || {
      name: '',
      description: '',
      price: 0,
      sku: '',
      unit: '',
      supplierId: suppliers[0]?.supplierId || 0,
      imgName: ''
    }
  );

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      if (product) {
        await axios.put(`${api.baseURL}${api.endpoints.products}/${product.productId}`, formData);
      } else {
        await axios.post(`${api.baseURL}${api.endpoints.products}`, formData);
      }
      onSave();
      onClose();
    } catch (error) {
      console.error('Error saving product:', error);
    }
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4">
      <div className="bg-gray-800 rounded-lg p-6 w-full max-w-md">
        <h2 className="text-2xl font-bold text-light mb-4">
          {product ? 'Edit Product' : 'Add New Product'}
        </h2>
        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-light mb-1">Name</label>
            <input
              type="text"
              value={formData.name}
              onChange={(e) => setFormData({ ...formData, name: e.target.value })}
              className="w-full px-3 py-2 bg-gray-700 text-light rounded"
              required
            />
          </div>
          <div>
            <label className="block text-light mb-1">Description</label>
            <textarea
              value={formData.description}
              onChange={(e) => setFormData({ ...formData, description: e.target.value })}
              className="w-full px-3 py-2 bg-gray-700 text-light rounded"
              required
            />
          </div>
          <div>
            <label className="block text-light mb-1">Price</label>
            <input
              type="number"
              value={formData.price}
              onChange={(e) => setFormData({ ...formData, price: parseFloat(e.target.value) })}
              className="w-full px-3 py-2 bg-gray-700 text-light rounded"
              required
              min="0"
              step="0.01"
            />
          </div>
          <div>
            <label className="block text-light mb-1">SKU</label>
            <input
              type="text"
              value={formData.sku}
              onChange={(e) => setFormData({ ...formData, sku: e.target.value })}
              className="w-full px-3 py-2 bg-gray-700 text-light rounded"
              required
            />
          </div>
          <div>
            <label className="block text-light mb-1">Unit</label>
            <input
              type="text"
              value={formData.unit}
              onChange={(e) => setFormData({ ...formData, unit: e.target.value })}
              className="w-full px-3 py-2 bg-gray-700 text-light rounded"
              required
            />
          </div>
          <div>
            <label className="block text-light mb-1">Image Name</label>
            <input
              type="text"
              value={formData.imgName}
              onChange={(e) => setFormData({ ...formData, imgName: e.target.value })}
              className="w-full px-3 py-2 bg-gray-700 text-light rounded"
              required
            />
          </div>
          <div>
            <label className="block text-light mb-1">Supplier</label>
            <select
              value={formData.supplierId}
              onChange={(e) => setFormData({ ...formData, supplierId: parseInt(e.target.value) })}
              className="w-full px-3 py-2 bg-gray-700 text-light rounded"
              required
            >
              {suppliers.map((supplier) => (
                <option key={supplier.supplierId} value={supplier.supplierId}>
                  {supplier.name}
                </option>
              ))}
            </select>
          </div>
          <div className="flex justify-end space-x-2">
            <button
              type="button"
              onClick={onClose}
              className="px-4 py-2 bg-gray-600 text-white rounded hover:bg-gray-500"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="px-4 py-2 bg-primary text-white rounded hover:bg-accent"
            >
              {product ? 'Update' : 'Create'}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}