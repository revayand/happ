package co.health.test.corona.di

import android.content.Context
import android.content.SharedPreferences
import android.os.UserManager
import co.health.test.corona.repository.db.AppDatabase
import co.health.test.corona.repository.manager.questionnaire.AnswerManager
import co.health.test.corona.repository.manager.questionnaire.QuestionManager
import co.health.test.corona.repository.manager.questionnaire.QuestionnaireManager
import co.health.test.corona.repository.manager.questionnaire.UsersManager
import co.health.test.corona.screen.main.questionnaire.QuestionnaireViewModel
import co.health.test.corona.screen.test.TestViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    single { AppDatabase.getInstance(androidApplication()) }
    single { get<AppDatabase>().questionDao() }
    single { get<AppDatabase>().questionnaireDao() }
    single { get<AppDatabase>().usersDao() }
    single { get<AppDatabase>().answerDao() }
    single { QuestionnaireManager(get()) }
    single { UsersManager(get()) }
    single { AnswerManager(get()) }
    single { QuestionManager(get()) }
    viewModel { QuestionnaireViewModel(get(), get()) }
    viewModel { TestViewModel(get()) }
    single { androidApplication().getSharedPreferences("Corona", Context.MODE_PRIVATE) }
}