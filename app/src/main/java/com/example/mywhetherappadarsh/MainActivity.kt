package com.example.mywhetherappadarsh



import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import java.net.URL
import java.util.*


class MainActivity : AppCompatActivity() {
    var cityname: String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        buttonwhether.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                // Perform action on click
//               find_whether()
//            }
//        })
        buttonwhether.setOnClickListener { find_whether() }

}
    private fun find_whether() {
        val editText = findViewById<EditText>(R.id.edittext)
        cityname=editText.getText().toString()
        Toast.makeText(this,"$cityname",Toast.LENGTH_LONG).show()
        val myURL = "http://api.openweathermap.org/data/2.5/weather?q=$cityname&appid=c84adac79793c9ff9e676af2004580d9"
        var url = myURL


        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val json_mainobj = response.getJSONObject("main")
                    //    date.text = "Response: %s".format(response.toString())
                    var humidity = json_mainobj.getString("humidity")
                    val kelvinValue = json_mainobj.getDouble("temp")

                    val city = response.getString("name")
                    val Fahrenheit = (((kelvinValue - 273.15) * 9 / 5) + 32).toFloat()
                    val celsius = (kelvinValue - 273.15).toFloat()

                    //  val ab=null //= celsius.toInt()
                    // day.text="$ab klk"
                    //   celsius.text = "$celsius 'C"
                    celsiusview.text = "$celsius 'C"
                    fahrenheit.text = "$Fahrenheit'K"
                    val date = Date()
                    day.text = "$date"
                    mycity.text = ("$city")
                    humidityview.text = ("humidity $humidity % ")

                } catch (e: JSONException) {
                    Log.d("JSONException", e.localizedMessage)
                }


            },
            {
                Log.d("error", it.localizedMessage)
            }
        )

// Access the RequestQueue through your singleton class.

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
        //   mComtext?.let { MySingleton.getInstance(it).addToRequestQueue(jsonObjectRequest) }

    } }