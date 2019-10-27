package pe.com.retrofind.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.exceptions.Exceptions
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_add_subject.*
import kotlinx.android.synthetic.main.activity_main.*
import pe.com.retrofind.R
import pe.com.retrofind.adapter.SubjectAdapter
import pe.com.retrofind.models.Subject
import pe.com.retrofind.models.SubjectInfertace
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import org.reactivestreams.Subscriber
import io.reactivex.schedulers.Schedulers
import pe.com.retrofind.data.SharedPreference


class AddSubjectActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_subject)



        btnAddSubject.setOnClickListener{
            AddSubject()

                this.finish()
        }
    }

     fun AddSubject()
    {
        val retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://tutorapp.somee.com/api/").build()

        val postsApi = retrofit.create(SubjectInfertace::class.java)
        val sp = SharedPreference(this)
        var subject = Subject(1, etTema.text.toString(), etArea.text.toString(),sp.getValueInt("tutor_id"))
        postsApi.postSubject(subject).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> Log.v("POSTED SUBJECT", "" ) },
                { error -> Log.e("ERROR", error.message ) }
            )
    }



}


