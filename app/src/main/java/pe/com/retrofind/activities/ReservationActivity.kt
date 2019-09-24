package pe.com.retrofind.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.exceptions.Exceptions
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_reservation.*
import pe.com.retrofind.R
import pe.com.retrofind.adapter.ReservationAdapter
import pe.com.retrofind.adapter.SubjectAdapter
import pe.com.retrofind.models.ReservationInterface
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ReservationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R
            .layout.activity_reservation)

        rvReservations.layoutManager =  LinearLayoutManager(this)

        getReservations()
    }

    private fun getReservations() {

        val retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://tutorapp.somee.com/api/").build()

        val postsApi = retrofit.create(ReservationInterface::class.java)

        var response = postsApi.getAllReservations()

        response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe(
            {   rvReservations.adapter = ReservationAdapter(it, this)
                // no op
            },
            { throwable ->
                // throw new RuntimeException("Error observing strings", throwable);
                // instead of throwing, just propagate
                Exceptions.propagate(throwable)
            })

    }
}
