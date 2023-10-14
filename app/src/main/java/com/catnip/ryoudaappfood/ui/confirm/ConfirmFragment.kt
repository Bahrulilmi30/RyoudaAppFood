package com.catnip.ryoudaappfood.ui.confirm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.ryoudaappfood.R
import com.catnip.ryoudaappfood.adapter.ConfirmAdapter
import com.catnip.ryoudaappfood.databinding.FragmentConfirmBinding
import com.catnip.ryuodaappfood.data.local.database.AppDatabase
import com.catnip.ryuodaappfood.data.repository.FoodRepository
import com.catnip.ryuodaappfood.utils.viewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ConfirmFragment : Fragment() {
    private var _binding : FragmentConfirmBinding? = null
    private val binding get()= _binding!!
    private val viewModel: ConfirmViewModel by viewModelFactory {
        ConfirmViewModel(FoodRepository(AppDatabase.getInstance(requireContext()).itemdao()))
    }
    private lateinit var confirmAdapter: ConfirmAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllItem()
        viewModel.item.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.rvPemesanan.isVisible = false
                binding.tvEmpty.isVisible = true
            } else {
                binding.rvPemesanan.isVisible = true
                binding.tvEmpty.isVisible = false
                confirmAdapter = ConfirmAdapter(
                    { item ->
                        val bundle = Bundle()
                        bundle.putString("menu", item.name)
                        bundle.putInt("harga", item.price)
                        bundle.putInt("image", item.image)
                        bundle.putString("keterangan", item.description)
                        bundle.putString("lokasi", item.location)
                        Navigation.findNavController(view).navigate(
                            R.id.action_cart_fragment_to_detailFragment, bundle
                        )///memindahkan fragment ke fragment lainnya
                    },
                    { item ->
                        viewModel.deleteItem(item)
                        viewModel.getAllItem()
                    }
                )
                confirmAdapter.setItems(it)
                binding.rvPemesanan.adapter = confirmAdapter
                binding.rvPemesanan.setHasFixedSize(true)
                binding.rvPemesanan.layoutManager = LinearLayoutManager(requireContext())
            }
            binding.btnPesan.setOnClickListener { view ->
                if (it.isEmpty()) {
                    Toast.makeText(
                        requireContext(), "Pemesanan tidak boleh kosong!", Toast.LENGTH_SHORT
                    ).show()
                } else {
                    MaterialAlertDialogBuilder(
                        requireContext(),
                        com.google.android.material.R.style.MaterialAlertDialog_Material3_Body_Text_CenterStacked
                    ).setMessage("Apakah anda yakin ingin membeli pesanan ini?")
                        .setNegativeButton("No") { act, _ -> act.dismiss() }
                        .setPositiveButton("Yes") { _, _ ->
                            viewModel.deleteALl()
                            MaterialAlertDialogBuilder(requireContext()).setMessage("Terima kasih anda telah membeli pemesanan ini!")
                                .setPositiveButton("OK") { act, _ ->
                                    Navigation.findNavController(view)
                                        .navigate(R.id.action_confirmFragment_to_home_fragment)
                                }.show()
                        }
                        .show()
                }
            }
        }
        binding.ivBack.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }
}