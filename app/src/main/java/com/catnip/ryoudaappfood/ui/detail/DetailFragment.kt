package com.catnip.ryuodaappfood.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.catnip.ryoudaappfood.R
import com.catnip.ryoudaappfood.databinding.FragmentDetailBinding
import com.catnip.ryuodaappfood.data.local.database.AppDatabase
import com.catnip.ryuodaappfood.data.local.database.entity.ItemEntity
import com.catnip.ryuodaappfood.data.repository.FoodRepository
import com.catnip.ryuodaappfood.utils.viewModelFactory

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModelFactory {
        DetailViewModel(FoodRepository(AppDatabase.getInstance(requireContext()).itemdao()))
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val ambilDataMenu = bundle!!.getString("menu")
        val ambilDataharga = bundle!!.getInt("harga")
        val ambilDataimeage = bundle!!.getInt("imeage")
        val ambilDataketerangan = bundle!!.getString("keterangan")
        val ambilDatakelokasi = bundle!!.getString("lokasi")
        binding.tvTextMenu.text = ambilDataMenu
        binding.tvHargaMenu.text = ambilDataharga.toString()
        Glide.with(requireContext()).load(ambilDataimeage).into(binding.ivPictMenu)
        binding.tvDescLocation.text = ambilDatakelokasi
        binding.tvDescMenu.text = ambilDataketerangan
        binding.ivBack.setOnClickListener {
            it.findNavController().navigate(R.id.action_detailFragment_to_cart_fragment)
        }
        binding.llDesc.setOnClickListener {
            val data = binding.tvDescLocation.text.toString()
            val uri = Uri.parse("https://www.google.com/maps/search/$data")
            val mapIntent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(mapIntent)
        }
        viewModel.number.observe(viewLifecycleOwner) { jumlah ->
            binding.tvSum.text = jumlah.toString()
            val number = jumlah * ambilDataharga
            binding.tvCart.text = "Tambah keranjang Rp. $number"
            binding.tvCart.setOnClickListener {
                if (jumlah != 0) {
                    viewModel.addItem(
                        ItemEntity(
                            null,
                            ambilDataimeage,
                            ambilDataMenu!!,
                            ambilDataharga,
                            ambilDataketerangan!!,
                            ambilDatakelokasi!!,
                            jumlah,
                            number
                        )
                    )
                    it.findNavController().popBackStack()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Tidak bisa menambahkan karna jumlah 0!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        binding.ivMinus.setOnClickListener {
            viewModel.increment()
        }
        binding.ivPlus.setOnClickListener {
            viewModel.decrement()
        }

    }
}