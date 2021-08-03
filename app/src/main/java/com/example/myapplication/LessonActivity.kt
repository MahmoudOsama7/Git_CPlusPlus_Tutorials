package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_lesson.*

class LessonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)
        var title= intent.extras?.getString("title")
        var body=intent.extras?.getString("body")
        val image=intent.extras?.getString("image")
        val id=intent.extras?.getInt("id")
        val postId=intent.extras?.getInt("postid")
        lesson_title.text=title+"  "+id.toString()+"    "+postId.toString()
        lesson_body.text=body
        Picasso.get().load(image).into(imageLesson);

    }
}