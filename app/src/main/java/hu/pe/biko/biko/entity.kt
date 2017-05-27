package hu.pe.biko.biko

data class Place(var name: String, var description: String, var address: String,
                 var lat: Double, var lng: Double, var city: String, var country: String, var state: String)

data class Route(var name: String, var description: String,
                 var city: String, var country: String, var state: String, var distance: Int, var time: String)


