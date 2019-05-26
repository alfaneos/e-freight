package kz.afr.efreight.view.stock_arrivale

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kz.afr.efreight.BaseActivity
import kz.afr.efreight.R
import kz.afr.efreight.network.model.Procedure
import kz.afr.efreight.network.service.ApiService
import kz.osultasign.view.stock_arrivale.StockArrivalViewModel


class StockArrivalFragment : Fragment() {

    companion object {
        fun newInstance() = StockArrivalFragment()
    }


    private var recyclerView: RecyclerView? = null
    private lateinit var viewModel: StockArrivalViewModel
    private var apiService: ApiService? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.stock_arrival_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StockArrivalViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView = view?.findViewById(R.id.rv)

    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData(){
        apiService = (context as? BaseActivity)?.apiService

        apiService?.let {
            it.fetchProceduresById(15256)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<ArrayList<Procedure>>{
                    override fun onSuccess(t: ArrayList<Procedure>) {
                        setRecycler(t)
                    }

                    override fun onSubscribe(d: Disposable) = Unit

                    override fun onError(e: Throwable) {
                        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
                    }

                })
        }
    }

    private fun setRecycler(list: ArrayList<Procedure>){
        recyclerView?.adapter = RecyclerAdapter(list, context!!)
        recyclerView?.layoutManager = LinearLayoutManager(context!!)
    }

}
