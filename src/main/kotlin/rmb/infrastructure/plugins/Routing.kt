package rmb.infrastructure.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.routing
import rmb.application.auth.Authenticator
import rmb.application.usecases.advertisement.CreateAdvertisementUsecase
import rmb.application.usecases.advertisement.DeleteAdvertisementUsecase
import rmb.application.usecases.advertisement.GetAdvertisementUsecase
import rmb.application.usecases.advertisement.GetAvailableAdvertisementsUsecase
import rmb.application.usecases.advertisement.UpdateAdvertisementUsecase
import rmb.application.usecases.bike.CreatebikeUsecase
import rmb.application.usecases.bike.DeletebikeUsecase
import rmb.application.usecases.bike.GetbikeUsecase
import rmb.application.usecases.bike.GetPersonalbikesUsecase
import rmb.application.usecases.bike.UpdatebikeUsecase
import rmb.application.usecases.rental.ApproveRentalUsecase
import rmb.application.usecases.rental.CancelRentalUsecase
import rmb.application.usecases.rental.GetRentalUsecase
import rmb.application.usecases.rental.RentAdvertisementUsecase
import rmb.application.usecases.rentaltrip.RegisterRentalTripUsecase
import rmb.application.usecases.user.LoginUsecase
import rmb.application.usecases.user.SignupUsecase
import rmb.domain.repositories.AddressRepositoryInterface
import rmb.domain.repositories.AdvertisementRepositoryInterface
import rmb.domain.repositories.bikeImageRepositoryInterface
import rmb.domain.repositories.bikeRepositoryInterface
import rmb.domain.repositories.RentalRepositoryInterface
import rmb.domain.repositories.RentalTripRepositoryInterface
import rmb.domain.repositories.UserRepositoryInterface
import rmb.domain.validatie.ExistingbikeAdvertisementValidator
import rmb.domain.validatie.ExistingbikeValidator
import rmb.infrastructure.auth.JwtTokenGenerator
import rmb.infrastructure.repositories.AddressRepository
import rmb.infrastructure.repositories.AdvertisementRepository
import rmb.infrastructure.repositories.bikeImageRepository
import rmb.infrastructure.repositories.bikeRepository
import rmb.infrastructure.repositories.RentalRepository
import rmb.infrastructure.repositories.RentalTripRepository
import rmb.infrastructure.repositories.UserRepository
import rmb.presentation.routes.advertisement.createAdvertisementRoute
import rmb.presentation.routes.advertisement.deleteAdvertisementRoute
import rmb.presentation.routes.advertisement.getAdvertisementRoute
import rmb.presentation.routes.advertisement.getAvailableAdvertisementsRoute
import rmb.presentation.routes.advertisement.updateAdvertisementRoute
import rmb.presentation.routes.bike.createbikeRoute
import rmb.presentation.routes.bike.deletebikeRoute
import rmb.presentation.routes.bike.getbikeRoute
import rmb.presentation.routes.bike.getPersonalbikesRoute
import rmb.presentation.routes.bike.updatebikeRoute
import rmb.presentation.routes.rental.approveRentalRoute
import rmb.presentation.routes.rental.cancelRentalRoute
import rmb.presentation.routes.rental.getRentalRoute
import rmb.presentation.routes.rental.rentAdvertisementRoute
import rmb.presentation.routes.rentaltrip.registerRentalTripRoute
import rmb.presentation.routes.user.userLoginRoute
import rmb.presentation.routes.user.userSignupRoute

fun Application.configureRouting() {
    val addressRepository: AddressRepositoryInterface = AddressRepository()
    val userRepository: UserRepositoryInterface = UserRepository(addressRepository)
    val bikeRepository: bikeRepositoryInterface = bikeRepository()
    val advertisementRepository: AdvertisementRepositoryInterface = AdvertisementRepository(addressRepository)
    val bikeImageRepository: bikeImageRepositoryInterface = bikeImageRepository()
    val rentalRepository: RentalRepositoryInterface = RentalRepository()
    val rentalTripRepository: RentalTripRepositoryInterface = RentalTripRepository()

    val existingbikeValidator = ExistingbikeValidator(bikeRepository)
    val existingbikeAdvertisementValidator = ExistingbikeAdvertisementValidator(advertisementRepository)

    val userLoginUsecase = LoginUsecase(userRepository)
    val userSignupUsecase = SignupUsecase(userRepository)
    val createbikeUsecase = CreatebikeUsecase(bikeRepository, userRepository, existingbikeValidator, bikeImageRepository)
    val updatebikeUsecase = UpdatebikeUsecase(bikeRepository, existingbikeValidator, userRepository, bikeImageRepository)
    val getbikeUsecase = GetbikeUsecase(bikeRepository, bikeImageRepository)
    val getPersonalbikesUsecase = GetPersonalbikesUsecase(bikeRepository, bikeImageRepository)
    val deletebikeUsecase = DeletebikeUsecase(bikeRepository, bikeImageRepository, advertisementRepository, rentalRepository)
    val createAdvertisementUsecase = CreateAdvertisementUsecase(bikeRepository, advertisementRepository, existingbikeAdvertisementValidator)
    val getAdvertisementUsecase = GetAdvertisementUsecase(advertisementRepository)
    val getAvailableAdvertisementsUsecase = GetAvailableAdvertisementsUsecase(advertisementRepository)
    val rentAdvertisementUsecase = RentAdvertisementUsecase(rentalRepository, userRepository, advertisementRepository)
    val registerRentalTripUsecase = RegisterRentalTripUsecase(rentalTripRepository, rentalRepository)
    val getRentalUsecase = GetRentalUsecase(rentalRepository, rentalTripRepository)
    val cancelRentalUsecase = CancelRentalUsecase(rentalRepository)
    val approveRentalUsecase = ApproveRentalUsecase(rentalRepository)
    val deleteAdvertisementUsecase = DeleteAdvertisementUsecase(advertisementRepository, bikeRepository, rentalRepository)
    val updateAdvertisementUsecase = UpdateAdvertisementUsecase(advertisementRepository)

    val jwtSecret = environment.config.property("jwt.secret").getString()
    val jwtAudience = environment.config.property("jwt.audience").getString()
    val jwtDomain = environment.config.property("jwt.domain").getString()

    val tokenGenerator = JwtTokenGenerator(jwtSecret, jwtAudience, jwtDomain)
    val authenticator = Authenticator(tokenGenerator)

    routing {
        userLoginRoute(userLoginUsecase, authenticator)
        userSignupRoute(userSignupUsecase)
        createbikeRoute(createbikeUsecase)
        updatebikeRoute(updatebikeUsecase)
        getbikeRoute(getbikeUsecase)
        getPersonalbikesRoute(getPersonalbikesUsecase)
        deletebikeRoute(deletebikeUsecase)
        createAdvertisementRoute(createAdvertisementUsecase)
        getAdvertisementRoute(getAdvertisementUsecase)
        getAvailableAdvertisementsRoute(getAvailableAdvertisementsUsecase)
        rentAdvertisementRoute(rentAdvertisementUsecase)
        registerRentalTripRoute(registerRentalTripUsecase)
        getRentalRoute(getRentalUsecase)
        cancelRentalRoute(cancelRentalUsecase)
        approveRentalRoute(approveRentalUsecase)
        deleteAdvertisementRoute(deleteAdvertisementUsecase)
        updateAdvertisementRoute(updateAdvertisementUsecase)
    }
}
