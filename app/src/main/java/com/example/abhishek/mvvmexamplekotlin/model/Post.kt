package com.example.abhishek.mvvmexamplekotlin.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
  val userId: Int?,
  @field:PrimaryKey
  val id: Int,
  val title: String?,
  val body: String?
) : Parcelable {
  constructor(parcel: Parcel) : this(
      parcel.readValue(Int::class.java.classLoader) as? Int,
      parcel.readInt(),
      parcel.readString(),
      parcel.readString()
  )

  override fun writeToParcel(
    parcel: Parcel,
    flags: Int
  ) {
    parcel.writeValue(userId)
    parcel.writeInt(id)
    parcel.writeString(title)
    parcel.writeString(body)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Post> {
    override fun createFromParcel(parcel: Parcel): Post {
      return Post(parcel)
    }

    override fun newArray(size: Int): Array<Post?> {
      return arrayOfNulls(size)
    }
  }

}