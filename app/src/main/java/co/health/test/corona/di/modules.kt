package co.health.test.corona.di

import co.health.test.corona.repository.db.AppDatabase
import org.koin.dsl.module

val modules = module {
    single { AppDatabase.getInstance(get()) }
    single { get<AppDatabase>().questionDao() }
    single { get<AppDatabase>().questionnaireDao() }
}