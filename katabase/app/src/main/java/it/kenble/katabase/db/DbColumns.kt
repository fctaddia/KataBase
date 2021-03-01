package it.kenble.katabase.db

import android.provider.BaseColumns

/**
 * @author Francesco Taddia
 * @see 'https://github.com/fctaddia'
 */
object DbColumns {

    /**
     * DTO Table for Sql
     */
    object DbItem : BaseColumns {
        const val TABLE_NAME = "rooms"
        const val ID = "id"
    }
    
}
