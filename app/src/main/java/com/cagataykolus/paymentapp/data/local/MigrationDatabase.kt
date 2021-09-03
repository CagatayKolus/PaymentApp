package com.cagataykolus.paymentapp.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cagataykolus.paymentapp.model.Applicable

/**
 * Created by Çağatay Kölüş on 27.08.2021.
 * cagataykolus@gmail.com
 */
/**
 * Each Migration has a start and end versions and Room runs these migrations to bring the
 * database to the latest version. The migration object that can modify the database and
 * to the necessary changes.
 */
object MigrationDatabase {
    const val DB_VERSION = 2

    val MIGRATION_APPLICABLE: Array<Migration>
        get() = arrayOf(
            migrationAPPLICABLE()
        )

    /**
     *  Creates a new migration between version 1 and 2 for [Applicable.TABLE_APPLICABLE] table.
     */
    private fun migrationAPPLICABLE(): Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ${Applicable.TABLE_APPLICABLE} ADD COLUMN body TEXT")
        }
    }
}
