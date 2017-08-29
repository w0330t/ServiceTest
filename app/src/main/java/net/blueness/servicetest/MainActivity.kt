package net.blueness.servicetest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startService: Button = find(R.id.start_service)
        val stopService: Button = find(R.id.stop_service)
        startService.setOnClickListener(this)
        stopService.setOnClickListener(this)
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
        }
    }
}
