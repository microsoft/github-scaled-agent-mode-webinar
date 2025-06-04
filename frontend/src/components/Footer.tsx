import React from 'react';

const Footer: React.FC = () => {
  return (
    <footer className="bg-gray-900 text-gray-300 py-8">
      <div className="container mx-auto px-4">
        <div className="grid grid-cols-1 md:grid-cols-4 gap-8">
          {/* About Section */}
          <div>
            <h2 className="font-bold text-xl mb-4 text-primary">About</h2>
            <p className="text-sm">
              OctoCAT Supply is a world leader in complex logistics and supply chain solutions. We specialize in providing tailored services to meet the unique needs of our clients, ensuring efficiency and reliability in every delivery.
            </p>
          </div>

          {/* Account Section */}
          <div>
            <h2 className="font-bold text-xl mb-4 text-primary">Account</h2>
            <ul className="space-y-2">
              <li><a href="#" className="hover:text-primary">My Cart</a></li>
              <li><a href="#" className="hover:text-primary">Checkout</a></li>
              <li><a href="#" className="hover:text-primary">Shopping Details</a></li>
              <li><a href="#" className="hover:text-primary">Order</a></li>
              <li><a href="#" className="hover:text-primary">Help Center</a></li>
            </ul>
          </div>

          {/* Helpful Links Section */}
          <div>
            <h2 className="font-bold text-xl mb-4 text-primary">Helpful Links</h2>
            <ul className="space-y-2">
              <li><a href="#" className="hover:text-primary">Services</a></li>
              <li><a href="#" className="hover:text-primary">Supports</a></li>
              <li><a href="#" className="hover:text-primary">Feedback</a></li>
              <li><a href="#" className="hover:text-primary">Terms & Conditions</a></li>
              <li><a href="#" className="hover:text-primary">Privacy Policy</a></li>
            </ul>
          </div>

          {/* Social Media Section */}
          <div>
            <h2 className="font-bold text-xl mb-4 text-primary">Social Media</h2>
            <ul className="space-y-2">
              <li><a href="#" className="hover:text-primary">Twitter</a></li>
              <li><a href="#" className="hover:text-primary">Facebook</a></li>
              <li><a href="#" className="hover:text-primary">Youtube</a></li>
              <li><a href="#" className="hover:text-primary">Linkedin</a></li>
              <li><a href="#" className="hover:text-primary">Instagram</a></li>
            </ul>
          </div>
        </div>

        <div className="mt-8 pt-8 border-t border-gray-700 text-center text-sm">
          <p>Copyright © 2025 OctoCAT Supply. All Rights Reserved</p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;