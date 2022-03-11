package es.magonxesp.elma.shared.infraestructure.hibernate

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.hibernate.boot.Metadata
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistry
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration
import org.hibernate.query.Query
import java.util.logging.Level
import java.util.logging.Logger

class HibernateDatabaseSession {
    private var registry: StandardServiceRegistry? = null
    private var metadata: Metadata? = null
    private var factory: SessionFactory? = null
    private var session: Session? = null

    fun session(block: HibernateDatabaseSession.(session: Session) -> Unit) {
        open()
        block(session!!)
        close()
    }

    fun query(block: HibernateDatabaseSession.(session: Session) -> Query<*>): MutableList<*>? {
        open()
        val query = block(session!!)
        val result = query.resultList
        close()

        return result
    }

    fun transaction(block: HibernateDatabaseSession.() -> Unit) {
        var transaction: Transaction? = null

        try {
            open()
            transaction = session!!.beginTransaction()

            block()
            transaction.commit()
        } catch (exception: Exception) {
            Logger.getGlobal().log(Level.WARNING, exception.message)
            transaction!!.rollback()
        } finally {
            close()
        }
    }

    fun open() {
        registry = StandardServiceRegistryBuilder()
            .configure("config/hibernate/hibernate.xml")
            .applySetting("hibernate.connection.url", System.getenv("ELMA_DB_CONNECTION_URL"))
            .applySetting("hibernate.connection.username", System.getenv("ELMA_DB_USERNAME"))
            .applySetting("hibernate.connection.password", System.getenv("ELMA_DB_PASSWORD"))
            .build()

        metadata = MetadataSources(registry).metadataBuilder.build()
        factory = metadata!!.sessionFactoryBuilder.build()
        session = factory!!.openSession()
    }

    fun close() {
        factory!!.close()
        session!!.close()
    }

    fun save(`object`: Any) {
        session!!.save(`object`)
    }

    fun remove(`object`: Any) {
        session!!.remove(`object`)
    }
}