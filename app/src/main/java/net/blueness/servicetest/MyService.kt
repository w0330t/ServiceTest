package net.blueness.servicetest

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log

class MyService : Service() {

    private val TAG = "MyService"
    private val mBinder = DownloadBinder()

    inner class DownloadBinder: Binder() {

        fun startDownload(){
            Log.i(TAG, "startDownload executed")
        }

        fun getProgress(): Int{
            Log.i(TAG, "getProgress executed")
            return 0
        }
    }

    override fun onBind(intent: Intent): IBinder? {
//        throw UnsupportedOperationException("Not yet implemented")
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate executed")

        val intent = Intent(this, MainActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, 0)
        val notification = NotificationCompat.Builder(this)
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build()
        startForeground(1, notification)

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand executed")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"onDestroy executed")
    }
}
