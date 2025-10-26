package rmb.application.exceptions

class InvalidCredentialsException(message: String) : RuntimeException(message)

class bikeNotFoundException(message: String) : RuntimeException(message)

class bikeHasActiveRentalException(message: String) : RuntimeException(message)

class InvalidUserException(message: String) : RuntimeException(message)

class UserNotFoundException(message: String) : RuntimeException(message)

class UserAlreadyExistsException(message: String) : RuntimeException(message)

class AdvertisementNotFoundException(message: String) : RuntimeException(message)

class AdvertisementHasActiveRentalException(message: String) : RuntimeException(message)

class RentalNotFoundException(message: String) : RuntimeException(message)
