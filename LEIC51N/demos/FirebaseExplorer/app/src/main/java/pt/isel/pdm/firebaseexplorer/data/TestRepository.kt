package pt.isel.pdm.firebaseexplorer.data

import pt.isel.pdm.firebaseexplorer.model.SimpleModel

interface TestRepository{
    suspend fun create(m : SimpleModel)
    suspend fun update(m : SimpleModel)
    suspend fun delete(m: SimpleModel)
    suspend fun getAll() : List<SimpleModel>
    suspend fun getById(id : String) : SimpleModel
    suspend fun getInstancesWithNumberBiggerThan(nr:Int) : List<SimpleModel>
}