package com.catnip.ryoudaappfood.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.ryoudaappfood.R
import com.catnip.ryoudaappfood.databinding.FragmentCartBinding
import com.catnip.ryuodaappfood.adapter.CartAdapter
import com.catnip.ryuodaappfood.data.local.database.AppDatabase
import com.catnip.ryuodaappfood.data.repository.FoodRepository
import com.catnip.ryuodaappfood.ui.cart.CartViewModel
import com.catnip.ryuodaappfood.utils.viewModelFactory


class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: CartViewModel by viewModelFactory {
        CartViewModel(FoodRepository(AppDatabase.getInstance(requireContext()).itemdao()))
    }
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getALlItem()
        viewModel.getTotalItem()
        viewModel.food.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.rvCart.isVisible = false
                binding.tvEmpty.isVisible = true
            } else {
                binding.rvCart.isVisible = true
                binding.tvEmpty.isVisible = false
                cartAdapter = CartAdapter(
                    { food ->
                        val bundle = Bundle()
                        bundle.putString("menu", food.name)
                        bundle.putInt("harga", food.price)
                        bundle.putInt("imeage", food.image)
                        bundle.putString("keterangan", food.description)
                        bundle.putString("lokasi", food.location)
                        Navigation.findNavController(view).navigate(
                            R.id.action_cart_fragment_to_detailFragment, bundle
                        )///memindahkan fragment ke fragment lainnya
                    },
                    { food ->
                        viewModel.deleteItem(food)
                        viewModel.getALlItem()
                        viewModel.getTotalItem()
                    },
                    {
                        if (it.stock > 1) {
                            viewModel.updateStock(it.stock - 1, it.id!!, (it.stock - 1) * it.price)
                            viewModel.getALlItem()
                            viewModel.getTotalItem()
                        } else {
                            Toast.makeText(
                                requireContext(), "Stock tidak boleh 0!", Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    {
                        viewModel.updateStock(it.stock + 1, it.id!!, (it.stock + 1) * it.price)
                        viewModel.getALlItem()
                        viewModel.getTotalItem()
                    },
                )
                cartAdapter.setItems(it)
                binding.rvCart.adapter = cartAdapter
                binding.rvCart.setHasFixedSize(true)
                binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
            }
            binding.btnPesan.setOnClickListener { view ->
                if (it.isEmpty()) {
                    Toast.makeText(
                        requireContext(), "Pemesanan tidak boleh kosong!", Toast.LENGTH_SHORT
                    ).show()
                } else {
                    view.findNavController().navigate(R.id.action_cart_fragment_to_confirmFragment)
                }
            }
        }
        viewModel.harga.observe(viewLifecycleOwner) {
            if (it != 0) {
                binding.tvTotalHarga.text = it.toString()
            } else {
                binding.tvTotalHarga.text = "0"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}