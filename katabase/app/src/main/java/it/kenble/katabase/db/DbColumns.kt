package it.kenble.katabase.db

import android.provider.BaseColumns

object DbColumns {
    object DbItem : BaseColumns {
        const val TABLE_NAME = "rooms"
        const val ID = "id"
    }
}
