package vn.mtouch.courtesycar.presentation.base_view;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import vn.mtouch.courtesycar.CourtesyCarApp;

/**
 * Created by 14617 on 22/1/2018.
 */

public abstract class BaseFragment extends Fragment {
    public static final int TOOLBAR_IC_MENU = 1;
    public static final int TOOLBAR_IC_BACK = 2;

    protected void setupToolbar(Activity activity, Toolbar toolbar, int idResourceTitle, int idResTitleColor, int type) {
        if (toolbar != null) {
            activity.setTitle(idResourceTitle);
            ((AppCompatActivity) activity).setSupportActionBar(toolbar);
            ((AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setTitle(idResourceTitle);
            toolbar.setTitleTextColor(idResTitleColor);

            if (type == TOOLBAR_IC_BACK) {
                //toolbar.setNavigationIcon(R.drawable.ic_action_back_white);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CourtesyCarApp.getActivity().finish();
                    }
                });
                return;
            }
//            if (type == TOOLBAR_IC_MENU) {
//                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, ((DashboardActivity) activity).getmDrawerLayout(), toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//                ((DashboardActivity) activity).getmDrawerLayout().addDrawerListener(toggle);
//                toggle.syncState();
//            }
        }
    }

    protected void replaceChildFragment(int idContainer, Fragment fragmentB) {
        if (fragmentB == null || !isAdded()) {
            return;
        }
        FragmentManager childFragMan = getChildFragmentManager();
        FragmentTransaction childFragTrans = childFragMan.beginTransaction();
        childFragTrans.replace(idContainer, fragmentB);
        try {
            childFragTrans.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void removeChildFragment(int id) {
        FragmentManager childFragMan = getChildFragmentManager();
        Fragment fragment = childFragMan.findFragmentById(id);
        if (fragment == null || !isAdded()) {
            return;
        }
        FragmentTransaction childFragTrans = childFragMan.beginTransaction();
        childFragTrans.remove(fragment);
        try {
            childFragTrans.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
