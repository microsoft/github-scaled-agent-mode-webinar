import { useState, useEffect } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();
  const { login } = useAuth();
  const [searchParams] = useSearchParams();

  useEffect(() => {
    const errorMsg = searchParams.get('error');
    if (errorMsg) {
      setError(errorMsg);
    }
  }, [searchParams]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await login(email, password);
      navigate('/');
    } catch (err) {
      setError('Login failed. Please try again.');
    }
  };

  return (
    <div className="min-h-screen pt-20 bg-dark flex items-center justify-center px-4">
      <div className="max-w-md w-full bg-gray-800 rounded-lg shadow-lg p-8">
        <h2 className="text-3xl font-bold text-light mb-6">Login</h2>
        
        {error && (
          <div 
            className="bg-red-500/10 border border-red-500 text-red-500 rounded-md p-3 mb-4"
            dangerouslySetInnerHTML={{ __html: error }}
          />
        )}

        <form onSubmit={handleSubmit} className="space-y-6">
          <div>
            <label htmlFor="email" className="block text-light mb-2">Email Address</label>
            <input
              id="email"
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="w-full bg-gray-700 text-light rounded px-3 py-2"
              required
              autoFocus
            />
          </div>

          <div>
            <label htmlFor="password" className="block text-light mb-2">Password</label>
            <input
              id="password"
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full bg-gray-700 text-light rounded px-3 py-2"
              required
            />
          </div>

          <button
            type="submit"
            className="w-full bg-primary hover:bg-accent text-white py-2 px-4 rounded transition-colors"
          >
            Login
          </button>
        </form>
      </div>
    </div>
  );
}