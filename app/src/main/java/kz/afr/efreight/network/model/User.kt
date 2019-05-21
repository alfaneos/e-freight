package kz.afr.efreight.network.model

import com.google.gson.annotations.SerializedName

class User : BaseResponse() {

    @SerializedName("api_key")
    var apiKey: String? = null
        internal set
}