package com.dsoft.liverpooldirectory.data.mappers

fun com.dsoft.liverpooldirectory.data.api.dto.vk.wall.VKWall.toModel(): List<VKWall> {
    val list = mutableListOf<VKWall>()
    if (error != null) {
        list.add(VKWall(
                0,
                "",
                "",
                0,
                0,
                0,
                0,
                0,
                errorCode = error.error_code ?: 0))
        return list.toList()
    } else {
        return response?.items?.map {
            val image =
                it.attachments?.firstOrNull()?.photo?.sizes?.firstOrNull { size -> size.type == "r" }
            VKWall(
                text = it.text ?: "",
                image = image?.url ?: "",
                likesCount = it.likes?.count ?: 0,
                commentsCount = it.comments?.count ?: 0,
                viewCount = it.views?.count ?: 0,
                postId = it.id ?: 0,
                imageHeight = image?.height ?: 0,
                imageWidth = image?.width ?: 0,
                errorCode = 0
            )
        }!!.toList()
    }
}

fun com.dsoft.liverpooldirectory.data.api.dto.vk.comments.VKComment.toModel(): List<VKComment> {
    return response.items.map {
        VKComment(
            userId = it.from_id ?: 0,
            text = it.text ?: "",
            date = it.date ?: 0
        )
    }.toList()
}
