package ifgoiano.urt.cronometro
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var segundos = 0
    private var execucao = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            savedInstanceState.putInt("segundos", segundos)
            savedInstanceState.putBoolean("execucao", execucao)
        }

        runTimer()
    }

    private fun updateTimerUI() {
        val timeView = findViewById<TextView>(R.id.timeView)
        val hours = segundos / 3600
        val minutes = (segundos % 3600) / 60
        val secs = segundos % 60
        val time = String.format("%d:%02d:%02d", hours, minutes, secs)
        timeView.text = time
    }

    fun onClickStart(view: View) {
        execucao = true
    }

    fun onClickStop(view: View) {
        execucao = false
    }

    fun onClickReset(view: View) {
        execucao = false
        segundos = 0
        updateTimerUI()

    }

    private fun runTimer() {
        val handler = Handler(Looper.getMainLooper())
        handler.post(object: Runnable {
            override fun run() {
                updateTimerUI()
                if (execucao) {
                    segundos++
                    updateTimerUI()
                }
                handler.postDelayed(this, 1000)
            }
        })
    }

}