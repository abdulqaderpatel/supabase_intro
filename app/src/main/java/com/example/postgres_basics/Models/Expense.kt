package com.example.postgres_basics.Models

import kotlinx.datetime.DateTimeUnit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.sql.Timestamp
import java.util.Date

@Serializable
data class Expense(
    val id:Int=0,
    @SerialName("created_at")
    val created_at:String="",
    val title:String="",
    val amount:Int=0,
    @SerialName("date")
    val date:String=""


)