package my.anmp.waroengujang.data.sharedpref

import android.content.SharedPreferences
import androidx.core.content.edit
import my.anmp.waroengujang.data.model.User


class SharedPrefHelper() {

    companion object {
        val authPrefKey = "Auth"
    }

    private val idKey = "id"
    private val nameKey = "name"
    private val profilePicKey = "img"
    private val workSinceKey = "worktime"

    fun storeUser(preferences: SharedPreferences, userData: User) {
        preferences.edit {
            putInt(idKey, userData.id!!)
            putString(nameKey, userData.name)
            putString(profilePicKey, userData.profilePic)
            putString(workSinceKey, userData.workSince)
            apply()
        }
    }

    fun deleteUser(preferences: SharedPreferences) {
        preferences.edit() {
            remove(idKey)
            remove(nameKey)
            remove(profilePicKey)
            remove(workSinceKey)
            apply()
        }
    }

    fun getUser(preferences: SharedPreferences): User {
        return User(
            preferences.getInt(idKey, 0),
            preferences.getString(nameKey, "Undefined"),
            preferences.getString(profilePicKey, "Undefined"),
            preferences.getString(workSinceKey, "Undefined")
        )
    }
}