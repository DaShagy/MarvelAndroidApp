package com.dashagy.data.database.entities

import com.dashagy.data.DEFAULT_ID
import com.dashagy.data.EMPTY_STRING
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class MarvelCharacterRealm (
    @PrimaryKey
    val id: Int = DEFAULT_ID,
    val name: String = EMPTY_STRING,
    val description: String = EMPTY_STRING,
    val image: String = EMPTY_STRING
) : RealmObject()