package kz.afr.efreight.network.service

import io.reactivex.Completable
import io.reactivex.Single
import kz.afr.efreight.network.model.Note
import kz.afr.efreight.network.model.Procedure
import kz.afr.efreight.network.model.User
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path



interface ApiService {
    @GET("list/arrival/{id}")
    fun fetchProceduresById(@Path("id") id: Int): Single<ArrayList<Procedure>>
//
//    @FormUrlEncoded
//    @POST("notes/user/register")
//    fun register(@Field("device_id") deviceId: String): Single<User>
//
//    // Create note
//    @FormUrlEncoded
//    @POST("notes/new")
//    fun createNote(@Field("note") note: String): Single<Note>
//
//
//    // Update single note
//    @FormUrlEncoded
//    @PUT("notes/{id}")
//    fun updateNote(@Path("id") noteId: Int, @Field("note") note: String): Completable
//
//    // Delete note
//    @DELETE("notes/{id}")
//    fun deleteNote(@Path("id") noteId: Int): Completable
}