package pe.com.retrofind.models

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface ReservationInterface {

    @GET("reservations")
    fun getAllReservations(): Observable<List<Reservation>>

    @GET("Tutors/{id}/Reservations")
    fun getAllReservationsByTutor(@Path("id") groupId: Int): Observable<List<Reservation>>

    @GET("Reservations/{id}/details")
    fun getReservationDetails(@Path("id") groupId: Int): Observable<ReservationDetails>


}