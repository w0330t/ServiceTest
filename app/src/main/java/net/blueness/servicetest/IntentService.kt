package net.blueness.servicetest

import android.app.IntentService
import android.content.Intent
import android.util.Log

/**
 * Created by Blueness on 2017/8/30.
 */
class MyIntentService: IntentService("MyIntentService"){
    
    override fun onHandleIntent(intent: Intent?) {
        Log.i("MyIntentService", "Thread id is " + Thread.currentThread().id)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MyIntentService", "onDestroy executed")
    }
}