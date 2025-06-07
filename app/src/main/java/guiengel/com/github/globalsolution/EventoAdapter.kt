import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import guiengel.com.github.globalsolution.R
import guiengel.com.github.globalsolution.eventoModel

class EventoAdapter(
    private val lista: MutableList<eventoModel>,
    private val onExcluirClick: (Int) -> Unit
) : RecyclerView.Adapter<EventoAdapter.EventoViewHolder>() {

    class EventoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewLocal: TextView = view.findViewById(R.id.textViewLocal)
        val textViewTipoEvento: TextView = view.findViewById(R.id.textViewTipoEvento)
        val textViewGrauImpacto: TextView = view.findViewById(R.id.textViewGrauImpacto)
        val textViewDataEvento: TextView = view.findViewById(R.id.textViewDataEvento)
        val textViewNumPessoas: TextView = view.findViewById(R.id.textViewNumPessoas)
        val excluir: ImageButton = view.findViewById(R.id.excluir)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.evento_layout, parent, false)
        return EventoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        val evento = lista[position]
        holder.textViewLocal.text = evento.local
        holder.textViewTipoEvento.text = evento.tipoEvento
        holder.textViewGrauImpacto.text = evento.grauImpacto
        holder.textViewDataEvento.text = evento.dataEvento
        holder.textViewNumPessoas.text = evento.numPessoas.toString()
        holder.excluir.setOnClickListener {
            onExcluirClick(position)
        }
    }

    override fun getItemCount(): Int = lista.size
}