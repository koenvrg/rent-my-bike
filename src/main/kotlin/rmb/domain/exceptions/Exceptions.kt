package rmb.domain.exceptions

class bikeAlreadyExistsException(message: String) : RuntimeException(message)

class UnauthorizedbikeAccessException(message: String) : RuntimeException(message)

class AdvertisementAlreadyExistsForbikeException(message: String) : RuntimeException(message)

class RentalNotPendingException(message: String) : RuntimeException(message)

class RentalCannotBeCancelledException(message: String) : RuntimeException(message)
