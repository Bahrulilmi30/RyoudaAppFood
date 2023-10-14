package com.catnip.ryuodaappfood.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.ryoudaappfood.databinding.FragmentHomeBinding
import com.catnip.ryuodaappfood.adapter.GridAdapter
import com.catnip.ryuodaappfood.data.local.datastore.SettingDataStore
import com.catnip.ryuodaappfood.data.repository.SettingRepository
import com.catnip.ryuodaappfood.utils.Data
import com.catnip.ryuodaappfood.utils.viewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModelFactory {
        HomeViewModel(SettingRepository(SettingDataStore(requireContext())))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSetting.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.inclMainMenu.switchListGrid.isChecked = true
                listLinear()
            } else {
                binding.inclMainMenu.switchListGrid.isChecked = false
                listGrid()
            }
        }
        binding.inclMainMenu.switchListGrid.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                listLinear()
                viewModel.setSetting(true)
            } else {
                listGrid()
                viewModel.setSetting(false)
            }
        }
    }

    private fun listGrid() {
        binding.inclMainMenu.rvMenu.adapter = GridAdapter(Data.listData)
        binding.inclMainMenu.rvMenu.layoutManager = GridLayoutManager(context, 2)
    }

    private fun listLinear(){
        binding.inclMainMenu.rvMenu.adapter= com.catnip.ryuodaappfood.adapter.ListAdapter(Data.listData)
        binding.inclMainMenu.rvMenu.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

