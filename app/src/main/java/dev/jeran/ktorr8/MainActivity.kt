package dev.jeran.ktorr8

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.Main).launch { useKtor() }
    }

    private suspend fun useKtor() {
        HttpClient().use { client ->
            client.get("https://pokeapi.co/api/v2/pokemon/1/") {
                contentType(ContentType.Application.Json)
            }
        }.bodyAsText().let { body ->
            println(body)
        }
    }
}
