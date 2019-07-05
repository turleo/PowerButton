package space.bm835.pb.su

import android.app.Activity
import android.os.Bundle

class TurnOffActivity : Activity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val turn = powerCode()
        turn.turnOff()
        finish()
    }

}