package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.home_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class adapter_home(): RecyclerView.Adapter<adapter_home.ViewHolderIndex>() {


    var config= RealmConfiguration.Builder().name("cplus.realm").build()
    var realm= Realm.getInstance(config)
    var allData=realm.where(Database::class.java).findAll()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter_home.ViewHolderIndex
    {
        var myView=LayoutInflater.from(parent.context).inflate(R.layout.home_item,parent,false)
        return ViewHolderIndex(myView)
    }
    override fun onBindViewHolder(holder: adapter_home.ViewHolderIndex, position: Int) {
        val data = allData[position]
        holder.lessonId.text=data!!.name
        holder.lessonId.setOnClickListener{
            var intentLesson=Intent(holder.itemView.context,LessonActivity::class.java)
            intentLesson.putExtra("id",data.id)
            intentLesson.putExtra("title",data.name)
            intentLesson.putExtra("body",data.body)
            intentLesson.putExtra("image","https://upload.wikimedia.org/wikipedia/commons/thumb/1/18/ISO_C%2B%2B_Logo.svg/1200px-ISO_C%2B%2B_Logo.svg.png")
            holder.itemView.context.startActivity(intentLesson)
        }
    }
    override fun getItemCount(): Int {
        return allData.size
    }
    class ViewHolderIndex(itemView: View):RecyclerView.ViewHolder(itemView) {

        val lessonId=itemView.lessonId as TextView
    }
}
