const About = () => {
  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-100 dark:bg-gray-900 p-4">
      <div className="max-w-4xl w-full bg-white dark:bg-gray-800 rounded-lg shadow-lg p-8">
        <h1 className="text-4xl font-bold mb-8 text-gray-900 dark:text-white">About OctoCAT Supplies</h1>
        <div className="space-y-6 text-gray-600 dark:text-gray-300">
          <p>
            OctoCAT Supplies is a comprehensive supply chain management system designed to streamline 
            your business operations. Our platform connects suppliers, branches, and headquarters 
            to ensure smooth delivery and inventory management.
          </p>
          <h2 className="text-2xl font-bold text-gray-800 dark:text-white">Our Mission</h2>
          <p>
            To provide businesses with an efficient, transparent, and reliable supply chain 
            management solution that enhances productivity and reduces operational costs.
          </p>
          <h2 className="text-2xl font-bold text-gray-800 dark:text-white">Key Features</h2>
          <ul className="list-disc list-inside space-y-2">
            <li>Centralized supplier management</li>
            <li>Real-time order tracking</li>
            <li>Multi-branch coordination</li>
            <li>Automated delivery scheduling</li>
            <li>Comprehensive reporting tools</li>
          </ul>
        </div>
      </div>
    </div>
  );
};

export default About;