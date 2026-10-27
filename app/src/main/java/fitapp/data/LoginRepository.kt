package fitapp.data

import fitapp.data.model.LoggedInUser

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    @Suppress("unused")
    val isLoggedIn: Boolean
        get() = user != null

    init {
        user = null
    }

    @Suppress("unused")
    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(@Suppress("UNUSED_PARAMETER") username: String, password: String): DataResult<LoggedInUser> {
        // handle login
        val result = dataSource.login(password)

        if (result is DataResult.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
    }
}
