package guiengel.com.github.globalsolution

//Guilherme Luis Engel RM-87438
// Gabriel Dias Santiago RM-551406

import EventoAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: EventoAdapter
    private lateinit var db: EventoDatabase
    private lateinit var dao: EventoDao
    private val listaEventos = mutableListOf<eventoModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etLocal = findViewById<EditText>(R.id.etLocal)
        val etTipoEvento = findViewById<EditText>(R.id.etTipoEvento)
        val etGrauImpacto = findViewById<EditText>(R.id.etGrauImpacto)
        val etDataEvento = findViewById<EditText>(R.id.etDataEvento)
        val etNumPessoas = findViewById<EditText>(R.id.etNumPessoas)
        val btnIncluir = findViewById<Button>(R.id.btnIncluir)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Lista de eventos"

        db = EventoDatabase.getDatabase(this)
        dao = db.eventoDao()

        adapter = EventoAdapter(listaEventos) { index ->
            val evento = listaEventos[index]
            lifecycleScope.launch {
                dao.delete(evento)
                listaEventos.removeAt(index)
                adapter.notifyItemRemoved(index)
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        lifecycleScope.launch {
            listaEventos.addAll(dao.getAll())
            adapter.notifyDataSetChanged()
        }

        btnIncluir.setOnClickListener{
            val local = etLocal.text.toString()
            val tipo = etTipoEvento.text.toString()
            val grau = etGrauImpacto.text.toString()
            val data = etDataEvento.text.toString()
            val pessoas = etNumPessoas.text.toString().toIntOrNull()

            if(local.isBlank() || tipo.isBlank() || grau.isBlank() || data.isBlank() || pessoas == null || pessoas <= 0){
                Toast.makeText(this, "Preencha corretamente os textos", Toast.LENGTH_SHORT).show()
            }else{
                val evento = eventoModel(0, local, tipo, grau, data, pessoas)
                lifecycleScope.launch {
                    dao.insert(evento)
                    listaEventos.add(evento)
                    adapter.notifyItemInserted(listaEventos.size - 1)
                }
                etLocal.text.clear()
                etTipoEvento.text.clear()
                etGrauImpacto.text.clear()
                etDataEvento.text.clear()
                etNumPessoas.text.clear()

            }
        }

        val btnMostrarRM = findViewById<Button>(R.id.btnMostrarRM)

        btnMostrarRM.setOnClickListener{
            val inflater = layoutInflater
            val dialogView = inflater.inflate(R.layout.mostrar_rm, null)

            val txt1 = dialogView.findViewById<TextView>(R.id.textoRms)
            val txt2 = dialogView.findViewById<TextView>(R.id.textoRms2)

            txt1.text = "Guilherme Luis Engel RM-87438"
            txt2.text = "Gabriel Dias Santiago RM-551406"

            val builder = AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton("Fechar", null)

            builder.create().show()
        }


    }
}