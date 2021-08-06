package id.ac.pei.rpl.layani

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class OrderService(
        var order_id: Int?=0,
        var lokasi: String?="",
        var detail_order: String? ="",
        var tgl: String? ="",
        var durasi: Int?=0,
        var kontak: Int?=0,
        var total_harga: Int?=0
)
