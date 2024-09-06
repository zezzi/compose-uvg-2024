package com.eventapp.eventappuvg.ui.concerts.model

data class Concert(
    val id: Long,
    val tourName: String,
    val artist: String,
    val dateDetails: List<ConcertDetail>,
    val description: String,
    val concertImage: Int,
    val imageUrl: String? = null,
)