package uz.botir.newsapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.botir.newsapp.R
import uz.botir.newsapp.core.adapter.MainAdapter
import uz.botir.newsapp.databinding.ScreenMainBinding


class MainScreen : Fragment(R.layout.screen_main) {

    private val binding by viewBinding(ScreenMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewPager()
    }

    private fun setViewPager() {
        val adapter = MainAdapter(lifecycle, childFragmentManager)
        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = false

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeMENU -> {
                    binding.viewPager.setCurrentItem(0, true)
                }

                R.id.saveMENU -> {
                    binding.viewPager.setCurrentItem(1, true)
                }

                R.id.settingsMENU -> {
                    binding.viewPager.setCurrentItem(2, true)
                }
            }
            true
        }
    }


}
