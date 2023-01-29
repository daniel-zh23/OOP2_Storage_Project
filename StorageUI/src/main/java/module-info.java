module com.storage.storageui {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.storage.storageBusiness;

    requires org.controlsfx.controls;
    requires java.logging;

    opens com.storage.storageui to javafx.fxml;
    exports com.storage.storageui.Controllers;
    opens com.storage.storageui.Controllers to javafx.fxml;
    exports com.storage.storageui.Controllers.Contracts;
    opens com.storage.storageui.Controllers.Contracts to javafx.fxml;
    opens com.storage.storageui.classes to javafx.fxml;
    exports com.storage.storageui;
}