package rocks.athrow.android_udacity_reviews.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import rocks.athrow.android_udacity_reviews.R;
import rocks.athrow.android_udacity_reviews.util.Constants;

/**
 * Created by Anthony M. Santiago on 8/24/2016.
 */
public class ReviewPullerService extends Service {

    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();

    private static boolean isRunning = false;
    private static boolean isTesting = false;

    public static boolean isRunning() {
        return isRunning;
    }

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        public ReviewPullerService getService() {
            // Return this instance of ReviewPullerService so clients can call public methods
            return ReviewPullerService.this;
        }
    }

    public void stopService(){
        stopForeground(true);
        stopSelf();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopService();
        isRunning = false;
        isTesting = false;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v("Izodine", "Unbound");
        return super.onUnbind(intent);
    }

    public void pull(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                isTesting = true;
                while(isTesting){
                    Log.v("Izodine", "Pulsing");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        isRunning = true;
        Log.v("Izodine", "Bound");
        startForeground(Constants.NOTIFICATION_PULLING_ID,
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.icon_star_filled)
                        .setContentTitle("Udacity Reviews")
                        .setContentText("Review Pulling Is Enabled").build());
        return mBinder;
    }
}
