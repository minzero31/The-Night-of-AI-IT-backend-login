module com.thenightof.aiit.thenightofaiitlogin {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.thenightof.aiit.thenightofaiitlogin to javafx.fxml;
    exports com.thenightof.aiit.thenightofaiitlogin;
}