<img src="src/main/resources/images/rmb-logo.png" alt="Home scherm" width="400">
<img src="src/main/resources/images/rmb-text-logo.png" alt="Auto overzicht" width="400">



**Rent My Bike** is a digital Bike-sharing platform that promotes sustainable mobility and efficient resource use. The platform allows users to easily rent or share Bikes, focusing on user-friendliness, transparency, and smart features such as driving behavior analysis and reward points.

---

## Features


### For Bike Owners
- Register Bikes with details such as brand, model, year, license plate, body type, and photos.
- Create advertisements including price, availability, location, and photos.
- Manage reservations for their Bikes (approve, cancel, view driving data).

### For Renters
- Search and filter Bikes by brand, price, type, and location.
- Make, manage, and cancel reservations.
- Get navigation to the Bike using external map services.
- Drive with automatic registration of driving data.
- Earn bonus points for responsible driving, redeemable for discounts or extras.

---

## Domain Concepts
- **User**: can be a renter, owner, or both.
- **Bike**: central object, linked to a category and owner.
- **Category**: ICE, BEV, or FCEV; determines cost per kilometer.
- **Reservation**: records rental period and status.
- **Driving Data**: collects mileage, acceleration, braking behavior.
- **Points**: rewards for safe driving.

---

## Architecture
The platform consists of:
- **Web API**: manages registration, advertisements, reservations, and driving data.
- **Android app**: user interaction, searching and reserving Bikes, navigation, and driving data collection.

**Key classes**:  
`User`, `Bike`, `Advertisement`, `Rental`, `RentalTrip`, `BikeImage`, `FuelType`, `BodyType`, `RentalStatus`

---

## Actors
- **Primary actors**: Renter, Bike Owner
- **Secondary actors**: Navigation service (Google Maps / Apple Maps)

---

## Use Cases
1. **Register**: Users create an account.
2. **Login**: Users log in to their account.
3. **Search Bikes**: Renters search and filter available Bikes.
4. **Reserve Bikes**: Renters reserve Bikes for a specific time block.
5. **Manage Reservations**: Renters and owners can modify or cancel reservations.
6. **Add / Manage / Remove Bikes**: Owners manage their fleet.
7. **Get Route**: Navigation to the reserved Bike via map services.
8. **Record Driving Data**: Automatic recording during rental period.
9. **Assign Bonus Points**: Rewards for responsible driving.

---

## Project Structure

The project is organized according to Domain-Driven Design principles:

```text
rent-my-Bike/
├── src/                                      
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── rmb/
│   │   │       ├── domain/                  # Domain layer
│   │   │       │   ├── entities/            # Domain entities
│   │   │       │   ├── exceptions/          # Custom exceptions
│   │   │       │   ├── repositories/        # Repository interfaces
│   │   │       │   └── validations/         # Validate business logic that can be validated in domain entity
│   │   │       ├── application/             # Application layer
│   │   │       │   ├── auth/                # Authentication / security
│   │   │       │   ├── exceptions/          # Custom exceptions
│   │   │       │   ├── usecases/            # Application use cases
│   │   │       ├── infrastructure/          # Infrastructure layer
│   │   │       │   ├── auth/                # Authentication implementations
│   │   │       │   ├── mappers/             # DTO / Entity mappers
│   │   │       │   ├── plugins/             # Ktor plugins / middleware
│   │   │       │   ├── repositories/        # Repository implementations
│   │   │       │   └── tables/              # Database table definitions
│   │   │       └── presentation/            # API / HTTP layer
│   │   │           ├── dto/                 # Request/Response DTOs
│   │   │           ├── mappers/             # DTO <-> domain mapping
│   │   │           └── routes/              # Route definitions
│   │   └── resources/                       # Resources: configs, images, etc.
│   │       └── images/                      # Images for README or app
│   └── test/                                # Test sources
│       └── kotlin/
│           └── rmb/
│               └── http/                    # HTTP request tests (GET, POST, DELETE)
├── build.gradle.kts                         # Backend Gradle file
├── settings.gradle.kts                      # Gradle settings
└── README.md                                # Project documentation
```

## Usage

Below is an example of three key use cases in this web API: registering a user, creating a Bike, and reserving a Bike.

---

### Endpoint 1: Register a new user
**Description:** Create a new user account with address.  
**HTTP Method:** POST  
**URL:** /signup

**Request Example:**
POST http://localhost:8080/signup
Content-Type: application/json
```
{
"email": "john@doe.com",
"userType": "CUSTOMER",
"password": "StrongPassword1!",
"firstName": "John",
"lastName": "Doe",
"phone": "06 123456789",
"userPoints": 0,
"address": 
    {
        "street": "123 Main Street",
        "city": "Amsterdam",
        "postalCode": "1011AA",
        "houseNumber": 32,
        "subHouseNumber": "a"
    }
}
```
**Response Example:**
```
{
"id": 1,
"email": "john@doe.com",
"userType": "CUSTOMER",
"firstName": "John",
"lastName": "Doe",
"phone": "06 123456789",
"userPoints": 0,
"address": 
    {
        "street": "123 Main Street",
        "city": "Amsterdam",
        "postalCode": "1011AA",
        "houseNumber": 32,
        "subHouseNumber": "a"
    }
}
```
---

### Endpoint 2: Create a new Bike
**Description:** Register a new Bike in the system.  
**HTTP Method:** POST  
**URL:** /Bike/create

**Request Example:**
POST http://localhost:8080/Bike/create
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN_HERE>
```
{
"fuelType": "ICE",
"userId": 1,
"bodyType": "SEDAN",
"brand": "Toyota",
"modelYear": "2022",
"model": "Supra",
"licensePlate": "KTR-123-A",
"mileage": "34000",
"createdStamp": "2020-08-30T18:43",
"BikeImages": 
    [
        {"image": "base64EncodedImageStringHere"},
        {"image": "anotherBase64EncodedImageStringHere"}
    ]
}
```
**Response Example:**
```
{
"id": 1,
"userId": 1,
"fuelType": "ICE",
"bodyType": "SEDAN",
"brand": "Toyota",
"modelYear": "2022",
"model": "Supra",
"licensePlate": "KTR-123-A",
"mileage": "34000",
"createdStamp": "2020-08-30T18:43",
"BikeImages": 
    [
        {"image": "base64EncodedImageStringHere"},
        {"image": "anotherBase64EncodedImageStringHere"}
    ]
}
```
---

### Endpoint 3: Reserve a Bike
**Description:** Rent an advertisement (reserve a Bike).  
**HTTP Method:** POST  
**URL:** /rent/advertisement

**Request Example:**
POST http://localhost:8080/rent/advertisement
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN_HERE>
```
{
"userId": 1,
"advertisementId": 6,
"rentalStatus": "ACTIVE",
"pickUpDate": "2025-10-18T14:30:00",
"returningDate": "2025-10-18T14:30:00"
}
```

**Response Example:**
```
{
"id": 1,
"userId": 1,
"advertisementId": 6,
"rentalStatus": "ACTIVE",
"pickUpDate": "2025-10-18T14:30:00",
"returningDate": "2025-10-18T14:30:00"
}
```

### User Endpoints

| Endpoint              | HTTP Method | Description                 | Access  |
|-----------------------|------------|-----------------------------|---------|
| /signup               | POST       | Create a new user with address | CLIENT |
| /login                | POST       | Login user                  | CLIENT |
| /login/{id}           | GET        | Get user by ID              | CLIENT |

### Bike Endpoints

| Endpoint                   | HTTP Method | Description                  | Access |
|----------------------------|------------|------------------------------|--------|
| /Bike/create                | POST       | Create a new Bike             | CLIENT |
| /Bike/update/{id}           | PUT        | Update existing Bike          | CLIENT |
| /Bike/show/{id}             | GET        | Get Bike by ID                | CLIENT |
| /personal/Bikes             | GET        | Get personal Bikes            | CLIENT |
| /Bike/delete/{id}           | DELETE     | Delete a specific Bike        | CLIENT |

### Advertisement Endpoints

| Endpoint                        | HTTP Method | Description                | Access |
|---------------------------------|------------|----------------------------|--------|
| /advertisement/create            | POST       | Create a new advertisement | CLIENT |
| /advertisement/all               | GET        | Get all advertisements     | CLIENT |
| /advertisement/show/{id}         | GET        | Get advertisement by ID    | CLIENT |
| /advertisement/delete/{id}       | DELETE     | Delete a specific ad       | CLIENT |

### Rental Endpoints

| Endpoint                       | HTTP Method | Description                  | Access |
|--------------------------------|------------|------------------------------|--------|
| /rent/advertisement             | POST       | Rent an advertisement        | CLIENT |
| /rental/cancel/{id}             | POST       | Cancel a rental              | CLIENT |
| /rental/approve/{id}            | POST       | Approve a rental             | CLIENT |
| /rental/show/{id}               | GET        | Get rental by ID             | CLIENT |

### RentalTrip Endpoints

| Endpoint                    | HTTP Method | Description                       | Access |
|-----------------------------|------------|-----------------------------------|--------|
| /rentalTrip/register        | POST       | Register a new rental trip        | CLIENT |

