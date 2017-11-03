package com.alhelal.textpad;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * <pre>
 *     author: Md.Inzamam-Ul-Haque
 * </pre>
 */

public class WebUIController implements Initializable
{

    public String keyURL;
    @FXML
    TextField txtURL;
    @FXML
    WebView webView;
    @FXML
    ProgressIndicator progress;
    private WebEngine webEngine;

    WebUIController(String keyURL)
    {
        this.keyURL = keyURL;
    }

    @FXML
    private void goAction(ActionEvent evt)
    {
        webEngine.load(txtURL.getText().startsWith("http://") ? txtURL.getText() : "http://" + txtURL.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        webEngine = webView.getEngine();
        webEngine.locationProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                progress.setVisible(true);
                txtURL.setText(newValue);
            }
        });

        // progress.progressProperty().bind(webEngine.getLoadWorker().progressProperty());

        webEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>()
                {
                    @Override
                    public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState)
                    {
                        if (newState == Worker.State.SUCCEEDED)
                        {
                            // hide progress bar then page is ready
                            progress.setVisible(false);
                        }
                    }
                });
        ;
        txtURL.setText(keyURL);
        webEngine.load(txtURL.getText());
    }
}
