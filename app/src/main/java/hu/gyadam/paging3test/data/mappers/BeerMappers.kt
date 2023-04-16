package hu.gyadam.paging3test.data.mappers

import hu.gyadam.paging3test.data.local.BeerEntity
import hu.gyadam.paging3test.data.remote.BeerDto
import hu.gyadam.paging3test.domain.Beer


fun BeerDto.toBeerEntity() = BeerEntity(
    id = id,
    name = name,
    tagline = tagline,
    description = description,
    firstBrewed = first_brewed,
    imageUrl = image_url
)

fun BeerEntity.toBeer() = Beer(
    id = id,
    name = name,
    tagline = tagline,
    description = description,
    firstBrewed = firstBrewed,
    imageUrl = imageUrl
)