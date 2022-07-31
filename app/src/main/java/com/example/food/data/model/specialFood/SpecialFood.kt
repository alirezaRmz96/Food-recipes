package com.example.food.data.model.specialFood

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.food.data.model.allList.AllFoodResultList
import java.util.*
import kotlin.collections.ArrayList

//@Entity(tableName = "food")
class SpecialFood
//    @PrimaryKey(autoGenerate = true)  val idd: Int = UUID.randomUUID().toString().toInt(),
//    @PrimaryKey val idd: Int = UUID.randomUUID().toString().toInt(),
//    @ColumnInfo(name = "name_category") var categoryName: Boolean = false
 : ArrayList<AllFoodResultList>()
//data class SpecialFood (
////    @PrimaryKey(autoGenerate = true)  val idd: Int = UUID.randomUUID().toString().toInt(),
//    val allFoodResultList : ArrayList<AllFoodResultList>,
//    )