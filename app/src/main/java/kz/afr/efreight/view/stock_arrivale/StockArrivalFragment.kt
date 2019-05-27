package kz.afr.efreight.view.stock_arrivale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
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


class StockArrivalFragment : Fragment(), RecyclerAdapter.ProcedureDelegate {



    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
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

        recyclerView = view?.findViewById(R.id.rv)
        progressBar = view?.findViewById(R.id.progress)
    }



    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData(){
        apiService = (context as? BaseActivity)?.apiService

        recyclerView?.visibility = View.GONE
        progressBar?.visibility = View.VISIBLE


        apiService?.let {
            it.fetchProceduresById(15256)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<ArrayList<Procedure>>{
                    override fun onSuccess(t: ArrayList<Procedure>) {
                        setRecycler(t)

                        recyclerView?.visibility = View.VISIBLE
                        progressBar?.visibility = View.GONE
                    }

                    override fun onSubscribe(d: Disposable) = Unit

                    override fun onError(e: Throwable) {
                        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()

                        recyclerView?.visibility = View.VISIBLE
                        progressBar?.visibility = View.GONE
                    }

                })
        }
    }

    private fun setRecycler(list: ArrayList<Procedure>){
        recyclerView?.adapter = RecyclerAdapter(list, context!!, this)
        recyclerView?.layoutManager = LinearLayoutManager(context!!)
    }

    override fun itemClicked(item: Procedure) {
        (activity as? BaseActivity)?.startFragment(ProcedureDetailsFragment())
    }

}
