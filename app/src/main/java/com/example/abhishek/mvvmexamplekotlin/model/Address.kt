package com.example.abhishek.mvvmexamplekotlin.model

import android.os.Parcel
import android.os.Parcelable

data class Address(
  val street: String?,
  val suite: String?,
  val city: String?,
  val zipcode: String?,
  val geo: Geo?
) : Parcelable {
  constructor(parcel: Parcel) : this(
      parcel.readString(),
      parcel.readString(),
      parcel.readString(),
      parcel.readString(),
      parcel.readParcelable(Geo::class.java.classLoader)
  )

  override fun writeToParcel(
    parcel: Parcel,
    flags: Int
  ) {
    parcel.writeString(street)
    parcel.writeString(suite)
    parcel.writeString(city)
    parcel.writeString(zipcode)
    parcel.writeParcelable(geo, flags)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Address> {
    override fun createFromParcel(parcel: Parcel): Address {
      return Address(parcel)
    }

    override fun newArray(size: Int): Array<Address?> {
      return arrayOfNulls(size)
    }
  }

}

data class Geo(
  val lat: String?,
  val lng: String?
) : Parcelable {
  constructor(parcel: Parcel) : this(
      parcel.readString(),
      parcel.readString()
  )

  override fun writeToParcel(
    parcel: Parcel,
    flags: Int
  ) {
    parcel.writeString(lat)
    parcel.writeString(lng)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Geo> {
    override fun createFromParcel(parcel: Parcel): Geo {
      return Geo(parcel)
    }

    override fun newArray(size: Int): Array<Geo?> {
      return arrayOfNulls(size)
    }
  }

}