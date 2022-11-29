module StorageDb {
    exports src.UserManager;
    opens src.Entity;
    requires org.hibernate.orm.core;
    requires org.postgresql.jdbc;
    requires java.persistence;
    requires java.naming;
    requires java.sql;
}