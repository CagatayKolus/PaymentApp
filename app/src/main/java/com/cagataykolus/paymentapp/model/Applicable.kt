package com.cagataykolus.paymentapp.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


@Entity(tableName = Applicable.TABLE_APPLICABLE)
@TypeConverters(LinksXConverter::class)
data class Applicable(
    var code: String = "",
    var grouping: String = "",
    @PrimaryKey
    @NonNull
    var label: String = "",
    var method: String = "",
    var operationType: String = "",
    var recurrence: String = "",
    var redirect: Boolean = false,
    var registration: String = "",
    var selected: Boolean = false,
    val links: LinksX,
)  {
    companion object {
        const val TABLE_APPLICABLE = "table_applicable"
    }
}