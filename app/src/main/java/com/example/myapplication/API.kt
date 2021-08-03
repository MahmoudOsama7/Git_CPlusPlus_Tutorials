package com.example.myapplication

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface API {
    //notation

    @GET("comments/")
    //method to get the data of type mentionned , since interface , so with no body
    fun getData():Call<ArrayList<data>>

    //if i want to get certain posts of certain id or post id or name
    /*
        //notation
    @GET("comments/")
    //method to get the data of type mentionned , since interface , so with no body
    fun getData(@Query("postId")postId:Int):Call<ArrayList<data>>
     */
    //if i want to change this id dynamically
/*
    @GET("comments/{postId}")
    fun getData(@Path("postId") postId:Int):Call<ArrayList<data>>
 */
    //create a post request
    /*
    @POST("posts")
    fun postData(@Body data: data):Call<data>


     */
}