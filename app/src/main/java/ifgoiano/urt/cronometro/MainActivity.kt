package ifgoiano.urt.cronometro
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
        }

        runTimer()
    }

    fun onClickStart(view: View) {
    }

    fun onClickStop(view: View) {
    }

    fun onClickReset(view: View) {
    }

    private fun runTimer() {
        val handler = Handler(Looper.getMainLooper())
        handler.post(object: Runnable{
            override fun run() {
                }
                handler.postDelayed(this, 1000)
            }
        })

    }


}