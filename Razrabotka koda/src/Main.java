package com.example.ogarkovbook;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Optional;

public class Main extends Application {
    private PhoneBook phoneBook;

    public void start(Stage primaryStage) {
        phoneBook = new PhoneBook();
        primaryStage.setTitle("Вход в записную книжку");

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Аутентификация");
        dialog.setHeaderText("Введите номер телефона");
        dialog.setContentText("Номер:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String phoneNumber = result.get();
            if (phoneBook.numberExists(phoneNumber)) {
                String password = phoneBook.getPassword(phoneNumber);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Аутентификация");
                alert.setHeaderText(null);
                alert.setContentText("Пароль для входа: " + password);
                alert.showAndWait();

                NotesApp notesApp = new NotesApp();
                notesApp.start(new Stage());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);

                alert.setContentText("Клиента с таким номером не существует");
                alert.showAndWait();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}