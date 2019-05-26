package kz.afr.efreight.network.model

data class Procedure(
    var procedure_id: Int,
    var flight_number: String,
    var flight_date: String,
    var awbs: ArrayList<String>
)