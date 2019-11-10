package pe.com.retrofind.activities

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_register.*
import pe.com.retrofind.R
import pe.com.retrofind.data.SharedPreference
import pe.com.retrofind.models.Tutor
import pe.com.retrofind.models.TutorInterface
import pe.com.retrofind.models.User
import pe.com.retrofind.models.UserInterface
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private val CERO = "0"
    private val BARRA = "/"
    private val DOS_PUNTOS = ":"

    val c = Calendar.getInstance()

    val mes = c.get(Calendar.MONTH)
    val dia = c.get(Calendar.DAY_OF_MONTH)
    val anio = c.get(Calendar.YEAR)

    var etFecha: EditText? = null
    var etHora: EditText? = null

    var etDateCreat: EditText? = null
    var etBirthdate: EditText? = null

    var ibObtenerFecha: ImageButton? = null
    var ibObtenerHora: ImageButton? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btn_click_me = findViewById(R.id.btnRegistrarme) as Button
// set on-click listener

        etFecha = findViewById(R.id.etDateCreat) as EditText
        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha
        ibObtenerFecha = findViewById(R.id.ib_obtener_fecha) as ImageButton
        //Evento setOnClickListener - clic
        ibObtenerFecha!!.setOnClickListener(this)

        etHora = findViewById(R.id.etBirthdate) as EditText
        //Widget ImageButton del cual usaremos el evento clic para obtener la hora
        ibObtenerHora = findViewById(R.id.ib_obtener_hora) as ImageButton
        //Evento setOnClickListener - clic
        ibObtenerHora!!.setOnClickListener(this)

        btn_click_me.setOnClickListener {

            saveUser()


        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ib_obtener_fecha -> obtenerFecha()

        }
        when (v.id) {
            R.id.ib_obtener_hora -> obtenerFecha2()
        }

    }

    private fun obtenerFecha2() {
        val recogerFecha = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                val mesActual = month + 1
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                val diaFormateado =
                    if (dayOfMonth < 10) CERO + dayOfMonth.toString() else dayOfMonth.toString()
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                val mesFormateado =
                    if (mesActual < 10) CERO + mesActual.toString() else mesActual.toString()
                //Muestro la fecha con el formato deseado
                etHora!!.setText(year.toString() + BARRA + mesFormateado + BARRA + diaFormateado)
            },
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             * También puede cargar los valores que usted desee
             */
            anio, mes, dia
        )
        //Muestro el widget
        recogerFecha.show()

    }

    private fun obtenerFecha() {
        val recogerFecha = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                val mesActual = month + 1
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                val diaFormateado =
                    if (dayOfMonth < 10) CERO + dayOfMonth.toString() else dayOfMonth.toString()
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                val mesFormateado =
                    if (mesActual < 10) CERO + mesActual.toString() else mesActual.toString()
                //Muestro la fecha con el formato deseado
                etFecha!!.setText(year.toString() + BARRA + mesFormateado + BARRA + diaFormateado)
            },
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             * También puede cargar los valores que usted desee
             */
            anio, mes, dia
        )
        //Muestro el widget
        recogerFecha.show()

    }


    private fun saveUser() {
        val retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://tutorapp.somee.com/api/").build()

        val postsApi = retrofit.create(UserInterface::class.java)
        val sp = SharedPreference(this)
        var user = User(1,etNames.text.toString(),etLastNames.text.toString(),etDNI.text.toString(),etAddress.text.toString(),etUser.text.toString(), etPassword.text.toString())
        postsApi.createUser(user).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> Log.v("POSTED USER", "" )
                    checkUserRegistered()},
                { error -> Log.e("ERROR", error.message ) }
            )
    }
    private fun saveTutor(user_id: Int) {
        val retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://tutorapp.somee.com/api/").build()

        val postsApi = retrofit.create(TutorInterface::class.java)
        val sp = SharedPreference(this)
        var tutor = Tutor(1, user_id, etAcadGroup.text.toString(), etFecha!!.text.toString(), etAddress.text.toString(),etHora!!.text.toString() )
        postsApi.createTutor(tutor).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> Log.v("POSTED TUTOR", "" )
                    checkTutorRegistered()
                },
                { error -> Log.e("ERROR", error.message ) }
            )
    }

    private fun checkUserRegistered()
    {

        val retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://tutorapp.somee.com/api/").build()

        val postsApi = retrofit.create(UserInterface::class.java)
        var user = User(0,"","","","",etUser.text.toString(),etPassword.text.toString())
        postsApi.checkUser(user).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {

                    if(it == null)

                    else {
                        val sp = SharedPreference(this)
                        sp.save("user_id",it.id)

                        saveTutor(it.id)

                    }
                },
                { error -> Log.e("ERROR", error.message )

                }
            )


    }


    private fun checkTutorRegistered()
    {

        val retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://tutorapp.somee.com/api/").build()

        val postsApi = retrofit.create(TutorInterface::class.java)
        val sp = SharedPreference(this)
        postsApi.getTutorByUserId(sp.getValueInt("user_id")).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {

                    if(it == null)

                    else {
                        val sp = SharedPreference(this)
                        sp.save("tutor_id",it.id)


                        val intent = Intent(this, MainActivity::class.java)
                        this.startActivity(intent)
                        finish()
                    }
                },
                { error -> Log.e("ERROR", error.message )

                }
            )


    }

}
