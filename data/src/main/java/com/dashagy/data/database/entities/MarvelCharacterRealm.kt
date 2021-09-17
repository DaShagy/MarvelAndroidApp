package com.dashagy.data.database.entities

import com.dashagy.data.DEFAULT_ID
import com.dashagy.data.EMPTY_STRING
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class MarvelCharacterRealm (
    @PrimaryKey
    var id: Int = DEFAULT_ID,
    var name: String = EMPTY_STRING,
    var description: String = EMPTY_STRING,
    var image: String = EMPTY_STRING
) : RealmObject()