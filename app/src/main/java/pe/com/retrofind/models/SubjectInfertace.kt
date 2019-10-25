package pe.com.retrofind.models

import android.telecom.Call
import io.reactivex.Observable
import retrofit2.http.*
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST




interface SubjectInfertace {

    @GET("subjects")
    fun getAllSubjects(): Observable<List<Subject>>

    @GET("Tutors/1/Reservations/Subjects")
    fun getAllSubjectsByTutor(): Observable<List<Subject>>

    @POST("subjects")
    @FormUrlEncoded
    fun savePost(
        @Field("id") title: Int,
        @Field("name") name: String,
        @Field("area") area: String
    ): Observable<Subject>

}