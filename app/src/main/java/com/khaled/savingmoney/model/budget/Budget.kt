package com.khaled.savingmoney.model.budget

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Budget(
    val id: String? = null,
    val name: String? = null,
    @SerializedName("last_modified_on") val modifiedDate: String? = null,
    @SerializedName("first_month") val firstMonth: String? = null,
    @SerializedName("last_month") val lastMonth: String? = null,
    @SerializedName("date_format") val dateFormat: DateFormat? = null,
    @SerializedName("currency_format") val CurrencyFormat: CurrencyFormat? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        id = parcel.readString(),
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Budget> {
        override fun createFromParcel(parcel: Parcel): Budget {
            return Budget(parcel)
        }

        override fun newArray(size: Int): Array<Budget?> {
            return arrayOfNulls(size)
        }
    }
}