package com.cagataykolus.paymentapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cagataykolus.paymentapp.model.Applicable
import kotlinx.coroutines.flow.Flow

/**
 * Created by Çağatay Kölüş on 27.08.2021.
 * cagataykolus@gmail.com
 */
/**
 * Data Access Object (DAO) for [com.cagataykolus.paymentapp.data.local.ApplicableDatabase]
 */
@Dao
interface ApplicableDao {
    /**
     * Inserts [applicables] into the [Applicable.TABLE_APPLICABLE] table.
     * Duplicate values are replaced in the table.
     * @param applicables
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addApplicables(applicables: List<Applicable>)

    /**
     * Fetches all the data from the [Applicable.TABLE_APPLICABLE] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${Applicable.TABLE_APPLICABLE}")
    fun getAllApplicables(): Flow<List<Applicable>>

    /**
     * Deletes all the data from the [Applicable.TABLE_APPLICABLE] table.
     */
    @Query("DELETE FROM ${Applicable.TABLE_APPLICABLE}")
    suspend fun deleteAllApplicables()
}