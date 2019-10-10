package vn.mtouch.courtesycar.presentation.features;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import vn.mtouch.courtesycar.R;
import vn.mtouch.courtesycar.presentation.base_view.BaseActivity;
import vn.mtouch.courtesycar.presentation.features.backup_restore.BackupRestoreActivity;
import vn.mtouch.courtesycar.presentation.features.edit_term.EditTermActivity;
import vn.mtouch.courtesycar.presentation.features.filter_contracts.FilterContractDialog;
import vn.mtouch.courtesycar.presentation.features.list_car.ListCarFragment;
import vn.mtouch.courtesycar.presentation.features.list_contract.ContractFragment;
import vn.mtouch.courtesycar.utils.ImageUtils;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.contracts);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(R.id.fragment, ContractFragment.newInstance(1));

        ImageUtils.askStoragePermission(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    Menu menu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, this.menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            FilterContractDialog filterContractDialog = FilterContractDialog.newInstance();
            filterContractDialog.setListener(() -> {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
                if(fragment instanceof ContractFragment) {
                    ((ContractFragment)fragment).initObserve("");
                }
            });
            filterContractDialog.show(getSupportFragmentManager(), null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        this.menu.findItem(R.id.action_filter).setVisible(false);
        if (id == R.id.nav_camera) {
            // Handle the camera action
            this.menu.findItem(R.id.action_filter).setVisible(true);
            replaceFragment(R.id.fragment, ContractFragment.newInstance(1));
            getSupportActionBar().setTitle(R.string.contracts);
        } else if (id == R.id.nav_gallery) {
            replaceFragment(R.id.fragment, ListCarFragment.newInstance());
            getSupportActionBar().setTitle(R.string.list_car);
        } else if (id == R.id.nav_cloud) {
//            replaceFragment(R.id.fragment, ListCarFragment.newInstance());
//            getSupportActionBar().setTitle(R.string.cloud_backup_restore);

            startActivity(BackupRestoreActivity.getCallingIntent(this));
        }else if (id == R.id.nav_edit_term) {
            startActivity(EditTermActivity.getCallingIntent(this));
        } else if (id == R.id.nav_exit) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
