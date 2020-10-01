package com.example.mywhetherappadarsh




import android.content.Context

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
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
   //     val intent = Intent(context, testActivity::class.java)
  //      val pendingIntent = getActivity(context, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK)

        val mgr: InputMethodManager =
           getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mgr.hideSoftInputFromWindow(editText.getWindowToken(), 0)
        if(cityname.isNullOrBlank()){

            Toast.makeText(this, "Please enter city name ", Toast.LENGTH_LONG).show()

        }
           else if (cityname!!.contains(" ")) {
            Toast.makeText(this, "Please enter valid name ", Toast.LENGTH_LONG).show()
            }
        else {
            Toast.makeText(this, "$cityname", Toast.LENGTH_LONG).show()
            val myURL =
                "http://api.openweathermap.org/data/2.5/weather?q=$cityname&appid=c84adac79793c9ff9e676af2004580d9"
            var url = myURL


            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    try {
                        val json_mainobj = response.getJSONObject("main")
                        val json_mainobj2 = response.getJSONObject("sys")
                        //    date.text = "Response: %s".format(response.toString())
                        var humidity = json_mainobj.getString("humidity")
                        val kelvinValue = json_mainobj.getDouble("temp")
                        val country = json_mainobj2.getString("country")
                        val city = response.getString("name")
                        val Fahrenheit = (((kelvinValue - 273.15) * 9 / 5) + 32).toFloat()
                        val celsius = (kelvinValue - 273.15).toFloat()

                        //  val ab=null //= celsius.toInt()
                        // day.text="$ab klk"
                        //   celsius.text = "$celsius 'C"
                        countryview.text="$country"
                        celsiusview.text = "$celsius 'C"
                        fahrenheit.text = "$Fahrenheit'K"
                        val date = Date()
                        day.text = "$date"
                        mycity.text = ("$city")
                        humidityview.text = ("humidity $humidity % ")
                        textView2.text = ""

                    } catch (e: JSONException) {
                        Log.d("JSONException", e.localizedMessage)
                    }

                },

                Response.ErrorListener {
                    textView2.text = "You enter wrong city!"

                    var mPlayer2 = MediaPlayer.create(this, R.raw.error);
                    mPlayer2.start();

                })

//                  Toast.makeText(this,"you enter wrong city name",Toast.LENGTH_LONG)
//                    Log.d("error", it.localizedMessage)
//                //    Log.w("error in response", "Error: " + error.getMessage());
//
//                }
//            )

// Access the RequestQueue through your singleton class.

            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
            //   mComtext?.let { MySingleton.getInstance(it).addToRequestQueue(jsonObjectRequest) }
        }
    } }