package com.example.myapplication.ui.temperature

import androidx.lifecycle.*
import com.example.myapplication.R
import com.example.myapplication.api.ApiEmptyResponse
import com.example.myapplication.api.ApiErrorResponse
import com.example.myapplication.api.ApiSuccessResponse
import com.example.myapplication.repository.TemperatureRepo
import com.example.myapplication.util.Event
import com.example.myapplication.util.SharedPreferencesHelper
import com.example.myapplication.vo.Image
import com.example.myapplication.vo.Resource
import com.example.myapplication.vo.TempModel
import com.example.myapplication.vo.temperature.Time
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TemperatureViewModel(temperatureRepo: TemperatureRepo) : ViewModel() {

    private val getTemperature = temperatureRepo.loadTemperatureForecast()

    private val result = mutableListOf<TempModel>()

    private val _timeModelLiveData = MutableLiveData<Time>()

    private val _toastLiveData = MutableLiveData<Event<Boolean>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = SharedPreferencesHelper.read(SharedPreferencesHelper.FIRST_TIME, true)
            println("SP內的值是 $result")
            if (result) {
                SharedPreferencesHelper.write(SharedPreferencesHelper.FIRST_TIME, false)
            }
            _toastLiveData.postValue(Event(result))
        }
    }

    val temperature: LiveData<Resource<List<TempModel>>> = Transformations.map(getTemperature) {
        when (it) {
            is ApiSuccessResponse -> {
                val x =
                    it.body.records.location.find { location -> return@find location.locationName == "臺北市" }
                val timeList =
                    x?.weatherElement?.find { element -> return@find element.elementName == "MinT" }?.time
                timeList?.forEach { time ->
                    val model = TempModel(time, 1)
                    val image = TempModel(Image(R.mipmap.ic_launcher), 2)
                    result.add(model)
                    result.add(image)
                }
                return@map Resource.success(result)
            }
            is ApiEmptyResponse -> Resource.success(null)
            is ApiErrorResponse -> Resource.error(it.errorMessage, null)
        }
    }

    fun setShareModel(model: Any) {
        if (model is Time) {
            _timeModelLiveData.value = model
        }
    }

    val shareModel: LiveData<Time>
        get() = _timeModelLiveData

    val toast: LiveData<Event<Boolean>>
        get() = _toastLiveData

}