package mediaplayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 *
 * @author Lumi
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Slider soundSlider;
    
    @FXML
    private Slider seekSlider;
    
    @FXML
    private MediaView mediaView;
    
     
    @FXML
    private Label label;
    
    private MediaPlayer mediaPlayer;
    
    private String filePath;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a file (*.mp4)","*.mp4");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        filePath = file.toURI().toString();
        
        if(filePath!=null){
            Media media = new Media(filePath);
            //label.setText(media.getDuration().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
                DoubleProperty width = mediaView.fitWidthProperty();
                DoubleProperty height = mediaView.fitHeightProperty();
                
                width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
                height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
                
                soundSlider.setValue(mediaPlayer.getVolume()*100);
                soundSlider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(soundSlider.getValue()/100);
                }
             
            });
            
           mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    seekSlider.setValue(newValue.toSeconds());
                }
            });
            
           seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
                    mediaPlayer.play();
                }
            });
                
                
            mediaPlayer.play();
            
        }
    }
    
    @FXML
    private void pauseVideo(ActionEvent evenet)
    {
        mediaPlayer.pause();
    }
    @FXML
    private void playVideo(ActionEvent evenet)
    {
        mediaPlayer.setRate(1);
        mediaPlayer.play();
    }
    @FXML
    private void stopVideo(ActionEvent evenet)
    {
        mediaPlayer.stop();
    }
    @FXML
    private void fastVideo(ActionEvent evenet)
    {
        mediaPlayer.setRate(1.5);
    }
    @FXML
    private void fasterVideo(ActionEvent evenet)
    {
        mediaPlayer.setRate(2);
    }
    @FXML
    private void slowVideo(ActionEvent evenet)
    {
        mediaPlayer.setRate(0.75);
    }
    @FXML
    private void slowerVideo(ActionEvent evenet)
    {
        mediaPlayer.setRate(0.5);
    }
     @FXML
    private void exitVideo(ActionEvent evenet)
    {
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    
    
}
