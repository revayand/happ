package co.health.test.corona.di

import co.health.test.corona.repository.db.AppDatabase
import co.health.test.corona.repository.manager.questionnaire.QuestionnaireManager
import co.health.test.corona.screen.main.questionnaire.QuestionnaireViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    single { AppDatabase.getInstance(androidApplication()) }
    single { get<AppDatabase>().questionDao() }
    single { get<AppDatabase>().questionnaireDao() }
    single { QuestionnaireManager(get()) }
    viewModel { QuestionnaireViewModel(get()) }
}