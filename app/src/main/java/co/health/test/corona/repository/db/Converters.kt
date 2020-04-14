package co.health.test.corona.repository.db

import androidx.room.TypeConverter
import co.health.test.corona.repository.db.entities.AnswerType
import co.health.test.corona.repository.db.entities.QuestionnaireState

class Converters {
    @TypeConverter
    fun fromAnswerType(value: Int): AnswerType {
        return AnswerType.values()[value]
    }

    @TypeConverter
    fun answerTypeToInt(value: AnswerType): Int {
        return value.ordinal
    }

    @TypeConverter
    fun fromQuestionnaireState(value: Int): QuestionnaireState {
        return QuestionnaireState.values()[value]
    }

    @TypeConverter
    fun questionnaireStateToInt(value: QuestionnaireState): Int {
        return value.ordinal
    }
}