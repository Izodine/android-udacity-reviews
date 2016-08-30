package rocks.athrow.android_udacity_reviews.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import rocks.athrow.android_udacity_reviews.R;
import rocks.athrow.android_udacity_reviews.util.Constants;

/**
 * Created by Anthony M. Santiago on 8/24/2016.
 */
public class ReviewPullerService extends IntentService {

    public ReviewPullerService() {
        super(ReviewPullerService.class.getName());
    }


    @Override
    protected void onHandleIntent(Intent intent) {

    }

    private void enablePulling(){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.icon_star_filled)
                        .setContentTitle("Udacity Reviews")
                        .setOngoing(true)
                        .setContentText("Review Pulling Is Enabled");
        mBuilder.build();

        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mNotifyMgr.notify(Constants.NOTIFICATION_PULLING_ID, mBuilder.build());
    }

    private void pull(){

    }

    private void disablePulling(){
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mNotifyMgr.cancel(Constants.NOTIFICATION_PULLING_ID);
    }
}
