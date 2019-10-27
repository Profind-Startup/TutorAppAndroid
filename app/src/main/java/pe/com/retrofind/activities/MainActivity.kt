package pe.com.retrofind.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_main.*
import pe.com.retrofind.R
import pe.com.retrofind.adapter.SubjectAdapter
import pe.com.retrofind.models.SubjectInfertace

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import io.reactivex.exceptions.Exceptions
import androidx.recyclerview.widget.LinearLayoutManager

import android.widget.TextView
import androidx.core.app.NotificationCompat.getExtras
import android.content.Intent
import android.view.View
import android.widget.Button
import pe.com.retrofind.data.SharedPreference


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myLoginEmailAddress = getLoginEmailAddress()
        val loginInformation = findViewById(R.id.login_email) as TextView

        rvSubjects.layoutManager =  LinearLayoutManager(this)

        getSubjects()

        // get reference to button
        val btn_click_me = findViewById(R.id.btnReservations) as Button
        var addbuton = findViewById(R.id.btnAdd) as Button

// set on-click listener
        btn_click_me.setOnClickListener {
            val intent = Intent(this, ReservationActivity::class.java)
            this.startActivity(intent)
        }
        addbuton.setOnClickListener {
            val intent = Intent(this, AddSubjectActivity::class.java)
            this.startActivity(intent)
        }

    }
    private fun getLoginEmailAddress(): String {
        var storedEmail: String? = ""
        val mIntent = intent
        val mBundle = mIntent.extras
        if (mBundle != null) {
            storedEmail = mBundle.getString("EMAIL")
        }
        return storedEmail!!
    }
    private fun getSubjects() {

        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://tutorapp.somee.com/api/").build()

        val postsApi = retrofit.create(SubjectInfertace::class.java)

        val sp = SharedPreference(this)
        var response = postsApi.getAllSubjectsByTutor(sp.getValueInt("tutor_id"))

        response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe(
                {   rvSubjects.adapter = SubjectAdapter(it, this)
                    // no op
                },
                { throwable ->
                    // throw new RuntimeException("Error observing strings", throwable);
                    // instead of throwing, just propagate
                    Exceptions.propagate(throwable)
                })


    }
}
