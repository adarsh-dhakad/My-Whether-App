package com.example.mywhetherappadarsh

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_whether.*

import org.json.JSONException
import java.util.*


class WhetherFragment : Fragment() {

    private var mComtext:Context?=null


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mComtext=context
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

    find_whether()


     var   view = inflater.inflate(R.layout.fragment_whether, container, false)
        return view
    }
    private fun find_whether(){
        val url = "http://api.openweathermap.org/data/2.5/weather?q=guna&appid=c84adac79793c9ff9e676af2004580d9"

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
                   celsiusview.text="$celsius 'C"
                    fahrenheit.text = "$Fahrenheit'K"
                    val date = Date()
                  day.text="$date"
                    mycity.text=("$city")
                    humidityview.text=("humidity $humidity % ")
                    
                } catch (e: JSONException) {
             Log.d("JSONException", e.localizedMessage)
                }


            },
            {
                Log.d("error", it.localizedMessage)
            }
        )

// Access the RequestQueue through your singleton class.

        //  MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
        mComtext?.let { MySingleton.getInstance(it).addToRequestQueue(jsonObjectRequest) }

    }

}
