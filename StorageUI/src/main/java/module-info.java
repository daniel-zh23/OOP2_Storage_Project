module com.storage.storageui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.persistence;

    requires org.controlsfx.controls;
    requires com.storage.storageBusiness;
    requires com.storage.storagedb;

    opens com.storage.storageui to javafx.fxml;
    exports com.storage.storageui;
}