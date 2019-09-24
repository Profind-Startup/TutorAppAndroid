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






class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myLoginEmailAddress = getLoginEmailAddress()
        val loginInformation = findViewById(R.id.login_email) as TextView
        if (myLoginEmailAddress != null || myLoginEmailAddress != "") {
            loginInformation.text = "Welcome!!! You have logged in as " + myLoginEmailAddress!!
        } else {
            loginInformation.text = "Your login email is missing"
        }

        rvSubjects.layoutManager =  LinearLayoutManager(this)

        getSubjects()

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

      /*  val retrofit = Retrofit.Builder()
            .baseUrl("http://tutorapp.somee.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val subjectInterface = retrofit.create(SubjectInfertace::class.java)

        val methodSubjectsData: Call<Subject> = subjectInterface.getSubjectResult()
*/
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://tutorapp.somee.com/api/").build()

        val postsApi = retrofit.create(SubjectInfertace::class.java)

        var response = postsApi.getAllSubjects()

        response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe(
                {   rvSubjects.adapter = SubjectAdapter(it, this)
                    // no op
                },
                { throwable ->
                    // throw new RuntimeException("Error observing strings", throwable);
                    // instead of throwing, just propagate
                    Exceptions.propagate(throwable)
                })



       /* methodSubjectsData.enqueue(object : Callback<Subject> {
            override fun onFailure(call: Call<Subject>, t: Throwable) {
                Log.d("Excepción: ", t.toString())
            }

            override fun onResponse(
                call: Call<Subject>,
                response: Response<Subject>
            ) {

                if (response.isSuccessful) {
                    subjects = response.body()!!.subjects
                    adapter = SubjectAdapter(subjects)
                    rvSubjects.adapter = adapter
                }

            }

        })
*/
    }
}
