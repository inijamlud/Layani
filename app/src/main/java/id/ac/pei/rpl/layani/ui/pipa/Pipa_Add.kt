package id.ac.pei.rpl.layani

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Pipa_Add : AppCompatActivity() {
    lateinit var btnSubmit: Button
    lateinit var lokasi: EditText
    lateinit var detail_order: ChipGroup
    lateinit var durasi: RadioGroup
    lateinit var durasi_val: RadioButton
    lateinit var kontak: EditText
    lateinit var apiService: ServiceInterface

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_pipa)
        declaration()
        myfunction()
    }

    fun declaration(){
        btnSubmit = findViewById(R.id.btn_pesan)
        lokasi = findViewById(R.id.lokasi)
        detail_order = findViewById(R.id.chipGroup)
        durasi = findViewById(R.id.radioGr)
        kontak = findViewById(R.id.kontak)
        apiService = Repository.getDataAPI().create(ServiceInterface::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun myfunction(){
        btnSubmit.setOnClickListener {

            //ambil nilai durasi
            val selectedOption: Int = durasi!!.checkedRadioButtonId
            durasi_val = findViewById(selectedOption)
            val nilai_durasi = durasi_val.text.toString().replace(" Jam", "").toInt()

            //ambil service
            val ids = detail_order.getCheckedChipIds()
            val titles = mutableListOf<CharSequence>()
                ids.forEach { id ->
                    titles.add(detail_order.findViewById<Chip>(id).text)
                }
            val detail_chip_text = if (titles.isNotEmpty()){ titles.joinToString(", ") } else { titles }

            //val date now
            val date = LocalDateTime.now()
            val currentDate = date.format(DateTimeFormatter.ISO_DATE).toString()

            //total harga
            var total_service:Int?=0
            if(titles.count() == 1 ){
                total_service = 1
            } else if(titles.count() == 2 ){
                total_service = 2
            } else if(titles.count() == 3 ){
                total_service = 3
            }

            val array = OrderService()
            array.lokasi= lokasi.text.toString()
            array.detail_order = "Pipa: ${detail_chip_text}"
            array.tgl = currentDate
            array.durasi = nilai_durasi
            array.kontak = kontak.text.toString().toInt()
            if (total_service != null) {
                array.total_harga = nilai_durasi * (total_service.times(55000))
            }

            apiService.postOrder(array).enqueue(object : Callback<OrderService>{
                override fun onResponse(call: Call<OrderService>, response: Response<OrderService>) {
                    startActivity(Intent(this@Pipa_Add, MainActivity::class.java))
                    Toast.makeText(baseContext, "Pesanan dibuat!", Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(call: Call<OrderService>, t: Throwable) {
                    Toast.makeText(baseContext, "Pesanan gagal dibuat!", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }
}