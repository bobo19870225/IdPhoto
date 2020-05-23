package cn.gz3create.idphoto;

import android.Manifest;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.cgfay.filter.glfilter.resource.FilterHelper;
import com.cgfay.filter.glfilter.resource.MakeupHelper;
import com.cgfay.filter.glfilter.resource.ResourceHelper;
import com.cgfay.uitls.utils.PermissionUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import cn.gz3create.idphoto.utils.StatusBarUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setStatusBarDarkTheme(this, true);
        if (PermissionUtils.permissionChecking(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            initResources();
        }
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
    /**
     * 初始化动态贴纸、滤镜等资源
     */
    private void initResources() {
        new Thread(() -> {
            ResourceHelper.initAssetsResource(MainActivity.this);
            FilterHelper.initAssetsFilter(MainActivity.this);
            MakeupHelper.initAssetsMakeup(MainActivity.this);
        }).start();
    }
}
