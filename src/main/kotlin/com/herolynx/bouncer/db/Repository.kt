package com.herolynx.bouncer.db

import com.querydsl.jpa.impl.JPAQuery
import org.funktionale.option.Option
import org.funktionale.tries.Try

interface RepositoryFactory {

    /**
     * Get basic repository.
     * Note: it has be closed manually after usage.
     *
     * @return new instance
     */
    fun repository(): ReadRepository

    /**
     * Get repository and make operations on it in single transaction.
     * Note: all resources will be closed automatically.
     *
     * @param operation operation to be performed on repository
     * @return result of operation
     */
    fun <T> transactional(operation: (WriteRepository) -> T): Try<T>

}


interface ReadRepository {

    fun <T, Id> find(clazz: Class<T>, id: Id): Try<Option<T>>

    fun <T> query(query: (JPAQuery<Any>) -> T): Try<T>

}

interface WriteRepository : ReadRepository {

    fun <T> save(entity: T): Try<T>

    fun <T, Id> delete(clazz: Class<T>, id: Id): Try<Option<T>>

}