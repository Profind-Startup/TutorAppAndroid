package pe.com.retrofind.models

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface TutorInterface {

    @GET("tutors")
    fun getAllTutors(): Observable<List<Tutor>>

    @GET("Users/{id}/Tutors")
    fun getTutorByUserId(@Path("id") groupId: Int): Observable<Tutor>
}