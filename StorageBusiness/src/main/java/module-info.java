module com.storage.storageBusiness {

    requires com.storage.storagedb;
    requires com.google.common;
    requires javafx.base;

    exports com.storage.storageBusiness.Services;
    exports com.storage.storageBusiness.Models;
    exports com.storage.storageBusiness.Models.Contracts;
}