package pt.isel.pdm.firebaseexplorer.data

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import pt.isel.pdm.firebaseexplorer.model.SimpleModel
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirestoreTestService(
    private val db: FirebaseFirestore
) : TestService {

    private val collectionName = "testCollection"

    private val NUMBER_LABEL = "Number"
    private val ARRAY_LABEL = "Array"

    override suspend fun create(m: SimpleModel) {
        db.collection(collectionName)
            .document(m.id)
            .set(simpleModelToHashMap(m))
            .await()

    }

    override suspend fun update(m: SimpleModel) {
        db.collection(collectionName)
            .document(m.id)
            .update(simpleModelToHashMap(m)).await()
    }

    override suspend fun delete(m: SimpleModel) {
        db.collection(collectionName)
            .document(m.id)
            .delete()
            .await()
    }

    override suspend fun getAll(): List<SimpleModel> {

        return db.collection(collectionName)
            .get()
            .await()
            .map {
                documentToSimpleModel(it)
            }
    }

    override suspend fun getById(id: String): SimpleModel {
        val doc = db.collection(collectionName)
            .document(id)
            .get()
            .await()
        return documentToSimpleModel(doc)
    }

    override suspend fun getInstancesWithNumberBiggerThan(nr: Int): List<SimpleModel> {
        return db.collection(collectionName)
            .whereGreaterThanOrEqualTo(NUMBER_LABEL, nr)
            .get()
            .await()
            .map {
                documentToSimpleModel(it)
            }
    }

    override suspend fun waitForChanges(id: String): SimpleModel {
        return suspendCancellableCoroutine() { continuation ->
            var listener: ListenerRegistration? = null
            listener = db.collection(collectionName)
                .document(id)
                .addSnapshotListener { snapshot, error ->
                    try {
                        if (error != null)
                            continuation.resumeWithException(error)
                        else if (snapshot == null)
                            continuation.resumeWithException(Exception("Object with id:$id cannot be found"))
                        else
                            continuation.resume(documentToSimpleModel(snapshot))
                    } finally {
                        listener!!.remove()
                    }
                }

            continuation.invokeOnCancellation {
                listener?.remove()
            }
        }
    }

    override suspend fun getByIdFlow(id: String): Flow<SimpleModel> {
       TODO("Not yet implemented")
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


}