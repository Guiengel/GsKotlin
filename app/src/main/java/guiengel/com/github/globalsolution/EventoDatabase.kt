package guiengel.com.github.globalsolution

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [eventoModel::class], version = 1)
abstract class EventoDatabase : RoomDatabase() {
    abstract fun eventoDao(): EventoDao

    companion object{
        @Volatile
        private var INSTANCE: EventoDatabase? = null

        fun getDatabase(context: Context): EventoDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EventoDatabase::class.java,
                    "evento_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}