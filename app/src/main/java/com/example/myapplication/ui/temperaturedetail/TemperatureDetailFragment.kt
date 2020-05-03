package com.example.myapplication.ui.temperaturedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.ui.temperature.TemperatureViewModel
import kotlinx.android.synthetic.main.temperature_detail_fragment.*

class TemperatureDetailFragment : Fragment() {

    private lateinit var mViewModel: TemperatureViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.temperature_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = requireActivity().let { activity ->
            ViewModelProvider(activity).get(TemperatureViewModel::class.java)
        }
        mViewModel.shareModel.observe(viewLifecycleOwner, Observer { time ->
            tv_center.text =
                "${time.startTime} \n${time.endTime} \n${time.parameter.parameterName}${time.parameter.parameterUnit}"
        })
    }
}