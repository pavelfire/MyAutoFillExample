package com.vk.directop.myautofillexample

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.vk.directop.myautofillexample.SmsBroadcastReceiver.SmsBroadcastReceiverListener
import com.vk.directop.myautofillexample.databinding.ActivityMainBinding
import io.sentry.Sentry
import java.util.regex.Pattern

const val REQ_USER_CONSENT = 200

class MainActivity : AppCompatActivity() {

    lateinit var smsBroadcastReceiver: SmsBroadcastReceiver

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startSmartUserConsent()

        binding.btn.setOnClickListener {
            Sentry.captureException(RuntimeException("This app uses Sentry! PV softUp :)"))
            Log.d("TAG", "Pressed button")
        }
    }

    private fun startSmartUserConsent() {
        val client = SmsRetriever.getClient(this)
        client.startSmsUserConsent(null)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_USER_CONSENT) {
            if (resultCode == RESULT_OK && data != null) {
                val message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
                getCodeFromMessage(message)
            }
        }
    }

    private fun getCodeFromMessage(message: String?) {
//        val codePattern = Pattern.compile("(|^)\\d{6}")
//        val matcher = codePattern.matcher(message)
//        if (matcher.find()) {
//            binding.et.setText(matcher.group(0))
//        }
//        binding.et.setText(message)
        binding.et.setText(message?.take(5))
    }

    private fun registerBroadcastReceiver() {
        smsBroadcastReceiver = SmsBroadcastReceiver()
        smsBroadcastReceiver.smsBroadcastReceiverListener = object : SmsBroadcastReceiverListener {
            override fun onSuccess(intent: Intent) {
                startActivityForResult(intent, REQ_USER_CONSENT)
            }

            override fun onFailure() {}
        }
        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(smsBroadcastReceiver, intentFilter, RECEIVER_EXPORTED)
        }else {
            registerReceiver(smsBroadcastReceiver, intentFilter)
        }
    }

    override fun onStart() {
        super.onStart()
        registerBroadcastReceiver()
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(smsBroadcastReceiver)
    }
}