package vn.mtouch.courtesycar.presentation.base_view;

import android.graphics.Color;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import vn.mtouch.courtesycar.R;


/**
 * Created by 14617 on 22/1/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected void setupToolbar(Toolbar toolbar, int idResourceTitle) {
        toolbar.setTitle(idResourceTitle);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    protected void onBackStackFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        if(getSupportFragmentManager().isDestroyed()) {
            return;
        }
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.add(containerViewId, fragment);
        transaction.addToBackStack("back stack").commit();
        //transaction.commit();
    }

    public void replaceFragment(int containerViewId, Fragment fragment) {
        if(getSupportFragmentManager().isDestroyed()) {
            return;
        }
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(containerViewId, fragment);
        try {
            transaction.commitAllowingStateLoss();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeFragment(int id){
        if(getSupportFragmentManager().isDestroyed()) {
            return;
        }
        if (getSupportFragmentManager().findFragmentById(id) != null) {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(id);
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(fragment);
            try {
                ft.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            finish();
        }
    }
}
