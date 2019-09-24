package pe.com.retrofind.models

import io.reactivex.Observable
import retrofit2.http.GET


interface UserInterface {

    @GET("users")
    fun getAllUsers(): Observable<List<User>>
}