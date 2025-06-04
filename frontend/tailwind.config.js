/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        'primary': '#76B852',
        'dark': '#0A0A0A',
        'light': '#F5F5F5',
        'accent': '#8BC34A'
      },
      backgroundImage: {
        'gradient-radial': 'radial-gradient(var(--tw-gradient-stops))',
      },
      width: {
        '7/8': '87.5%'
      }
    },
  },
  plugins: [],
}