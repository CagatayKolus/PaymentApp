package com.cagataykolus.paymentapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cagataykolus.paymentapp.data.local.dao.ApplicableDao
import com.cagataykolus.paymentapp.model.Applicable

/**
 * Created by Çağatay Kölüş on 27.08.2021.
 * cagataykolus@gmail.com
 */
/**
 * ApplicableDatabase provides DAO [ApplicableDao] by using method [getApplicableDao].
 */
@Database(
    entities = [Applicable::class],
    version = MigrationDatabase.DB_VERSION
)
abstract class ApplicableDatabase : RoomDatabase() {

    abstract fun getApplicableDao(): ApplicableDao

    companion object {
        private const val DB_NAME = "database_applicable"

        @Volatile
        private var INSTANCE: ApplicableDatabase? = null

        fun getInstance(context: Context): ApplicableDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ApplicableDatabase::class.java,
                    DB_NAME
                ).addMigrations(*MigrationDatabase.MIGRATION_APPLICABLE).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
