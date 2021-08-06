package id.ac.pei.rpl.layani.ui.order

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.pei.rpl.layani.OrderService
import id.ac.pei.rpl.layani.R
import id.ac.pei.rpl.layani.Repository
import id.ac.pei.rpl.layani.ServiceInterface

class OrderAdapter(private val listku: ArrayList<OrderService>): RecyclerView.Adapter<OrderAdapter.KontakViewHolder>(){
    inner class KontakViewHolder(viewku: View): RecyclerView.ViewHolder(viewku) {
        var tv_service: TextView = viewku.findViewById(R.id.tv_service)
        var tv_detail: TextView = viewku.findViewById(R.id.tv_detail)
        var tv_lokasi: TextView = viewku.findViewById(R.id.tv_lokasi)
        var tv_kontak: TextView = viewku.findViewById(R.id.tv_kontak)
        var tv_durasi: TextView = viewku.findViewById(R.id.tv_durasi)
        var tv_totalHarga: TextView = viewku.findViewById(R.id.tv_totalHarga)
        var apiIterface: ServiceInterface? = Repository.getDataAPI().create(ServiceInterface::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KontakViewHolder {
        val viewView: View = LayoutInflater.from(parent.context).inflate(R.layout.data_item, parent, false)
        return KontakViewHolder(viewView)
    }

    override fun onBindViewHolder(holder: KontakViewHolder, position: Int) {
        val dataku = listku[position]
        val service = dataku.detail_order.toString()

        var service_text: String? = ""
        var detail_text: String? = ""

        if (service.contains("AC:")) {
            service_text = "Service AC"
            detail_text = service.replace("AC: ", "")
        } else if (service.contains("Pipa:")) {
            service_text = "Perbaikan Pipa"
            detail_text = service.replace("Pipa: ", "")
        } else if (service.contains("Disinfektan:")) {
            service_text = "Disinfektan"
            detail_text = service.replace("Disinfektan: ", "")
        } else if (service.contains("Cleaning:")) {
            service_text = "Cleaning"
            detail_text = service.replace("Cleaning: ", "")
        } else {
            service_text = service
            detail_text = service
        }

        holder.tv_service.text = service_text
        holder.tv_detail.text = detail_text
        holder.tv_lokasi.text = dataku.lokasi.toString()
        holder.tv_kontak.text = "0"+ dataku.kontak.toString()
        holder.tv_durasi.text = dataku.tgl.toString() + " | " +  dataku.durasi.toString() + " Jam"
        holder.tv_totalHarga.text = "Rp. "+ dataku.total_harga.toString()


    }

    override fun getItemCount(): Int {
        return listku.size
    }

}