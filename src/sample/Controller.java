package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class Controller {


    @FXML
    private TextField name_text;
    @FXML
    private Button hello_button;
    @FXML
    private Button bye_button;
    @FXML
    private CheckBox ourCheckBox;
    @FXML
    private Label ourLabel;

    @FXML
    public void handleChange()
    {
        System.out.println("The checkbox is "+(ourCheckBox.isSelected()?"checked":"not checked"));
    }

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

        Runnable task=new Runnable() {
            @Override
            public void run() {
                try{
                    String s=Platform.isFxApplicationThread()?"UI Thread":"Background Thread";
                    System.out.println("I'm going to sleep on the: "+s);
                    Thread.sleep(10000);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            String s=Platform.isFxApplicationThread()?"UI Thread":"Background Thread";
                            System.out.println("I'm updating the label on the: "+s);
                            ourLabel.setText("We did something");
                        }
                    });

                } catch (InterruptedException event){
                    //we don't care about that right now
                }
            }
        };

        new Thread(task).start();
        if(ourCheckBox.isSelected()){
            name_text.clear();
            hello_button.setDisable(true);
            bye_button.setDisable(true);
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
