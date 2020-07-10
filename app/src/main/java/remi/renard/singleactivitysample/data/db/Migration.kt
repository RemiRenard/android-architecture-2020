@file:Suppress("MagicNumber")
@file:JvmName("Migration")

package remi.renard.singleactivitysample.data.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Here is the sql code to migrate the db.
        // database.execSQL("")
    }
}
