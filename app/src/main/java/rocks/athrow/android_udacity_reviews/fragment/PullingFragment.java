package rocks.athrow.android_udacity_reviews.fragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import rocks.athrow.android_udacity_reviews.R;
import rocks.athrow.android_udacity_reviews.service.ReviewPullerService;

/**
 * A simple {@link Fragment} subclass.
 */
public class PullingFragment extends Fragment {

    /** Messenger for communicating with the service. */
    private ReviewPullerService mService = null;

    /** Flag indicating whether we have called bind on the service. */
    private boolean mBound;

    /** Flag indicating the user has requested to stop the service.*/
    private boolean mStopRequested = false;


    public PullingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pulling, container, false);

        final Switch pullSwitch = (Switch) rootView.findViewById(R.id.enablePullingSwitch);
        final Context context = getContext();

        pullSwitch.setChecked(ReviewPullerService.isRunning());

        pullSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pullSwitch.isChecked()){
                    context.startService(new Intent(context, ReviewPullerService.class));
                    context.bindService(new Intent(context, ReviewPullerService.class), mConnection, Context.BIND_AUTO_CREATE);
                }
                else{
                    if(ReviewPullerService.isRunning() && mBound){
                        context.unbindService(mConnection);
                    }
                    context.stopService(new Intent(context, ReviewPullerService.class));
                }
            }
        });

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        getContext().unbindService(mConnection);
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {
        // Called when the connection with the service is established
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.e("Izodine", "onServiceConnected");
            // Because we have bound to an explicit
            // service that is running in our own process, we can
            // cast its IBinder to a concrete class and directly access it.
            ReviewPullerService.LocalBinder binder = (ReviewPullerService.LocalBinder) service;
            mService = binder.getService();

            mBound = true;

            mService.pull();

        }

        // Called when the connection with the service disconnects unexpectedly
        public void onServiceDisconnected(ComponentName className) {
            Log.e("Izodine", "onServiceDisconnected");
            mBound = false;
        }
    };

}
