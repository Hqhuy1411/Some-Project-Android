package com.example.contactapp

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import java.io.Serializable

//
//import androidx.room.PrimaryKey
//
//@Entity
import kotlinx.parcelize.Parcelize
@Parcelize
class Contact  (
     val firstName: String,
     val lastName: String,
     var phone: String ? = "",
     var email: String ? = ""

): Parcelable {
     override fun toString(): String {
          return "Contact(firstName='$firstName', lastName='$lastName', phone=$phone, email=$email)"
     }
}