package pt.isel.pdm.firebaseexplorer.services

import kotlinx.coroutines.flow.Flow
import pt.isel.pdm.firebaseexplorer.model.SimpleModel

interface TestService {
    suspend fun create(m: SimpleModel)
    suspend fun update(m: SimpleModel)
    suspend fun delete(m: SimpleModel)
    suspend fun getAll(): List<SimpleModel>
    suspend fun getById(id: String): SimpleModel
    suspend fun getInstancesWithNumberBiggerThan(nr: Int): List<SimpleModel>
    suspend fun waitForChanges(id: String): SimpleModel

    fun getByIdFlow(id: String): Flow<SimpleModel>
}