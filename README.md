<img src="docs/katabase_logo.png" alt="Showcase" height="100px">

## KataBase
Creating databases has never been easier

[![Kotlin](https://img.shields.io/badge/Kotlin-1.4.10-f58a1f.svg?style=flat-square)](http://kotlinlang.org)
[![AndroidX](https://img.shields.io/badge/AndroidX-1.3.2-4971a9.svg?style=flat-square)](https://developer.android.com/jetpack/androidx/)
[![GitHub (pre-)release](https://img.shields.io/github/v/release/fctaddia/katabase.svg?color=f77200&label=Release&style=flat-square)](./../../releases)
[![License](https://img.shields.io/github/license/fctaddia/KataBase?color=29a621&label=License)](https://opensource.org/licenses/MIT)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/c642e1a24d2a44108d53233ede4bee94)](https://www.codacy.com/gh/fctaddia/KataBase/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=fctaddia/KataBase&amp;utm_campaign=Badge_Grade)

KataBase aims for simplicity. He wants to give in hand a tool to create and manipulate databases in a simple and clear way.

### How does it work?
The DbHandler class contains all the **functions** that allow you to **add**, **remove** or **show** the contents of the database.

#### Create Table
> **Tip:** The database table must be created inside the onCreate function
```Kotlin
val CREATE_EXAMPLE_TABLE = "CREATE TABLE " + DbColumns.DbItem.TABLE_NAME + "(" + DbColumns.DbItem.ID + " TEXT PRIMARY KEY)"
db.execSQL(CREATE_EXAMPLE_TABLE)
```
#### Add an item in the database:
> **Tip:** In the id parameter enter the identification key of the article contained in DbKeys
```Kotlin
fun addItem(id: String) {
    val value = ContentValues()
    value.put(DbColumns.DbItem.ID , id)
    val db = this.writableDatabase
    db.insert(DbColumns.DbItem.TABLE_NAME, null, value)
    db.close()
}
```
#### Remove an item in the database:
> **Tip:** In the id parameter enter the identification key of the article contained in DbKeys
```Kotlin
fun removeItem(id: String) {
    if(isItem(id)){
        val db = this.writableDatabase
        db.delete(DbColumns.DbItem.TABLE_NAME, DbColumns.DbItem.ID+ "=?", arrayOf(id))
        db.close()
    }
}
```
#### Check if an item is inside the database
> **Tip:** This function returns true when the article is present in the database
> **Tip:** In the id parameter enter the identification key of the article contained in DbKeys
```Kotlin
fun isItem(id: String): Boolean {
    val db = this.readableDatabase
    val cursor = db.rawQuery("SELECT * FROM ${DbColumns.DbItem.TABLE_NAME}  WHERE ${DbColumns.DbItem.ID} = ? ", arrayOf(id) )
    val result = cursor.move(1)
    cursor.close()
    db.close()
    return result
}
```
#### Show all database
> **Tip:** In the id parameter enter the identification key of the article contained in DbKeys
```Kotlin
fun getAllItems() : Cursor? {
    val db = this.readableDatabase
    return db.rawQuery("SELECT * FROM ${DbColumns.DbItem.TABLE_NAME}", null)
}
```

### Conclusion
To conclude, KataBase might be the right choice to start understanding how databases work in Android. The implementation is very simple and allows it to be customized to your liking.
