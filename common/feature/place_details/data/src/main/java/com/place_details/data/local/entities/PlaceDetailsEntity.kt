package com.place_details.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.place_details.data.local.PlaceDetailsDatabaseContract
import com.place_details.domain.models.places.Category

@Entity(
    tableName = PlaceDetailsDatabaseContract.TABLE_NAME,
    indices = [Index(PlaceDetailsDatabaseContract.ID)]
)
@TypeConverters(
    PlaceDetailsConverter::class
)
data class PlaceDetailsEntity(

    @ColumnInfo(PlaceDetailsDatabaseContract.ID)
    @PrimaryKey
    val id: String,

    @ColumnInfo(PlaceDetailsDatabaseContract.NAME)
    val name: String,

    @ColumnInfo(PlaceDetailsDatabaseContract.ADDRESS)
    val address: String,

    @ColumnInfo(PlaceDetailsDatabaseContract.BEST_PHOTO)
    val bestPhoto: String,

    @ColumnInfo(PlaceDetailsDatabaseContract.PLACE_PHOTOS)
    val placePhotos: List<String>,

    @ColumnInfo(PlaceDetailsDatabaseContract.CATEGORIES)
    val categories: List<Category>,

    @ColumnInfo(PlaceDetailsDatabaseContract.PHONE)
    val phone: String?,

    @ColumnInfo(PlaceDetailsDatabaseContract.TWITTER)
    val twitter: String?,

    @ColumnInfo(PlaceDetailsDatabaseContract.FACEBOOK)
    val facebook: String?,

    @ColumnInfo(PlaceDetailsDatabaseContract.INSTAGRAM)
    val instagram: String?,
)

class PlaceDetailsConverter {

    @TypeConverter
    fun fromListOfString(strings: List<String>): String = strings.joinToString(",")

    @TypeConverter
    fun toListOfStrings(string: String): List<String> = string.split(",")

    @TypeConverter
    fun fromListOfCategories(categories: List<Category>): String {
        var result = ""
        categories.forEach { category ->
            result += "${category.id},${category.name},${category.icon};"
        }
        return result
    }

    @TypeConverter
    fun toListOfCategories(string: String): List<Category> {
        val categories = mutableListOf<Category>()
        string.dropLast(1).split(";").forEach { categoryString ->
            val category = categoryString.split(",")
            categories.add(
                Category(
                    id = category[0],
                    name = category[1],
                    icon = category[2]
                )
            )
        }
        return categories.toList()
    }

}