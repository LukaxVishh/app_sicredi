package com.sicredi.onboarding.data.local


import android.content.Context
import androidx.room.*
import com.sicredi.onboarding.data.model.Associate
import java.time.LocalDate


@Database(
    entities = [Associate::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun associateDao(): AssociateDao


    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null


        fun get(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "sicredi_onboarding.db"
            ).fallbackToDestructiveMigration().build().also { INSTANCE = it }
        }
    }
}


class Converters {
    @TypeConverter fun fromEpoch(value: Long?): LocalDate? = value?.let { LocalDate.ofEpochDay(it) }
    @TypeConverter fun toEpoch(date: LocalDate?): Long? = date?.toEpochDay()
}