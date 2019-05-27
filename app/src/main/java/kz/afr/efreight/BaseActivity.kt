package kz.afr.efreight

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.paperdb.Paper
import kz.afr.efreight.network.client.ApiClient
import kz.afr.efreight.network.service.ApiService



abstract class BaseActivity : AppCompatActivity() {

    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        Paper.book().write(ApiClient.USERNAME_PREFS, "test_user")
        Paper.book().write(ApiClient.PASSWORD_PREFS, "123456")
        apiService = ApiClient.getClient(this).create(ApiService::class.java)
        super.onCreate(savedInstanceState)
    }

    fun showMessage(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    fun startFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frame, fragment, fragment.javaClass.canonicalName)
        transaction.addToBackStack(fragment.javaClass.canonicalName)
        transaction.commit()

    }
}