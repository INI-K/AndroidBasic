package com.inik.imageextract.mvp

import com.inik.imageextract.mvc.ImageCountModel
import com.inik.imageextract.mvp.repository.ImageRepository

class MvpPresenter(
    private val model: ImageCountModel,
    private val imageRepository: ImageRepository
) : MvpContractor.Presenter,ImageRepository.Callback {
    private var view :  MvpContractor.View? = null
    override fun attachView(view: MvpContractor.View) {
        this.view = view
    }

    override fun dettachView() {
        view = null
    }

    override fun loadRandomImage() {
        imageRepository.getRandomImage(this)
    }

    override fun loadImage(url: String, color: String) {
      model.increase()
        view?.showImage(url,color)
        view?.showImageCountText(model.count)
    }
}