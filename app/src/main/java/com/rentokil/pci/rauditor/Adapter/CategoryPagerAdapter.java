package com.rentokil.pci.rauditor.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rentokil.pci.rauditor.Fragments.Completed_Fragment;
import com.rentokil.pci.rauditor.Fragments.In_Progress_Fragment;
import com.rentokil.pci.rauditor.Fragments.Profile_Fragment;
import com.rentokil.pci.rauditor.Recyclerview;

/**
 * Created by wolfsoft on 10/11/2015.
 */
public class CategoryPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    Context context;
//    Recyclerview tb1;
//    Completed_Fragment tb2;
//    In_Progress_Fragment tb3;
//    Profile_Fragment tb4;
//



    public CategoryPagerAdapter(FragmentManager fm, int NumOfTabs,Context context) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        context=context;
    }


    @Override
    public Fragment getItem(int position) {
        Recyclerview tab1 = new Recyclerview();
        Completed_Fragment tab2 =new Completed_Fragment();
        In_Progress_Fragment tab3=new In_Progress_Fragment();
        Profile_Fragment tab4 =new Profile_Fragment();
        switch (position) {
            case 0:

                tab2.isDetached();
                tab3.isDetached();
                tab4.isDetached();
                return tab1;


            case 1:

                tab1.isDetached();
                tab3.isDetached();
                tab4.isDetached();
                return tab2;


            case 2:

                tab2.isDetached();
                tab1.isDetached();
                tab4.isDetached();
                return tab3;

            case 3:

                tab2.isDetached();
                tab3.isDetached();
                tab1.isDetached();
                return tab4;

            case 4:
                In_Progress_Fragment tab5 = new In_Progress_Fragment();
                return tab5;

            default:
                return null;

        }
    }



    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
