package com.cagataykolus.paymentapp.data.repository

import com.cagataykolus.paymentapp.data.local.dao.ApplicableDao
import com.cagataykolus.paymentapp.data.remote.api.PaymentService
import com.cagataykolus.paymentapp.model.Applicable
import com.cagataykolus.paymentapp.model.PaymentResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Çağatay Kölüş on 27.08.2021.
 * cagataykolus@gmail.com
 */
interface ApplicableRepository {
    fun getAllApplicables(
    ): Flow<Resource<List<Applicable>>>

    fun deleteAllApplicables(
    ): Flow<Resource<List<Applicable>>>
}

/**
 * Singleton repository for fetching data from remote and storing it in database
 * for offline capability. This is single source of data.
 */
@ExperimentalCoroutinesApi
class DefaultApplicableRepository @Inject constructor(
    private val dao: ApplicableDao,
    private val service: PaymentService
) : ApplicableRepository {
    /**
     * Fetched the data from network and stored it in database. At the end, data from persistence
     * storage is fetched and emitted.
     */
    override fun getAllApplicables(
    ): Flow<Resource<List<Applicable>>> {
        return object : NetworkBoundRepository<List<Applicable>, PaymentResult>() {

            override suspend fun saveRemoteData(response: PaymentResult) = dao.addApplicables(response.networks.applicable)

            override fun fetchFromLocal(): Flow<List<Applicable>> = dao.getAllApplicables()

            override suspend fun fetchFromRemote(): Response<PaymentResult> =
                service.getPaymentResult()

        }.asFlow()
    }
    /**
     * Deletes all data.
     */
    override fun deleteAllApplicables(): Flow<Resource<List<Applicable>>> {
        return object : NetworkBoundRepository<List<Applicable>, PaymentResult>() {

            override suspend fun saveRemoteData(response: PaymentResult) = dao.deleteAllApplicables()

            override fun fetchFromLocal(): Flow<List<Applicable>> {
                return dao.getAllApplicables()
            }

            override suspend fun fetchFromRemote(): Response<PaymentResult> {
                return service.getPaymentResult()
            }

        }.asFlow()
    }
}