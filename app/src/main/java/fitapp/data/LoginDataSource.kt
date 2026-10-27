package fitapp.data

import fitapp.data.model.LoggedInUser
import java.io.IOException
import java.util.UUID

class LoginDataSource {

    fun login(password: String): DataResult<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val pwd = password
            val fakeUser = LoggedInUser(UUID.randomUUID().toString(), pwd.hashCode().toString())
            return DataResult.Success(fakeUser)
        } catch (e: Throwable) {
            return DataResult.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}
