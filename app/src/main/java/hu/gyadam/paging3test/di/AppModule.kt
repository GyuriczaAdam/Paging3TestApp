package hu.gyadam.paging3test.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.gyadam.paging3test.data.local.BeerDatabase
import hu.gyadam.paging3test.data.local.BeerEntity
import hu.gyadam.paging3test.data.remote.BeerApi
import hu.gyadam.paging3test.data.remote.BeerRemoteMediator
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBeerDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context,
        BeerDatabase::class.java,
        "beers.dp"
    ).build()

    @Provides
    @Singleton
    fun provideBeerApi() = Retrofit
        .Builder()
        .baseUrl(BeerApi.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create<BeerApi>()

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideBeerPager(beerDb :BeerDatabase, beerApi: BeerApi) : Pager<Int,BeerEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = BeerRemoteMediator(
                beerDb,
                beerApi
            ),
            pagingSourceFactory = {
                beerDb.dao.pagingSource()
            }
        )
    }

}