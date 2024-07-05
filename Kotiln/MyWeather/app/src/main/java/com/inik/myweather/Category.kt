package com.inik.myweather

import com.google.gson.annotations.SerializedName

enum class Category {
    @SerializedName("POP")
    POP, // 걍수 확률

    @SerializedName("PTY")
    PTY, // 강수 형태

    @SerializedName("SKY")
    SKY, // 하늘 상태

    @SerializedName("TMP")
    TMP, // 1시간 기온
}