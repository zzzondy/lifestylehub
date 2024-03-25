package com.main.domain.models.places

sealed class PagingItem {

    data class Place(
        val id: String,
        val name: String,
        val address: String,
        val photo: String,
        val categories: List<Category>,
    ) : PagingItem()
}