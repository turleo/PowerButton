package space.bm835.pb;

import android.annotation.TargetApi;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.service.quicksettings.TileService;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.N)
public class ButtonTileService extends TileService {

    static final int RESULT_ENABLE = 1;

    DevicePolicyManager devicePolicyManager;
    ComponentName componentName;

    public void onClick() {
        super.onClick();

        devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(this, AdminManager.class);

        //Always check before execute for safety
        if(devicePolicyManager.isAdminActive(componentName)){
            //looks like administrator permission is granted, so just turn off the screen
            devicePolicyManager.lockNow();
        }else{
            //Looks like administrator permission is not granted, so show the user current situation
            Toast.makeText(this, "Device administrator permission is not granted, the application could not perform requested action", Toast.LENGTH_LONG).show();

            //button1 clicked, so we request administrator permission
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, R.string.please_grant);
            startActivity(intent);
        }
        // Called when the user click the tile
    }

    public void onTileRemoved() {
        super.onTileRemoved();

        // Do something when the user removes the Tile
    }

    public void onTileAdded() {
        super.onTileAdded();

        // Do something when the user add the Tile
    }

    public void onStartListening() {
        super.onStartListening();

        // Called when the Tile becomes visible
    }

    public void onStopListening() {
        super.onStopListening();

        // Called when the tile is no longer visible
    }
}
