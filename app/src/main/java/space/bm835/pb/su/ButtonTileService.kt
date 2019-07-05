package space.bm835.pb.su

import android.os.Build
import android.service.quicksettings.TileService
import android.support.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
class ButtonTileService : TileService() {

    override fun onClick() {
        super.onClick()
        val turn = powerCode()
        turn.turnOff()
    }

    override fun onTileRemoved() {
        super.onTileRemoved()

        // Do something when the user removes the Tile
    }

    override fun onTileAdded() {
        super.onTileAdded()

        // Do something when the user add the Tile
    }

    override fun onStartListening() {
        super.onStartListening()

        // Called when the Tile becomes visible
    }

    override fun onStopListening() {
        super.onStopListening()

        // Called when the tile is no longer visible
    }

    companion object {

        internal val RESULT_ENABLE = 1
    }
}