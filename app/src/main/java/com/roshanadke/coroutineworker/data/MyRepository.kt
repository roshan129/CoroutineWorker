package com.roshanadke.coroutineworker.data

interface MyRepository {
    suspend fun fetchData(): String
}