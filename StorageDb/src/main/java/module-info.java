module com.storage.storagedb {
    opens com.storage.storagedb.Entity;
    requires org.hibernate.orm.core;
    requires org.postgresql.jdbc;
    requires java.persistence;
    requires java.naming;
    requires java.sql;

    exports com.storage.storagedb.DAO;
    exports com.storage.storagedb.Entity;
}