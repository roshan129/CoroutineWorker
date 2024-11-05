package com.roshanadke.coroutineworker.data

class MyRepositoryImpl: MyRepository {
    override suspend fun fetchData(): String {
        return "fetchData Success"
    }
}