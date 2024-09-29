package ifgoiano.urt.cronometro
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ifgoiano.urt.cronometro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // Variáveis para controle de tempo
    private var seconds = 0
    private var running = false
    private var wasRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Verifica se há um estado salvo e restaura
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
            wasRunning = savedInstanceState.getBoolean("wasRunning")
        }

        // Executa o cronômetro
        runTimer()
    }

    // Método chamado quando o botão Start é clicado
    fun onClickStart(view: View) {
        running = true
    }

    // Método chamado quando o botão Stop é clicado
    fun onClickStop(view: View) {
        running = false
    }

    // Método chamado quando o botão Reset é clicado
    fun onClickReset(view: View) {
        running = false
        seconds = 0
    }

    // Atualiza o tempo no TextView
    private fun runTimer() {
        val handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val secs = seconds % 60

                // Atualiza o texto do TextView
                binding.timeView.text = String.format("%02d:%02d:%02d", hours, minutes, secs)

                // Incrementa os segundos se o cronômetro estiver rodando
                if (running) {
                    seconds++
                }

                // Reexecuta o código a cada segundo
                handler.postDelayed(this, 1000)
            }
        })
    }

    // Salva o estado da atividade (segundos, execução, etc)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("seconds", seconds)
        outState.putBoolean("running", running)
        outState.putBoolean("wasRunning", wasRunning)
    }

    // Restaura o estado do cronômetro ao pausar/resumir a Activity
    override fun onPause() {
        super.onPause()
        wasRunning = running
        running = false
    }

    override fun onResume() {
        super.onResume()
        if (wasRunning) {
            running = true
        }
    }
}