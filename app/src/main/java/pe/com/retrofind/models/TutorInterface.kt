package pe.com.retrofind.models

import io.reactivex.Observable
import retrofit2.http.*


interface TutorInterface {

    @GET("tutors")
    fun getAllTutors(): Observable<List<Tutor>>

    @GET("Users/{id}/Tutor")
    fun getTutorByUserId(@Path("id") groupId: Int): Observable<Tutor>

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("tutors")
    fun createTutor(@Body tutor: Tutor): Observable<Tutor>
}