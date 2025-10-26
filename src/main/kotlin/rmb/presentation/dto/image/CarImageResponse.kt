package rmb.presentation.dto.image

import kotlinx.serialization.Serializable

@Serializable
data class bikeImageResponse(
    val id: Int? = null,
    val bikeId: Int,
    val image: String,
    val weight: Int,
)
