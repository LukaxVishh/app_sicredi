package com.sicredi.onboarding


import android.app.Application
import com.sicredi.onboarding.data.local.AppDatabase
import com.sicredi.onboarding.data.repository.AssociateRepository


class App : Application() {
    lateinit var repository: AssociateRepository
        private set


    override fun onCreate() {
        super.onCreate()
        val db = AppDatabase.get(this)
        repository = AssociateRepository(db.associateDao())
    }
}