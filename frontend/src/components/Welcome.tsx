import Slider from 'react-slick';
import { useRef } from 'react';

export default function Welcome() {
  const sliderRef = useRef<Slider | null>(null);

  const sliderSettings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 6,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 3000,
    responsive: [
      {
        breakpoint: 1024,
        settings: {
          slidesToShow: 4,
        }
      },
      {
        breakpoint: 768,
        settings: {
          slidesToShow: 3,
        }
      },
      {
        breakpoint: 640,
        settings: {
          slidesToShow: 2,
        }
      }
    ]
  };

  return (
    <div className="relative bg-dark text-light">
      {/* Content */}
      <div className="relative px-4 sm:px-6 lg:px-8 pt-16">
        <div className="relative py-20">
          {/* Hero Image */}
          <div className="w-7/8">
            <img
              src="/hero.png"
              alt="Logistics and Supply Chain"
              className="w-full h-auto rounded-lg"
            />
          </div>

          {/* Text Content */}
          <div className="absolute inset-0 flex items-center justify-end max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <div className="max-w-2xl bg-dark/60 backdrop-blur-sm p-8 rounded-xl shadow-[0_0_25px_rgba(0,100,0,0.5)]">
              <div className="bg-primary/10 inline-block px-4 py-2 rounded-full mb-4">
                <span className="text-primary font-medium">15 Years of Experience</span>
              </div>
              <h1 className="text-5xl font-bold mb-6 leading-tight">
                Professional Logistics and Supply Chain
              </h1>
              <p className="text-gray-200 mb-8 text-lg">
                OctoCAT Supply has over 15 years of experience in providing top-notch logistics and supply chain solutions. Our team of experts is dedicated to ensuring your goods are delivered on time and in perfect condition.
              </p>
              <button className="bg-primary hover:bg-accent text-white px-8 py-3 rounded-md font-medium transition-colors">
                Read More
              </button>
            </div>
          </div>
        </div>
        
        {/* Partner Logos */}
        <div className="py-12 border-t border-gray-800">
          <h2 className="text-3xl font-bold text-white text-center mb-12">Customers</h2>
          <div className="max-w-7xl mx-auto px-4">
            {/* SVG Filter Definition */}
            <svg className="hidden">
              <defs>
                <filter id="green-glow">
                  <feFlood result="flood" floodColor="rgb(0,255,0)" floodOpacity=".3"></feFlood>
                  <feComposite in="flood" operator="in" in2="SourceGraphic"></feComposite>
                  <feMerge>
                    <feMergeNode></feMergeNode>
                    <feMergeNode in="SourceGraphic"></feMergeNode>
                  </feMerge>
                </filter>
              </defs>
            </svg>

            <Slider {...sliderSettings} ref={sliderRef} className="opacity-50">
              {/* Logo 1 - Abstract Box */}
              <div className="flex flex-col items-center justify-center text-center px-4">
                <div className="flex items-center justify-center transition-all duration-300 group-hover:drop-shadow-[0_0_12px_rgba(0,255,0,0.4)] group-hover:scale-110 mb-2">
                  <svg viewBox="0 0 100 100" className="w-24 h-24 text-gray-500 group-hover:text-green-400 transition-colors duration-300" style={{ filter: 'url(#green-glow)' }}>
                    <rect x="20" y="20" width="60" height="60" fill="none" stroke="currentColor" strokeWidth="4"/>
                    <circle cx="50" cy="50" r="20" fill="none" stroke="currentColor" strokeWidth="4"/>
                    <path d="M30 70 L70 30" stroke="currentColor" strokeWidth="4"/>
                  </svg>
                </div>
                <span className="text-gray-500 text-sm font-medium group-hover:text-green-400 transition-colors duration-300 w-full text-center">Nexus Corp</span>
              </div>

              {/* Logo 2 - Tech Wave */}
              <div className="flex flex-col items-center justify-center text-center px-4">
                <div className="flex items-center justify-center transition-all duration-300 group-hover:drop-shadow-[0_0_12px_rgba(0,255,0,0.4)] group-hover:scale-110 mb-2">
                  <svg viewBox="0 0 100 100" className="w-24 h-24 text-gray-500 group-hover:text-green-400 transition-colors duration-300" style={{ filter: 'url(#green-glow)' }}>
                    <path d="M10 50 Q 25 30, 40 50 T 70 50 T 100 50" fill="none" stroke="currentColor" strokeWidth="4"/>
                    <path d="M10 60 Q 25 40, 40 60 T 70 60 T 100 60" fill="none" stroke="currentColor" strokeWidth="4"/>
                  </svg>
                </div>
                <span className="text-gray-500 text-sm font-medium group-hover:text-green-400 transition-colors duration-300 w-full text-center">WaveTech Solutions</span>
              </div>

              {/* Logo 3 - Connected Dots */}
              <div className="flex flex-col items-center justify-center text-center px-4">
                <div className="flex items-center justify-center transition-all duration-300 group-hover:drop-shadow-[0_0_12px_rgba(0,255,0,0.4)] group-hover:scale-110 mb-2">
                  <svg viewBox="0 0 100 100" className="w-24 h-24 text-gray-500 group-hover:text-green-400 transition-colors duration-300" style={{ filter: 'url(#green-glow)' }}>
                    <circle cx="25" cy="25" r="8" fill="currentColor"/>
                    <circle cx="75" cy="25" r="8" fill="currentColor"/>
                    <circle cx="50" cy="75" r="8" fill="currentColor"/>
                    <line x1="25" y1="25" x2="75" y2="25" stroke="currentColor" strokeWidth="2"/>
                    <line x1="25" y1="25" x2="50" y2="75" stroke="currentColor" strokeWidth="2"/>
                    <line x1="75" y1="25" x2="50" y2="75" stroke="currentColor" strokeWidth="2"/>
                  </svg>
                </div>
                <span className="text-gray-500 text-sm font-medium group-hover:text-green-400 transition-colors duration-300 w-full text-center">ConnectSphere</span>
              </div>

              {/* Logo 4 - Infinity Tech */}
              <div className="flex flex-col items-center justify-center text-center px-4">
                <div className="flex items-center justify-center transition-all duration-300 group-hover:drop-shadow-[0_0_12px_rgba(0,255,0,0.4)] group-hover:scale-110 mb-2">
                  <svg viewBox="0 0 100 100" className="w-24 h-24 text-gray-500 group-hover:text-green-400 transition-colors duration-300" style={{ filter: 'url(#green-glow)' }}>
                    <path d="M30 50 C 30 30, 70 30, 70 50 C 70 70, 30 70, 30 50" fill="none" stroke="currentColor" strokeWidth="4"/>
                    <circle cx="30" cy="50" r="5" fill="currentColor"/>
                    <circle cx="70" cy="50" r="5" fill="currentColor"/>
                  </svg>
                </div>
                <span className="text-gray-500 text-sm font-medium group-hover:text-green-400 transition-colors duration-300 w-full text-center">Infinite Systems</span>
              </div>

              {/* Logo 5 - Circuit Board */}
              <div className="flex flex-col items-center justify-center text-center px-4">
                <div className="flex items-center justify-center transition-all duration-300 group-hover:drop-shadow-[0_0_12px_rgba(0,255,0,0.4)] group-hover:scale-110 mb-2">
                  <svg viewBox="0 0 100 100" className="w-24 h-24 text-gray-500 group-hover:text-green-400 transition-colors duration-300" style={{ filter: 'url(#green-glow)' }}>
                    <rect x="20" y="20" width="60" height="60" fill="none" stroke="currentColor" strokeWidth="2"/>
                    <line x1="50" y1="20" x2="50" y2="80" stroke="currentColor" strokeWidth="2"/>
                    <line x1="20" y1="50" x2="80" y2="50" stroke="currentColor" strokeWidth="2"/>
                    <circle cx="50" cy="50" r="10" fill="none" stroke="currentColor" strokeWidth="2"/>
                    <circle cx="30" cy="30" r="5" fill="currentColor"/>
                    <circle cx="70" cy="30" r="5" fill="currentColor"/>
                    <circle cx="30" cy="70" r="5" fill="currentColor"/>
                    <circle cx="70" cy="70" r="5" fill="currentColor"/>
                  </svg>
                </div>
                <span className="text-gray-500 text-sm font-medium group-hover:text-green-400 transition-colors duration-300 w-full text-center">CircuitCore Labs</span>
              </div>

              {/* Logo 6 - Data Flow */}
              <div className="flex flex-col items-center justify-center text-center px-4">
                <div className="flex items-center justify-center transition-all duration-300 group-hover:drop-shadow-[0_0_12px_rgba(0,255,0,0.4)] group-hover:scale-110 mb-2">
                  <svg viewBox="0 0 100 100" className="w-24 h-24 text-gray-500 group-hover:text-green-400 transition-colors duration-300" style={{ filter: 'url(#green-glow)' }}>
                    <path d="M20 50 L80 50" stroke="currentColor" strokeWidth="4"/>
                    <path d="M50 20 L50 80" stroke="currentColor" strokeWidth="4"/>
                    <circle cx="50" cy="50" r="15" fill="none" stroke="currentColor" strokeWidth="4"/>
                    <circle cx="20" cy="50" r="8" fill="currentColor"/>
                    <circle cx="80" cy="50" r="8" fill="currentColor"/>
                    <circle cx="50" cy="20" r="8" fill="currentColor"/>
                    <circle cx="50" cy="80" r="8" fill="currentColor"/>
                  </svg>
                </div>
                <span className="text-gray-500 text-sm font-medium group-hover:text-green-400 transition-colors duration-300 w-full text-center">DataFlow Dynamics</span>
              </div>
            </Slider>
          </div>
        </div>
      </div>
    </div>
  )
}