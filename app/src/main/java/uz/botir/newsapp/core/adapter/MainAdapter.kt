package uz.botir.newsapp.core.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.botir.newsapp.ui.main.home.HomePage
import uz.botir.newsapp.ui.main.save.SavePage
import uz.botir.newsapp.ui.main.setting.SettingPage

class MainAdapter(lf: Lifecycle, fm: FragmentManager) : FragmentStateAdapter(fm, lf) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomePage()
            1 -> SavePage()
            else -> SettingPage()
        }
    }
}