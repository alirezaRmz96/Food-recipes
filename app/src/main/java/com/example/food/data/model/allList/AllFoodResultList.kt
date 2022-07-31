package com.example.food.data.model.allList
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "food")
data class AllFoodResultList(
    @PrimaryKey(autoGenerate = true)  val idd: Int = UUID.randomUUID().toString().toInt(),
    val id: Int,
    val image: String,
    val imageType: String,
    val title: String,
    val isFetchingArticles: Boolean = false,
//    @ColumnInfo(name = "name_category") var categoryName: Boolean = false
) : Serializable