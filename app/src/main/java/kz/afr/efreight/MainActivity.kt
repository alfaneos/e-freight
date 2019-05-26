package kz.afr.efreight

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kz.afr.efreight.view.receiver_accept.ReceiverAcceptFragment
import kz.afr.efreight.view.stock_arrivale.StockArrivalFragment


class MainActivity : BaseActivity() {

    lateinit var navView: BottomNavigationView
    lateinit var viewPager: ViewPager
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewPager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                viewPager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                viewPager.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        setupViewpager()

    }

    private fun setupViewpager() {
        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = PagerAdapter(supportFragmentManager)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) = Unit

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> navView.selectedItemId = R.id.navigation_home
                    1 -> navView.selectedItemId = R.id.navigation_dashboard
                    2 -> navView.selectedItemId = R.id.navigation_notifications
                }
            }

        })

    }

    inner class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment = when (position) {
            0 -> ReceiverAcceptFragment()
            else -> StockArrivalFragment()
        }

        override fun getCount(): Int = 3

    }
}
