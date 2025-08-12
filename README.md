# Spring Boot Trading Platform

This is a Spring Boot application that implements a trading platform with trader management functionality.

## Features

### Trading Platform
The trading platform provides the following REST endpoints:

### 1. Register a New Trader
- **POST** `/trading/traders/register`
- Registers a new trader with name, email, and balance
- Returns HTTP 201 on success, 400 if email already exists
- Request body: `{"name": "Trader Name", "email": "trader@example.com", "balance": 100.0}`

### 2. Get All Traders
- **GET** `/trading/traders/all`
- Returns all traders sorted by ID in ascending order
- Returns HTTP 200 with JSON array of traders

### 3. Get Trader by Email
- **GET** `/trading/traders?email={email}`
- Returns trader with specified email
- Returns HTTP 200 on success, 404 if not found

### 4. Update Trader Name
- **PUT** `/trading/traders`
- Updates trader's name by email
- Returns HTTP 200 on success, 404 if not found
- Request body: `{"name": "New Name", "email": "trader@example.com"}`

### 5. Add Money to Trader Account
- **PUT** `/trading/traders/add`
- Adds specified amount to trader's balance
- Returns HTTP 200 on success, 404 if not found
- Request body: `{"email": "trader@example.com", "amount": 50.0}`

### FizzBuzz Controller Advice
The application also includes a FizzBuzz controller with global exception handling:

- **GET** `/controller_advice/{code}` - FizzBuzz endpoint that throws exceptions based on the code parameter
  - `fizz` → throws FizzException (HTTP 500)
  - `buzz` → throws BuzzException (HTTP 400)  
  - `fizzbuzz` → throws FizzBuzzException (HTTP 507)
  - `success` → returns success response (HTTP 200)
  - Any other code → returns default response (HTTP 200)

## Technical Details

- **Database**: H2 in-memory database (for demo purposes)
- **JPA**: Spring Data JPA with Hibernate
- **Security**: Spring Security configured to allow access to `/trading/**` and `/controller_advice/**` endpoints
- **Timezone**: UTC (as required by the assignment)
- **Data Validation**: Automatic validation for duplicate emails and non-existent traders
- **Exception Handling**: Global exception handler with custom error responses

## Running the Application

1. **Prerequisites**: Java 24, Maven
2. **Build**: `mvn clean install`
3. **Run**: `mvn spring-boot:run`
4. **Access**: Application will be available at `http://localhost:8080`

## Testing

Run the tests with:
```bash
mvn test
```

The test suite covers all the trading platform functionality including:
- Trader registration
- Duplicate email handling
- Retrieving traders
- Updating trader information
- Adding money to accounts
- Error handling for non-existent traders

## Database Schema

The `traders` table contains:
- `id`: Auto-generated primary key
- `name`: Trader's name
- `email`: Unique email address
- `balance`: Account balance (BigDecimal with precision 10, scale 2)
- `created_at`: Timestamp when trader was created
- `updated_at`: Timestamp when trader was last updated

## Example Usage

### FizzBuzz Controller Advice
```bash
# Test FizzException (HTTP 500)
curl http://localhost:8080/controller_advice/fizz

# Test BuzzException (HTTP 400)
curl http://localhost:8080/controller_advice/buzz

# Test FizzBuzzException (HTTP 507)
curl http://localhost:8080/controller_advice/fizzbuzz

# Test Success Response (HTTP 200)
curl http://localhost:8080/controller_advice/success

# Test Default Response (HTTP 200)
curl http://localhost:8080/controller_advice/other
```

### Trading Platform
### Register a Trader
```bash
curl -X POST http://localhost:8080/trading/traders/register \
  -H "Content-Type: application/json" \
  -d '{"name":"Elizabeth Small","email":"elizabeth@example.com","balance":62.0}'
```

### Get All Traders
```bash
curl http://localhost:8080/trading/traders/all
```

### Get Trader by Email
```bash
curl "http://localhost:8080/trading/traders?email=elizabeth@example.com"
```

### Update Trader Name
```bash
curl -X PUT http://localhost:8080/trading/traders \
  -H "Content-Type: application/json" \
  -d '{"name":"Susan Wood","email":"elizabeth@example.com"}'
```

### Add Money to Account
```bash
curl -X PUT http://localhost:8080/trading/traders/add \
  -H "Content-Type: application/json" \
  -d '{"email":"elizabeth@example.com","amount":73.0}'
```

## Notes

- The application uses H2 in-memory database for simplicity
- All timestamps are in UTC timezone
- The balance field allows for decimal precision (e.g., 62.50)
- Email addresses must be unique across all traders
- Automatic timestamp management for created_at and updated_at fields
