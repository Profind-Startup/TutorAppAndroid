package pe.com.retrofind.data

import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.exceptions.Exceptions
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_main.*
import pe.com.retrofind.adapter.SubjectAdapter
import pe.com.retrofind.data.model.LoggedInUser
import pe.com.retrofind.models.SubjectInfertace
import pe.com.retrofind.models.User
import pe.com.retrofind.models.UserInterface
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
           val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "User")

         /*   val users = retrofit.create(UserInterface::class.java)
            var response = users.getAllUsers()
*/
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}

