package co.health.test.corona.di

import android.content.Context
import co.health.test.corona.repository.db.AppDatabase
import co.health.test.corona.repository.manager.questionnaire.*
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
    single { get<AppDatabase>().behaviorDao() }
    single { QuestionnaireManager(get()) }
    single { UsersManager(get()) }
    single { AnswerManager(get()) }
    single { BehaviorManager(get()) }
    single { QuestionManager(get()) }
    viewModel { QuestionnaireViewModel(get(), get()) }
    viewModel { TestViewModel(get()) }
    single { androidApplication().getSharedPreferences("Corona", Context.MODE_PRIVATE) }
}