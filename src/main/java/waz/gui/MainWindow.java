package waz.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import waz.Waz;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Waz waz;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image wazImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    /**
     * Initializes the controller class.
     * <p>
     * This method is automatically called by the JavaFX framework after the FXML file
     * has been loaded and all @FXML fields have been injected. It performs the following tasks:
     * <ul>
     *   <li>Adds an initial greeting message from Waz to the dialog container.</li>
     *   <li>Automatically scrolls the ScrollPane to the bottom whenever new messages are added.</li>
     * </ul>
     * The greeting message is displayed using the {@link DialogBox#getWazDialog(String, Image)} method.
     */
    @FXML
    public void initialize() {
        // Automatically greet the user
        String greeting = "Hello I'm Waz.\n" + "What can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getWazDialog(greeting, wazImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Waz instance */
    public void setWaz(Waz w) {
        waz = w;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Waz's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = waz.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getWazDialog(response, wazImage)
        );
        userInput.clear();

        // Close app if input is "bye"
        if (input.equalsIgnoreCase("bye")) {
            // Delay exit by 1 second so the user can see the message
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(e -> Platform.exit());
            delay.play();
        }
    }
}
