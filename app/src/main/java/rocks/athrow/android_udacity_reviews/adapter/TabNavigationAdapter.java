package rocks.athrow.android_udacity_reviews.adapter;

import android.content.Context;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import rocks.athrow.android_udacity_reviews.R;
import rocks.athrow.android_udacity_reviews.fragment.PullingFragment;
import rocks.athrow.android_udacity_reviews.fragment.ReviewsListActivityFragment;
import rocks.athrow.android_udacity_reviews.fragment.ReportsActivityFragment;

/**
 * ReviewsListActivityFragment
 * Created by jose on 7/24/16.
 */
public class TabNavigationAdapter extends FragmentPagerAdapter {
    Context mContext;
    public TabNavigationAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ReviewsListActivityFragment();
            case 1:
                return new ReportsActivityFragment();

            case 2:
                return new PullingFragment();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String[] navItems = mContext.getResources().getStringArray(R.array.nav_items);
        return navItems[position];
    }
}