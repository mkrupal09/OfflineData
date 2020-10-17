package com.dc.offlinefirstarchitecture.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class User {

    @PrimaryKey(autoGenerate = true)
     var id: Int = 0

    @SerializedName("gender")
    var gender: String = ""

    @Embedded
    @SerializedName("name")
    var name: Name? = null

    @SerializedName("email")
    var email: String = ""

    @Embedded
    @SerializedName("picture")
    var picture: Picture? = null

    @Embedded
    @SerializedName("dob")
    var dateOfBirth: DateOfBirth? = null

    @Embedded
    @SerializedName("location")
    var location: Location? = null


}

class Name {
    @SerializedName("title")
    var title: String = ""

    @SerializedName("first")
    var first: String = ""

    @SerializedName("last")
    var last: String = ""
}

class Picture {

    @SerializedName("large")
    var largeImage: String = ""

    @SerializedName("thumb")
    var thumbImage: String = ""

}

class DateOfBirth {
    @SerializedName("date")
    var date: String = ""

    @SerializedName("age")
    var age: String = ""

}

class Location {
    @SerializedName("city")
    var city: String = ""

    @SerializedName("state")
    var state: String = ""

    @SerializedName("country")
    var country: String = ""

    @SerializedName("postcode")
    var postCode: String = ""
}
