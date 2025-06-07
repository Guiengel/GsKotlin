package guiengel.com.github.globalsolution

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "eventos")
data class eventoModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val local: String,
    val tipoEvento: String,
    val grauImpacto: String,
    val dataEvento: String,
    val numPessoas: Int,
)
