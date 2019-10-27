package pe.com.retrofind.models

import android.telecom.Call
import io.reactivex.Observable
import retrofit2.http.*
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST




interface SubjectInfertace {

    @GET("subjects")
    fun getAllSubjects(): Observable<List<Subject>>

    @GET("Tutors/{id}/Subjects")
    fun getAllSubjectsByTutor(@Path("id") groupId: Int): Observable<List<Subject>>

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("subjects")
    fun postSubject(@Body subject: Subject): Observable<Subject>

}