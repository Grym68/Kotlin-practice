package co.uk.practice.shopinglisttesting.data.remote.reponses

data class ImageResponse(
    val hits: List<ImageResult>,
    val total: Int,
    val totalHits: Int
)
