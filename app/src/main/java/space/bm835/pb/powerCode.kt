package space.bm835.pb

import java.io.DataOutputStream
import java.io.IOException

class powerCode {
    val cmd = "input keyevent 26"
    fun turnOff(){
        //execute input power button
        try {
            val su = Runtime.getRuntime().exec("su")
            val outputStream = DataOutputStream(su.outputStream)

            outputStream.writeBytes(cmd+"\n")
            outputStream.flush()

            outputStream.writeBytes("exit\n")
            outputStream.flush()
            su.waitFor()
        } catch (e: IOException) {
            throw Exception(e)
        } catch (e: InterruptedException) {
            throw Exception(e)
        }

    }
}