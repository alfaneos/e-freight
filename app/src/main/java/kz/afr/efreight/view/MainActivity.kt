package kz.afr.efreight.view

import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kz.afr.efreight.BaseActivity
import kz.afr.efreight.R
import kz.afr.efreight.network.model.Note


class MainActivity : BaseActivity() {

    private lateinit var button: Button
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                button.setText(R.string.activity_title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                button.setText(R.string.action_settings)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                button.setText(R.string.app_name)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        button = findViewById(R.id.button)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        button.setOnClickListener {

            apiService.fetchAllNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Note>>() {
                    override fun onSuccess(notes: List<Note>) {
                        // Received all notes
                    }

                    override fun onError(e: Throwable) {
                        // Network error
                    }
                })
        }
    }
}
