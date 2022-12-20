module com.storage.storageui {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.storage.storageBusiness;

    requires org.controlsfx.controls;
    requires com.storage.storageBusiness;

    opens com.storage.storageui to javafx.fxml;
    exports com.storage.storageui;
}