package edu.xfolex.stockapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.xfolex.stockapp.data.local.entities.CompanyListingEntity

@Database(
    entities = [CompanyListingEntity::class],
    version = 1
)

abstract class StockDatabase: RoomDatabase() {

    abstract val dao: StockDatabase
}