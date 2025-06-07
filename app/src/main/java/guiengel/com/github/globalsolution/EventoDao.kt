package guiengel.com.github.globalsolution

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EventoDao {
    @Query("SELECT * FROM eventos")
    suspend fun getAll(): List<eventoModel>

    @Insert
    suspend fun insert(eventoModel: eventoModel)

    @Delete
    suspend fun delete(eventoModel: eventoModel)
}