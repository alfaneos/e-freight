package kz.afr.efreight.view.receiver_accept

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kz.afr.efreight.R

class ReceiverAcceptFragment : Fragment() {

    companion object {
        fun newInstance() = ReceiverAcceptFragment()
    }

    private lateinit var viewModel: ReceiverAcceptViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.receiver_accept_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ReceiverAcceptViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
