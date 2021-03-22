package com.kno85.tecnicaltest.ui.main.mvp

import com.kno85.tecnicaltest.domain.Character
import com.kno85.tecnicaltest.usecases.UseCases
import com.kno85.tecnicaltest.utils.ScopedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainPresenterImp (private val reposUseCase: UseCases) : MainPresenter, ScopedViewModel(){


    private lateinit var mView: MainView

    init {
        initScope()
    }


    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }



    override fun start(mainView: MainView) {
        mView= mainView
        getItems(mView)
    }



    private fun getItems(mView: MainView) {
        var items: List<Character>? = emptyList<Character>()
        GlobalScope.launch ( Dispatchers.Main) {
               mView.showLoadding()
            val call=  async ( Dispatchers.IO) {
                let { items= reposUseCase.invokeList() }
            }
            call.await()
            if(call.isCompleted && !items.isNullOrEmpty()){
                mView.addItems(items.let{items?.sortedBy { it.name }?.filter {it.description!!.isNotEmpty()}}!!)
                mView.hideLoadding()
            }else{
                mView.showError()
                mView.hideLoadding()
            }
        }
    }


}