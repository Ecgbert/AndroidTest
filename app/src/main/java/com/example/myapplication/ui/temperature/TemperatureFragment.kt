package com.example.myapplication.ui.temperature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.repository.TemperatureRepo
import com.example.myapplication.viewmodel.TemperatureViewModelFactory
import com.example.myapplication.vo.Status
import kotlinx.android.synthetic.main.temperature_fragment.*

class TemperatureFragment : Fragment() {

    private val repo: TemperatureRepo by lazy { return@lazy TemperatureRepo }
    private lateinit var mViewModel: TemperatureViewModel
    private lateinit var mAdapter: TemperatureAdapter
    private val mFactory: ViewModelProvider.Factory by lazy {
        return@lazy TemperatureViewModelFactory(repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = TemperatureAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.temperature_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        rv_temperature.apply {
            hasFixedSize()
            adapter = mAdapter
        }

        mAdapter.addChildClickViewIds(R.id.tv_temperature)
        mViewModel = requireActivity().let { activity ->
            ViewModelProvider(activity, mFactory).get(TemperatureViewModel::class.java)
        }

        mViewModel.temperature.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> mAdapter.setList(it.data)
                Status.ERROR -> Toast.makeText(
                    requireActivity().applicationContext,
                    "連線發生錯誤",
                    Toast.LENGTH_SHORT
                ).show()
                Status.LOADING -> Toast.makeText(
                    requireActivity().applicationContext,
                    "資料讀取中",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        mAdapter.setOnItemChildClickListener { _, view, position ->
            if (view.id == R.id.tv_temperature) {
                mViewModel.setShareModel(mAdapter.getItem(position).data)
                navController.navigate(R.id.temperatureDetailFragment)
                println(mAdapter.getItem(position).data)
            }
        }

        mViewModel.toast.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { isFirst ->
                if (!isFirst) {
                    Toast.makeText(
                        requireActivity().applicationContext,
                        "歡迎回來",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
}