package pe.com.retrofind.models

import io.reactivex.Observable
import retrofit2.http.GET


interface ReservationInterface {

    @GET("reservations")
    fun getAllReservations(): Observable<List<Reservation>>
}