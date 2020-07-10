package remi.renard.singleactivitysample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import remi.renard.singleactivitysample.data.db.converter.DateConverter
import remi.renard.singleactivitysample.data.db.dao.UserDao
import remi.renard.singleactivitysample.domain.model.User

@Database(
    entities = [
        User::class
    ], version = 1, exportSchema = true
)
@TypeConverters(DateConverter::class)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}
