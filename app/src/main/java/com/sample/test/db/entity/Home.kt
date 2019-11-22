package com.sample.test.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by SangiliPandian C on 22-11-2019.
 */

@Entity
data class Home(
    @PrimaryKey
    val id: Long,
    val name: String,
    val price: Double
)
