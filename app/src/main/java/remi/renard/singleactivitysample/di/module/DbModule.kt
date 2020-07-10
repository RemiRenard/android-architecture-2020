package remi.renard.singleactivitysample.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import remi.renard.singleactivitysample.data.db.AppRoomDatabase
import remi.renard.singleactivitysample.data.db.MIGRATION_1_2
import remi.renard.singleactivitysample.data.db.dao.UserDao
import javax.inject.Singleton

/**
 * Module that provides all dependencies from the cache package/layer.
 */
@Module
object DbModule {

    @Provides
    @Singleton
    fun provideArticleDao(database: AppRoomDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppRoomDatabase {
        return Room.databaseBuilder(context, AppRoomDatabase::class.java, "app-db-room.db")
            .addMigrations(MIGRATION_1_2)
            //.allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}
