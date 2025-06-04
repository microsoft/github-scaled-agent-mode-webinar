# Build stage
FROM mcr.microsoft.com/dotnet/sdk:9.0 AS build
WORKDIR /src

# Copy csproj and restore dependencies
COPY ["api-dotnet.csproj", "./"]
RUN dotnet restore

# Copy the rest of the source code
COPY . .

# Build the application
RUN dotnet build "api-dotnet.csproj" -c Release -o /app/build

# Run tests and generate coverage
FROM build as test
WORKDIR /src
COPY ["../api-tests/*.csproj", "../api-tests/"]
RUN dotnet restore "../api-tests/api-tests.csproj"
COPY ["../api-tests/", "../api-tests/"]
RUN dotnet test "../api-tests/api-tests.csproj" -c Release --collect:"XPlat Code Coverage" --results-directory /testresults

# Publish the application
FROM build AS publish
RUN dotnet publish "api-dotnet.csproj" -c Release -o /app/publish /p:UseAppHost=false

# Final stage/runtime image
FROM mcr.microsoft.com/dotnet/aspnet:9.0 AS final
WORKDIR /app
EXPOSE 3000

# Copy from publish stage
COPY --from=publish /app/publish .

# Set the entry point
ENTRYPOINT ["dotnet", "api-dotnet.dll"]