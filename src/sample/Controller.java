package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class Controller {


    @FXML
    private TextField name_text;
    @FXML
    private Button hello_button;
    @FXML
    private Button bye_button;

    @FXML
    public void initialize(){
        hello_button.setDisable(true);
        bye_button.setDisable(true);
    }


    @FXML
    public void onButtonClicked(ActionEvent e){

        if(e.getSource().equals(hello_button)){
            System.out.println("Hello, "+name_text.getText());
        }
        else if(e.getSource().equals(bye_button)){
            System.out.println("Bye, "+name_text.getText());
        }
        else{
            System.out.println("Dunno what to say");
        }
    }
    @FXML
    public void handleKeyReleased(){
        String text=name_text.getText();
        boolean disabledButton=text.isEmpty() || text.trim().isEmpty();
        hello_button.setDisable(disabledButton);
        bye_button.setDisable(disabledButton);
    }


}
