package pt.isel.pdm.firebaseexplorer.data

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import pt.isel.pdm.firebaseexplorer.model.SimpleModel

class FirestoreRepository(
    private val db: FirebaseFirestore
) : TestRepository {

    private val collectionName = "testCollection"

    private val NUMBER_LABEL = "Number"
    private val ARRAY_LABEL = "Array"

    override suspend fun create(m: SimpleModel) {
        db.collection(collectionName).document(m.id).set(
            simpleModelToHashMap(m)
        ).await()
    }

    override suspend fun update(m: SimpleModel) {
        db.collection(collectionName).document(m.id).set(simpleModelToHashMap(m)).await()
    }

    override suspend fun delete(m: SimpleModel) {
        db.collection(collectionName).document(m.id).delete().await()
    }

    override suspend fun getAll(): List<SimpleModel> {

        return db.collection(collectionName).get().await().map {
            documentToSimpleModel(it)
        }
    }


    private fun simpleModelToHashMap(m: SimpleModel): Map<String, Any> {
        return mapOf(
            NUMBER_LABEL to m.number,
            ARRAY_LABEL to m.array
        )
    }

    private fun documentToSimpleModel(obj: DocumentSnapshot): SimpleModel {

        return SimpleModel(
            id = obj.id,
            number = (obj.data!![NUMBER_LABEL] as Long).toInt(),
            array = (obj.data!![ARRAY_LABEL] as List<Long>).map { it.toInt() }
        )
    }

    override suspend fun getById(id: String): SimpleModel {
        val doc = db.collection(collectionName).document(id).get().await()
        return documentToSimpleModel(doc)
    }
}