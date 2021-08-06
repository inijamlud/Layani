package id.ac.pei.rpl.layani
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface ServiceInterface {

    @GET("Order")
    fun getData(): Call<List<OrderService>>

    @POST("Order")
    fun postOrder(@Body orderdata: OrderService): Call<OrderService>

    @FormUrlEncoded
    @HTTP(method="PUT", path="Order", hasBody = true)
    fun updateOrder(
            @Field("order_id") order_id: Int,
            @Field("lokasi") lokasi: String,
            @Field("detail_order") detail_order: String,
            @Field("durasi") durasi: Int,
            @Field("kontak") kontak: Int,
            @Field("total_harga") total_harga: Int,
       ): Call<OrderService>

}