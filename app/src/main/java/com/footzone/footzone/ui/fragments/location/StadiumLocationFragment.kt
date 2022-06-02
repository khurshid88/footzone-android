package com.footzone.footzone.ui.fragments.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.footzone.footzone.R
import com.footzone.footzone.databinding.FragmentStadiumLocationBinding
import com.footzone.footzone.ui.fragments.BaseFragment
import com.footzone.footzone.utils.KeyValues


class StadiumLocationFragment : BaseFragment(R.layout.fragment_stadium_location) {

    lateinit var binding: FragmentStadiumLocationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStadiumLocationBinding.bind(view)
        initViews()
    }


    private fun initViews() {
        binding.apply {
            icClose.setOnClickListener { requireActivity().onBackPressed() }
            tvCancel.setOnClickListener { requireActivity().onBackPressed() }
            tvSelection.setOnClickListener {
                val result = "Toshkent, Bodomzor ko'chasi"
                setFragmentResult(KeyValues.TYPE_LOCATION, bundleOf("bundleKey" to result))
                requireActivity().onBackPressed()
            }
        }
    }
}