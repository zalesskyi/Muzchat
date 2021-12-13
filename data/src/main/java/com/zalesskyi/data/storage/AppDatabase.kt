package com.zalesskyi.data.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.zalesskyi.data.storage.dao.MessagesDao
import com.zalesskyi.data.storage.models.MessageEntity
import com.zalesskyi.domain.models.generateMessageId
import java.util.concurrent.Executors

@Database(entities = [MessageEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        private var instance: AppDatabase? = null

        fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "db")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        prepopulateDatabase()
                    }
                })
                .build().also {
                    instance = it
                }
        }

        private fun prepopulateDatabase() {
            Executors.newSingleThreadExecutor().execute {
                instance?.messagesDao()?.insertAll(generateInitialMessages())
            }
        }

        private fun generateInitialMessages(): List<MessageEntity> =
            listOf(
                MessageEntity(generateMessageId(), 1639037125467, "Hello, good morning", 0),
                MessageEntity(generateMessageId(), 1639037195467, "oh hey, how are you?", 1),
                MessageEntity(generateMessageId(), 1639037196467, "Just got up", 0),
                MessageEntity(generateMessageId(), 1639037225467, "Wow, you're happy", 1),
                MessageEntity(generateMessageId(), 1639037226467, "thanks", 0),
                MessageEntity(generateMessageId(), 1639037325467, "What about the breakfast?", 1),
                MessageEntity(generateMessageId(), 1639037334467, "Would be nice to meet at 12 pm", 1),
                MessageEntity(generateMessageId(), 1639037344467, "what do you think?", 1),
                MessageEntity(generateMessageId(), 1639099124467, "Good idea", 0),
                MessageEntity(generateMessageId(), 1639099125467, "What do you fancy?", 1),
                MessageEntity(generateMessageId(), 1639099126467, "Well", 0),
                MessageEntity(generateMessageId(), 1639139127467, "Just coffee, or do you want to eat?", 0),
                MessageEntity(generateMessageId(), 1639139128467, "Hmm, coffee is enough I think", 1)
            )
    }

    abstract fun messagesDao(): MessagesDao
}