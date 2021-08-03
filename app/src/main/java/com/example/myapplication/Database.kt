package com.example.myapplication

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Database:RealmObject() {
    @PrimaryKey
    var id:Int=0
    var name:String?=null
    var email:String?=null
    var body:String?=null
}