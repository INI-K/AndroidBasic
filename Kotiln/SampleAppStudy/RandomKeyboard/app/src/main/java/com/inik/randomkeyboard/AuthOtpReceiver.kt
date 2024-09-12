package com.inik.randomkeyboard

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status

/**
 * 1.문자 내용이 140바이트를 초과하면 안됨
 * 2.문자앞에 <#>가 포함되어야한다.
 * 3.맨 마지막에 앱을 식별하는 11글자 해시코드가 존재해야함
 */
class AuthOtpReceiver: BroadcastReceiver() {
    private var listener: OtpReceiveListener? = null


    fun setOtpListener(receiveListener: OtpReceiveListener){
        this.listener = receiveListener
    }

    fun doFilter() = IntentFilter().apply {
        addAction(SmsRetriever.SMS_RETRIEVED_ACTION)
    }
    interface OtpReceiveListener{
        fun onOtpReceived(otp: String)
    }
    companion object {
        private const val PATTERN = "^<#>.*\\[Sample\\].+\\[(\\d{6})\\].+\$"
    }
    override fun onReceive(context: Context, intent: Intent) {
        if(intent.action == SmsRetriever.SMS_RETRIEVED_ACTION){
            intent.extras?.let {bundle ->
                val staus = bundle.get(SmsRetriever.EXTRA_STATUS) as Status
                when(staus.statusCode){
                    CommonStatusCodes.SUCCESS -> {
                        val otpSms = bundle.getString(SmsRetriever.EXTRA_SMS_MESSAGE,"")
                        if(listener != null && otpSms.isNotEmpty()){
                            val otp = PATTERN.toRegex().find(otpSms)?.destructured?.component1()
                            if(!otp.isNullOrBlank()){
                                listener!!.onOtpReceived(otp)
                            }
                        }
                    }
                }
            }
        }
    }
}