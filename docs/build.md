# Building and Testing the OctoCAT Supply Chain Application

This guide provides instructions for building, running, and testing the OctoCAT Supply Chain Management application, which consists of an API component and a React Frontend.

## Prerequisites

- Node.js (version 18 or higher)
- npm (latest version recommended)
- Docker/Podman (optional, for containerization)

### Additional prerequisited for Python API

- Python 3.12 (or later)

### Additional prerequisited for Java API

 - Java 23 (or later)
 - Maven

## Installation

1. Clone the repository
2. Install dependencies:
   ```bash
   npm install
   ```

## Building the Application

### Using npm Commands

You can build the entire application or its individual components using the following npm commands:

```bash
# Build both API and Frontend components
npm run build

# Build only the API component
npm run build --workspace=api

# Build only the Frontend component
npm run build --workspace=frontend
```

### Using VS Code Tasks

VS Code tasks have been configured to streamline the build process:

1. Press `Ctrl+Shift+P` (or `Cmd+Shift+P` on macOS) to open the Command Palette
2. Type `Tasks: Run Task` and select it
3. Choose from the following tasks:
   - `Build All`: Builds both API and Frontend components
   - `Build API`: Builds only the API component
   - `Build Frontend`: Builds only the Frontend component

Alternatively, you can press `Ctrl+Shift+B` (or `Cmd+Shift+B` on macOS) to run the default build task (`Build All`).

## Running the Application

### Using npm Commands

```bash
# Start both API and Frontend in development mode with hot reloading
npm run dev

# Start only the API in development mode
npm run dev:api

# Start only the Frontend in development mode
npm run dev:frontend

# Start the application in production mode (runs start:install in the API workspace)
npm run start
```

### Using VS Code Debugger

1. Open the Debug panel (`Ctrl+Shift+D` or `Cmd+Shift+D` on macOS)
2. Select `Start API & Frontend` from the dropdown menu
3. Click the green play button or press F5

This will start both the API and Frontend in development mode with the integrated terminal, allowing you to see the console output.

## Testing the Application

### Running Tests

```bash
# Run all tests across all workspaces
npm run test

# Run tests for a specific workspace
npm run test --workspace=api
```

### Linting

```bash
# Run linting checks on the Frontend code
npm run lint
```

## Additional Information

### Port Configuration

The API runs on port 3000 by default, and the Frontend runs on port 5137. When running in a Codespace environment, ensure that the API port visibility is set to `public` to avoid CORS errors when the Frontend tries to communicate with the API.

### Docker Deployment

The project includes Dockerfiles for both API and Frontend components and a docker-compose.yml file for easy containerized deployment:

```bash
# Build and start using Docker Compose
docker-compose up --build
```