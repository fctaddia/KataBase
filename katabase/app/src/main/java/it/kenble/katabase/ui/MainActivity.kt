package it.kenble.katabase.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import it.kenble.katabase.db.DbHandler
import it.kenble.katabase.db.DbColumns
import it.kenble.katabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // region Variables

    private lateinit var dbHandler: DbHandler
    private lateinit var arrayItem: Array<String>
    private lateinit var mainBind: ActivityMainBinding

    // endregion

    // region Lifecycle
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init() ; listeners()
    }
    
    // endregion

    // region Init, Listeners, Toast

    private fun init() {
        mainBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBind.root)
        dbHandler = DbHandler(this, null)
        arrayItem = arrayOf("")
        showDb()
    }

    private fun listeners() {
        mainBind.btnAddToDb.setOnClickListener {
            val item = mainBind.etName.text.toString()
            if (mainBind.etName.length() != 0) {
                dbHandler.addItem(item)
                showDb()
                toast(mainBind.etName.text.toString() + " Added to database")
            } else {
                toast("Insert something in the edit text first")
            }
        }

        mainBind.btnRmToDb.setOnClickListener {
            refreshDb()
            if (mainBind.etName.length() != 0) {
                dbHandler.removeItem(arrayItem[arrayItem.size-1])
                showDb()
                toast(arrayItem[arrayItem.size - 1] + " Removed to database")
            } else {
                toast("Insert something in the edit text first")
            }
        }
    }
    
    private fun toast(message: String) {
        val toastItem: Toast = Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG)
        toastItem.show()
    }

    // endregion

    // region Query

    private fun refreshDb() {
        val cursor = dbHandler.getAllItems() ; var i = 0
        while(cursor!!.moveToNext()){
            arrayItem[i] = cursor.getString(cursor.getColumnIndex(DbColumns.DbItem.ID))
            cursor.moveToNext()
            i++
        } ; cursor.close()
    }

    private fun showDb() {
        mainBind.tvDisplayName.text = ""
        val cursor = dbHandler.getAllItems()
        while (cursor!!.moveToNext()) {
            mainBind.tvDisplayName.append((cursor.getString(cursor.getColumnIndex(DbColumns.DbItem.ID))))
            mainBind.tvDisplayName.append("\n")
        } ; cursor.close()
    }

    // endregion

}
