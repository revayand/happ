package co.health.test.corona.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.health.test.corona.repository.db.daos.QuestionDao
import co.health.test.corona.repository.db.daos.QuestionnaireDao
import co.health.test.corona.repository.db.entities.Question
import co.health.test.corona.repository.db.entities.Questionnaire


@Database(entities = [Questionnaire::class, Question::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun questionDao(): QuestionDao
    abstract fun questionnaireDao(): QuestionnaireDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "Corona.db"
            )
                .build()
    }
}