module com.storage.storageui {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.storage.storageBusiness;

    requires org.controlsfx.controls;

    opens com.storage.storageui to javafx.fxml;
    exports com.storage.storageui;
}