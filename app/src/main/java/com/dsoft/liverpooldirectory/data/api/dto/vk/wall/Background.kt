package com.dsoft.liverpooldirectory.data.api.dto.vk.wall

data class Background(
    val angle: Int,
    val color: String,
    val id: Int,
    val name: String,
    val points: List<Point>,
    val type: String
)