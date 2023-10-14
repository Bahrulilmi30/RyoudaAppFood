package com.catnip.ryuodaappfood.utils


import com.catnip.ryoudaappfood.R
import com.catnip.ryuodaappfood.model.Item

object Data {
    private val name = arrayOf(
        "Ayam bakar",
        "Ayam geprek",
        "Ayam goreng",
        "Sate Usus",
        "Minuman",
        "Mie",
        "Nasi",
        "Makanan Ringan"
    )

    private val image = arrayOf(
        R.drawable.ic_ayam_bakar,
        R.drawable.ic_ayam_geprek,
        R.drawable.ic_ayam_goreng,
        R.drawable.ic_sate_usus,
        R.drawable.ic_drink,
        R.drawable.ic_noodle,
        R.drawable.ic_rice,
        R.drawable.ic_snack
    )

    private val price = arrayOf(
        50000,
        40000,
        40000,
        5000,
        10000,
        10000,
        20000,
        5000
    )

    private val desc = arrayOf(
        "Ayam bakar adalah makanan enak dan lezat",
        "Ayam goreng adalah makanan enak",
        "Ayam geprek adalah makanan pedas",
        "Sate usus terbuat dari usus ayam",
        "Minuman ini rasanya enak dan untuk menghilangkan dahaga",
        "Mie yang terbuat dari bahan pilihan merupakan menu yang diminati banyak pengunjung",
        "Nasi yang terbuat dari beras pilihan bisa menjadi salah satu pilihan",
        "Makanan ringan adalahj makanan yang disajikan sebagai makanan penutup"
    )

    private val location = arrayOf(
        "Ayam bakar",
        "Ayam geprek",
        "Ayam goreng",
        "Sate Usus",
        "Minuman",
        "Mie",
        "Nasi",
        "Makanan Ringan"
    )

    val listData: ArrayList<Item>
        get(){
            val list = arrayListOf<Item>()
            for (position in name.indices){
                list.add(
                    Item(
                        name[position],
                        image[position],
                        price[position],
                        desc[position],
                        location[position]
                    )
                )
            }
            return list
        }
}