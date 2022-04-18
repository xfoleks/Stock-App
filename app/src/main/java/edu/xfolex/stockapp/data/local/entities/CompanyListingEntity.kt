package edu.xfolex.stockapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CompanyListingEntity(
    @PrimaryKey val id: Long? = null,
    val name: String,
    val symbol: String,
    val exchange: String
)