package com.ylabz.windwatersnow.di

import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.ylabz.windwatersnow.network.YelpRepoImp
import com.ylabz.windwatersnow.network.model.MapsAPI
import com.ylabz.windwatersnow.network.model.YelpAPI
import com.ylabz.windwatersnow.network.repo.YelpRepo
import com.ylabz.windwatersnow.network.service.maps.MapsClient
import com.ylabz.windwatersnow.network.service.yelp.ApolloYelpClient
import com.ylabz.windwatersnow.network.service.yelp.apolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApolloClient(
        @ApplicationContext context: Context
    ): ApolloClient {
        return apolloClient(context)
    }

    @Provides
    @Singleton
    fun bindsYelpAPI(
        apolloClient: ApolloClient
    ): YelpAPI {
        return ApolloYelpClient(apolloClient)
    }

    @Provides
    @Singleton
    fun bindsMapsAPI(): MapsAPI {
        return MapsClient()
    }

    @Singleton
    @Provides
    fun bindsYelpRepo(
        yelpApi: YelpAPI
    ): YelpRepo {
        return YelpRepoImp(yelpApi)
    }
}
