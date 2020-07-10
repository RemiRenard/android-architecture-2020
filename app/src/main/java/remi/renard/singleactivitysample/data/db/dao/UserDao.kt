package remi.renard.singleactivitysample.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import remi.renard.singleactivitysample.domain.model.User

@Dao
abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUser(user: User)

    @Query("SELECT * FROM users")
    abstract fun getUsers(): LiveData<List<User>>

    @Query("DELETE FROM users")
    abstract fun clearUsers()

    @Transaction
    open fun clearAndInsertUsers(users: List<User>) {
        clearUsers()
        users.forEach { insertUser(it) }
    }
}
