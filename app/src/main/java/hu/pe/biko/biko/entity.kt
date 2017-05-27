package hu.pe.biko.biko

import android.os.Parcel
import android.os.Parcelable

data class Place(var name: String, var description: String, var address: String,
                 var lat: Double, var lng: Double,
                 var city: String, var country: String, var state: String) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Place> = object : Parcelable.Creator<Place> {
            override fun createFromParcel(source: Parcel): Place = Place(source)
            override fun newArray(size: Int): Array<Place?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readDouble(),
            source.readDouble(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(description)
        dest.writeString(address)
        dest.writeDouble(lat)
        dest.writeDouble(lng)
        dest.writeString(city)
        dest.writeString(country)
        dest.writeString(state)
    }
}

data class Route(var name: String, var description: String, var image: String,
                 var city: String, var country: String, var state:
                 String, var distance: Int, var time: String) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Route> = object : Parcelable.Creator<Route> {
            override fun createFromParcel(source: Parcel): Route = Route(source)
            override fun newArray(size: Int): Array<Route?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(description)
        dest.writeString(image)
        dest.writeString(city)
        dest.writeString(country)
        dest.writeString(state)
        dest.writeInt(distance)
        dest.writeString(time)
    }
}


