package kz.afr.efreight

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kz.afr.efreight.network.client.ApiClient
import kz.afr.efreight.network.service.ApiService

abstract class BaseActivity : AppCompatActivity(){

    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        apiService = ApiClient.getClient(this).create(ApiService::class.java)
    }
}