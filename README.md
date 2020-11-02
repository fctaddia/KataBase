<img src="docs/katabase_logo.png" alt="Showcase" height="100px">

## KataBase
Creating databases has never been easier

[![Kotlin](https://img.shields.io/badge/Kotlin-1.4.10-f58a1f.svg?style=flat-square)](http://kotlinlang.org)
[![AndroidX](https://img.shields.io/badge/AndroidX-1.3.2-4971a9.svg?style=flat-square)](https://developer.android.com/jetpack/androidx/)
[![GitHub (pre-)release](https://img.shields.io/github/v/release/fctaddia/katabase.svg?color=f77200&include_prereleases&label=Release&style=flat-square)](./../../releases)
[![License](https://img.shields.io/github/license/fctaddia/KataBase?color=29a621&label=License)](https://opensource.org/licenses/MIT)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/c642e1a24d2a44108d53233ede4bee94)](https://www.codacy.com/gh/fctaddia/KataBase/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=fctaddia/KataBase&amp;utm_campaign=Badge_Grade)

KataBase aims for simplicity. He wants to give in hand a tool to create and manipulate databases in a simple and clear way.

### How does it work?
How KataBase works is **very basic**, and that's what it aims for. The DbHandler class contains all the **functions** that allow you to **add**, **remove** or **show** the contents of the database.

> **Tip:** The database table must be created inside the onCreate function
#### Create Table
```Kotlin
val CREATE_EXAMPLE_TABLE = "CREATE TABLE " + DbColumns.DbItem.TABLE_NAME + "(" + DbColumns.DbItem.ID + " TEXT PRIMARY KEY)"
db.execSQL(CREATE_EXAMPLE_TABLE)
```
> **Tip:** In the id parameter enter the identification key of the article contained in DbKeys
#### Add an item in the database:
```Kotlin
fun addItem(id : String){
    val value = ContentValues()
    value.put(DbColumns.DbItem.ID , id)
    val db = this.writableDatabase
    db.insert(DbColumns.DbItem.TABLE_NAME, null, value)
    db.close()
}
```
> **Tip:** In the id parameter enter the identification key of the article contained in DbKeys
#### Remove an item in the database:
```Kotlin
fun removeItem(id : String){
    if(is_Item_inDB(id)){
        val db = this.writableDatabase
        db.delete(DbColumns.DbItem.TABLE_NAME, DbColumns.DbItem.ID+ "=?", arrayOf(id))
    }
}
```
> **Tip:** This function returns true when the article is present in the database
> **Tip:** In the id parameter enter the identification key of the article contained in DbKeys
#### Check if an item is inside the database
```Kotlin
fun is_Item_inDB(id : String): Boolean {
    val db = this.readableDatabase
    val cursor = db.rawQuery("SELECT * FROM ${DbColumns.DbItem.TABLE_NAME}  WHERE ${DbColumns.DbItem.ID} = ? ", arrayOf(id) )
    return cursor.move(1)
}
```
> **Tip:** In the id parameter enter the identification key of the article contained in DbKeys
#### Show all database
```Kotlin
fun is_Item_inDB(id : String): Boolean {
    val db = this.readableDatabase
    val cursor = db.rawQuery("SELECT * FROM ${DbColumns.DbItem.TABLE_NAME}  WHERE ${DbColumns.DbItem.ID} = ? ", arrayOf(id) )
    return cursor.move(1)
}
```
> **Tip:** To use the getAllItems function, use the code commented below
```Kotlin
var cursor = getAllItems()
var i = 0
var arrayItems : Array<String> = array("")
while(i<cursor.count){
    arrayItems[i] = cursor.toString(cursor.getColumnIndex(DbColumns.DbRoom.ID))
    cursor.moveToNext()
    i++
}
```
### Conclusion
To conclude, KataBase may be the right choice to start understanding how databases work in android. The implementation is very simple and allows it to be customized to your liking.
