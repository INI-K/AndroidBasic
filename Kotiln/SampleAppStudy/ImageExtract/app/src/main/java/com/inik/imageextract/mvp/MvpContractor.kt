package com.inik.imageextract.mvp

interface MvpContractor {
    interface View{
        fun showImage(url: String, color:String)
        fun showImageCountText(count: Int)
    }

    interface Presenter{
        fun attachView(view: View)

        fun dettachView()

        fun loadRandomImage()
    }
}