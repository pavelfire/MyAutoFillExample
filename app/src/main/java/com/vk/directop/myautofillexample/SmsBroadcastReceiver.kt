package com.vk.directop.myautofillexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status

class SmsBroadcastReceiver : BroadcastReceiver() {

    lateinit var smsBroadcastReceiverListener: SmsBroadcastReceiverListener

    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent?.action == SmsRetriever.SMS_RETRIEVED_ACTION) {

            val extras = intent.extras
            val smsRetrieverStatus: Status = extras?.get(SmsRetriever.EXTRA_STATUS) as Status

            when (smsRetrieverStatus.statusCode) {
                CommonStatusCodes.SUCCESS -> {
                    val messageIntent: Intent? =
                        extras?.getParcelable(SmsRetriever.EXTRA_CONSENT_INTENT)
                    if (messageIntent != null)
                        smsBroadcastReceiverListener.onSuccess(messageIntent)
                }

                CommonStatusCodes.TIMEOUT -> {
                    smsBroadcastReceiverListener.onFailure()
                }
            }
        }
    }

    interface SmsBroadcastReceiverListener {
        fun onSuccess(intent: Intent)
        fun onFailure()
    }
}