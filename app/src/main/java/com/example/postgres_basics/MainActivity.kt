package com.example.postgres_basics

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.postgres_basics.Models.Expense
import com.example.postgres_basics.Supabase.SupabaseClient
import com.example.postgres_basics.ui.theme.Postgres_basicsTheme
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

class MainActivity : ComponentActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Postgres_basicsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    var a by remember {
                        mutableStateOf("hello")
                    }

                    fun abc() {


                    }

                    val client = createSupabaseClient(
                        supabaseUrl = "https://wvdkuwcawujulrlknzcn.supabase.co",
                        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Ind2ZGt1d2Nhd3VqdWxybGtuemNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDA4NDE5NjAsImV4cCI6MjAxNjQxNzk2MH0.7SnOFDKwAercO-o4owtXSfIeJiRhorfhp9rHLjOyUN4"
                    ) {
                        install(GoTrue)
                        install(Postgrest)
                    }
                    Button(onClick = {
                        CoroutineScope(Dispatchers.Default).launch {
                            try {


                                var c = Expense(
                                    title = "dsf", amount = 23, date = "2023-11-26T00:00:00Z"
                                )
                                var d = LocalDate.parse(c.date, DateTimeFormatter.ISO_DATE_TIME)


                                var year = d.year
                                var month = d.month
                                var day = d.dayOfMonth
                                val query = """
    SELECT *
    FROM expenses
    WHERE date_trunc('date', expense_date_column::timestamptz) = '$year-$month-$day'::date
""".trimIndent()

                             var result=client.postgrest["expenses"].select {
                                 gt("created_at", "2023-11-26T00:00:00Z")
                                 lt("created_at", "2023-11-27T00:00:00Z");

                                }

                                var b=result.body
                                Log.d("TAG", b.toString())
                            } catch (e: Exception) {
                                Log.d("TAG", e.toString())
                            }


                        }
                    }) {
                        Text(text = a)
                    }
                }
            }
        }
    }
}

