package it.kenble.katabase.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHandler(context: Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_EXAMPLE_TABLE = "CREATE TABLE " + DbColumns.DbItem.TABLE_NAME + "(" + DbColumns.DbItem.ID + " TEXT PRIMARY KEY)"
        db.execSQL(CREATE_EXAMPLE_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${DbColumns.DbItem.TABLE_NAME}")
        onCreate(db)
    }

    /*
     * To use the getAllItems function, use the code commented below
     *
        var cursor = getAllItems()
        var i = 0
        var arrayItems : Array<String> = array("")
        while(i<cursor.count){
            arrayItems[i] = cursor.toString(cursor.getColumnIndex(DbColumns.DbRoom.ID))
            cursor.moveToNext()
            i++
        }
    */
    fun getAllItems(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM ${DbColumns.DbItem.TABLE_NAME}", null)
    }


    /*
    * In the id parameter enter the identification key of the article contained in DbKeys
    */
    fun addItem(id:String){
        val value = ContentValues()
        value.put(DbColumns.DbItem.ID , id)
        val db = this.writableDatabase
        db.insert(DbColumns.DbItem.TABLE_NAME, null, value)
        db.close()
    }

    /*
    * This function returns true when the article is present in the database
    * In the id parameter enter the identification key of the article contained in DbKeys
    */
    fun is_Item_inDB(id : String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${DbColumns.DbItem.TABLE_NAME}  WHERE ${DbColumns.DbItem.ID} = ? ", arrayOf(id) )
        return cursor.move(1)
    }


    /*
    * In the id parameter enter the identification key of the article contained in DbKeys
    */
    fun removeItem(id : String){
        if(is_Item_inDB(id)){
            val db = this.writableDatabase
            db.delete(DbColumns.DbItem.TABLE_NAME, DbColumns.DbItem.ID+ "=?", arrayOf(id))
        }
    }

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "Example.db"
    }

}