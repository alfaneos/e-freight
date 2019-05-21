package kz.afr.efreight.network.model

class Note : BaseResponse() {
    var id: Int = 0
        internal set
    var note: String? = null
    var timestamp: String? = null
        internal set
}