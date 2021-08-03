package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Realm.init(activity)
        fitchData()
        recycler_home.layoutManager =
            LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        recycler_home.adapter = adapter_home()

    }

    fun fitchData() {
        val url = "https://jsonplaceholder.typicode.com/"
        //to configure the retrofit and start using it and convert the data coming to json format
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api: API = retrofit.create(API::class.java)
        //here we put postId=certain number as we declared that we only want the posts that equal to a certain postId not all of them
        val call =
            api.getData() //the method in the interface //as to connect with the link i have but with index

        var item = call.enqueue(object : Callback<ArrayList<data>> {
            //when there is connection with server ,
            override fun onResponse(
                call: Call<ArrayList<data>>,
                response: Response<ArrayList<data>>
            ) {
                Toast.makeText(activity, "There is connection with the server", Toast.LENGTH_LONG)
                    .show()
                //retreive the data from the server , in array of same type of Data class we created
                var allData: ArrayList<data> = response.body()!!
                //configuration of realm then for loop to retreiev all data reacived by the array allData that recieve data by retrofit
                //as to put each data in a single object of type database called lesson and assosiate the primary id with it
                //so now lesson variable is object contain all the data in a single item of the data stored

                var config = RealmConfiguration.Builder().name("cplus.realm").build()
                var realm = Realm.getInstance(config)
                //used as to make sure if there is data already , not to read them again from the server
                if (realm.where(Database::class.java).findAll().isEmpty()) {
                    realm.beginTransaction()
                    for (i in allData) {
                        val lesson = realm.createObject(Database::class.java, i.id)
                        lesson.name = i.name
                        lesson.body = i.body
                        lesson.email = i.email
                    }
                }
                //here if the data on sever is updated , will update them also in the realm but we need to delete the old data and write the new data
                if (realm.where(Database::class.java).findAll().size != allData!!.size) {
                    realm.beginTransaction()
                    realm.deleteAll()
                    for (i in allData) {
                        val lesson = realm.createObject(Database::class.java, i.id)
                        lesson.name = i.name
                        lesson.body = i.body
                        lesson.email = i.email
                    }
                }


                recycler_home.adapter = adapter_home()
            }

            //when there is no connection with server
            override fun onFailure(call: Call<ArrayList<data>>, t: Throwable) {
                Toast.makeText(
                    activity,
                    "There is no Connection with the server",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}