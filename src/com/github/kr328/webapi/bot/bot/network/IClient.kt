package com.github.kr328.webapi.bot.bot.network

import com.github.kr328.webapi.bot.bot.network.markup.Markup
import com.github.kr328.webapi.bot.bot.network.updates.File
import com.github.kr328.webapi.bot.bot.network.updates.Message
import com.github.kr328.webapi.bot.bot.network.updates.Update
import com.github.kr328.webapi.bot.bot.network.updates.UserProfilePhotos
import retrofit2.http.*

interface IClient {
    @GET("getUpdates")
    suspend fun getUpdates(
        @Query("offset") offset: Long,
        @Query("timeout") timeout: Long = 60
    ): Response<List<Update>>

    @GET("getFile")
    suspend fun getFile(
        @Query("file_id") fileId: String
    ): Response<File>

    @GET("getUserProfilePhotos")
    suspend fun getUserProfilePhotos(
        @Query("user_id") userId: Long,
        @Query("offset") offset: Long?,
        @Query("limit") limit: Long?
    ): Response<UserProfilePhotos>

    @FormUrlEncoded
    @POST("sendMessage")
    suspend fun sendMessage(
        @Field("chat_id") chatId: Long,
        @Field("text") text: String,
        @Field("reply_to_message_id") replyToMessageId: Long?,
        @Field("reply_markup") replyMarkup: Markup?,
        @Field("parse_mode") parseMode: String?
    ): Response<Message>

    @FormUrlEncoded
    @POST("answerCallbackQuery")
    suspend fun answerCallbackQuery(
        @Field("callback_query_id") callbackQueryId: String,
        @Field("text") text: String?,
        @Field("show_alert") showAlert: Boolean?,
        @Field("url") url: String?,
        @Field("cache_time") cacheTime: Int?
    )

    @FormUrlEncoded
    @POST("deleteMessage")
    suspend fun deleteMessage(
        @Field("chat_id") chatId: Long,
        @Field("message_id") messageId: Long
    )

    @FormUrlEncoded
    @POST("editMessageText")
    suspend fun editMessageText(
        @Field("chat_id") chatId: Long,
        @Field("message_id") messageId: Long,
        @Field("text") text: String,
        @Field("parse_mode") parseMode: String?,
        @Field("reply_markup") replyMarkup: Markup?
    ): Response<Message>
}