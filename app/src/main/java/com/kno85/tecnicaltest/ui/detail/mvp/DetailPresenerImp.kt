package com.kno85.tecnicaltest.ui.detail.mvp

import com.kno85.tecnicaltest.domain.Character
import com.kno85.tecnicaltest.usecases.UseCases
import com.kno85.tecnicaltest.utils.ScopedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DetailPresenterImp (private val reposUseCase: UseCases) : DetailPresenter, ScopedViewModel(){


    private lateinit var dView: DetailView

    init {
        initScope()
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }


    override fun checkUpates(detailView: DetailView, id: Int) {
        dView= detailView
        check(dView, id)    }


    private fun check(dView: DetailView, id:Int) {
        var item: Character? = Character()
        var items: List<Character>? = emptyList<Character>()

        GlobalScope.launch ( Dispatchers.Main) {
            val call=  async ( Dispatchers.IO) {
                let { items= reposUseCase.invokeList() }
            }
            val callTwo=  async ( Dispatchers.IO) {
                let { item= reposUseCase.checkItem(id) }
            }

            call.await()
            callTwo.await()

            if(call.isCompleted && callTwo.isCompleted){
                val selectedItem= items.let { items?.find { it.id == id} }
                if(item?.id==0) {
                    dView.showError()
                } else {
                    when {
                        hasChangedValues(selectedItem, item) -> {
                            dView.showChangesResponse()
                        }
                        else -> {
                            dView.showNoChangesResponse()
                        }
                    }
                }
            }else{
                dView.showError()
            }
        }
    }

    private fun hasChangedValues(selectedItem: Character?, item: Character?):Boolean {
        return !selectedItem?.description.equals(item?.description) || !selectedItem?.thumbnail.equals(item?.thumbnail)
    }


}