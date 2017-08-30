package net.blueness.servicetest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Button
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var downloadBinder: MyService.DownloadBinder? = null

    private val connection = object: ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?){}

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            downloadBinder = service as MyService.DownloadBinder?
            downloadBinder?.startDownload()
            downloadBinder?.getProgress()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startService: Button = find(R.id.start_service)
        val stopService: Button = find(R.id.stop_service)
        val bindService: Button = find(R.id.bind_service)
        val unbindService: Button = find(R.id.unbind_service)
        val startIntentService: Button = find(R.id.start_intent_service)
        startService.setOnClickListener(this)
        stopService.setOnClickListener(this)
        bindService.setOnClickListener(this)
        unbindService.setOnClickListener(this)
        startIntentService.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.start_service -> {
                val startIntent = Intent(this, MyService::class.java)
                startService(startIntent)
            }
            R.id.stop_service -> {
                val stopIntent = Intent(this, MyService::class.java)
                stopService(stopIntent)
            }
            R.id.bind_service -> {
                val bindIntent = Intent(this, MyService::class.java)
                bindService(bindIntent, connection, Context.BIND_AUTO_CREATE)
            }
            R.id.unbind_service -> unbindService(connection)
            R.id.start_intent_service -> {
                Log.i("MainActivity", "Thread id is " + Thread.currentThread().id)
                val intentService = Intent(this, MyIntentService::class.java)
                startService(intentService)
            }
        }
    }
}
