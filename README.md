# Mock Authentication Service

This is a simple Spring Boot application that mocks various OAuth authentication methods for testing purposes. 
It provides endpoints to authenticate using OAuth Resource Owner, OAuth Client Credentials, and OAuth Basic authentication methods, 
and returns mock JSON data.

## Endpoints

### 1. Authenticate Resource Owner
- **Endpoint**: `POST /auth/resource-owner`
- **Description**: Authenticates a user using resource owner credentials.
- **Request Body**:
  ```json
  {
    "username": "string",
    "password": "string",
    "clientId": "string",
    "clientSecret": "string"
  }
  ```
- **Headers** (optional):
  ```plaintext
  Authorization: Basic base64(clientId:clientSecret)
  ```
- **Response**:
  ```json
  {
    "token": "mock_resource_owner_token"
  }
  ```

### 2. Authenticate Client Credentials
- **Endpoint**: `POST /auth/client-credentials`
- **Description**: Authenticates a client using client credentials.
- **Request Body**:
  ```json
  {
    "clientId": "string",
    "clientSecret": "string"
  }
  ```
- **Headers** (optional):
  ```plaintext
  Authorization: Basic base64(clientId:clientSecret)
  ```
- **Response**:
  ```json
  {
    "token": "mock_client_credentials_token"
  }
  ```

### 3. Authenticate Basic
- **Endpoint**: `POST /auth/basic`
- **Description**: Authenticates a user using basic authentication.
- **Request Body**:
  ```json
  {
    "username": "string",
    "password": "string"
  }
  ```
- **Response**:
  ```json
  {
    "token": "Basic base64(user:password)"
  }
  ```

### Endpoints to Verify Tokens
- **Endpoint For Resource Owner**: `GET /auth/resource-owner/data`
- **Endpoint For Client Credentials**: `GET /auth/client-credentials/data`
- **Endpoint For Basi Authentication**: `GET /auth/basic/data`
- **Header**:
  ```
  Authentication: {TOKEN}
  ```
- **Response**:
  ```json
  { 
      "id": 999,
      "user": "mock user",
      "description": "hello from mock api",
      "completed": true
  }
  ```
  
### Mock Values

**Tokens**
  - RESOURCE OWNER TOKEN = MjJxCGDtCovx3GjyBMFVqAQvhPh6pZTaD8c8qJ5IjoxKFQh5cjJof4ZWaO8aDf00
  - CLIENT CREDENTIALS TOKEN = D428S6rvyukC36I728cRdhp6FQWZRGXl9hCAUkJxVFPbgwjAwHYPIAfW6zEPyXit
  - BASIC AUTH TOKEN = Basic dXNlcm5hbWU6cGFzc3dvcmQ= (username:password)

**Credentials**
  - USERNAME = username
  - PASSWORD = password
  - CLIENT ID = id
  - CLIENT SECRET = secret