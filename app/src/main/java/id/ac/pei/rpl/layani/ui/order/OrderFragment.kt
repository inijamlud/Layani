package id.ac.pei.rpl.layani.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.pei.rpl.layani.OrderService
import id.ac.pei.rpl.layani.R
import id.ac.pei.rpl.layani.Repository
import id.ac.pei.rpl.layani.ServiceInterface
import id.ac.pei.rpl.layani.ui.order.OrderAdapter
import retrofit2.Callback
import retrofit2.Response

class OrderFragment : Fragment() {
    lateinit var rvdata: RecyclerView
    lateinit var apiService: ServiceInterface
    private var ambilData: ArrayList<OrderService> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_order, container, false)
        rvdata = view.findViewById(R.id.rv_data)
        apiService = Repository.getDataAPI().create(ServiceInterface::class.java)
        apiService.getData().enqueue(object : Callback<List<OrderService>> {
            override fun onResponse(
                call: retrofit2.Call<List<OrderService>>,
                response: Response<List<OrderService>>
            ) {
                if (response.isSuccessful){
                    val res = response.body()
                    ambilData.addAll(res!!)
                    rvdata.layoutManager = LinearLayoutManager(view.context)
                    val oa = OrderAdapter(ambilData)
                    rvdata.adapter = oa
                }
            }
            override fun onFailure(call: retrofit2.Call<List<OrderService>>, t: Throwable) {
            }
        })
        return view

    }
}