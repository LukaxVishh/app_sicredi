package com.sicredi.onboarding.data.repository


import com.sicredi.onboarding.data.local.AssociateDao
import com.sicredi.onboarding.data.model.Associate


class AssociateRepository(private val dao: AssociateDao) {
    fun observeAll() = dao.observeAll()
    suspend fun get(id: Long) = dao.getById(id)
    suspend fun add(associate: Associate): Long = dao.insert(associate)
    suspend fun update(associate: Associate) = dao.update(associate)
    suspend fun delete(associate: Associate) = dao.delete(associate)
}