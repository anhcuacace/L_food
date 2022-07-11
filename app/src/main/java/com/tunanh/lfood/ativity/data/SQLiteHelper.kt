package com.tunanh.lfood.ativity.data


import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.media.tv.TvContentRating
import com.google.android.material.transition.SlideDistanceProvider

class SQLiteHelper(context: Context, name:String, factory: SQLiteDatabase.CursorFactory?, version: Int):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSOIN) {
    companion object{
        private const val DATABASE_VERSOIN=1
        private const val DATABASE_NAME="app.db"
        private const val TABLE_NAME="food1"
        private const val ID= "id"
        private const val NAME="name"

    }

    public fun QueryData(sql: String){
        var db =writableDatabase
        db.execSQL(sql)

    }
    public fun INSERT_food(sale:ByteArray,imgfood:ByteArray,distance:String,name: String,rating: String){
        var db =writableDatabase

        val sql="INSERT INTO food1(,?,?,?,?,?)"
        val statement= db.compileStatement(sql)
        statement.clearBindings()
        statement.bindBlob(1,sale)
        statement.bindBlob(2,imgfood)
        statement.bindString(3,distance)
        statement.bindString(4,name)
        statement.bindString(5,rating)
        statement.executeInsert()
    }
    public fun GetData(sql: String):Cursor{
        var db =writableDatabase
        return db.rawQuery(sql,null)
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable="CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY AUTOINCREMENT,saleoff BLOB,imgfood BLOB,distance VARCHAR,name VARCHAR,ratting VARCHAR)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}