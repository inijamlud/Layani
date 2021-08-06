package id.ac.pei.rpl.layani.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import id.ac.pei.rpl.layani.*

class DashboardFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val menu1 : LinearLayout = view.findViewById(R.id.menu_ac)
        val menu2 : LinearLayout = view.findViewById(R.id.menu_pipa)
        val menu3 : LinearLayout = view.findViewById(R.id.menu_dis)
        val menu4 : LinearLayout = view.findViewById(R.id.menu_cleaning)
        menu1.setOnClickListener(){
            activity?.let{
                val intent = Intent (it, AC_Add::class.java)
                it.startActivity(intent)
            }
        }

        menu2.setOnClickListener(){
            activity?.let{
                val intent = Intent (it, Pipa_Add::class.java)
                it.startActivity(intent)
            }
        }

        menu3.setOnClickListener(){
            activity?.let{
                val intent = Intent (it, Disinfektan_Add::class.java)
                it.startActivity(intent)
            }
        }

        menu4.setOnClickListener(){
            activity?.let{
                val intent = Intent (it, Cleaning_Add::class.java)
                it.startActivity(intent)
            }
        }
        return view
    }


}