/*
* Mercado de TI
* @author Paulo R. Almeida Filho
* pauloalmeidaf@gmail.com
* */

package com.github.palmeidaprog.iccmercado.main;

import com.github.palmeidaprog.iccmercado.main.test.Choices;
import com.github.palmeidaprog.iccmercado.main.test.TestResultController;
import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML protected Label tituloLbl;
    @FXML protected VBox testVB, homeVB, educacaoVB, mainPanel, vbox1, leiVB;
    @FXML private BorderPane mainWindow;
    @FXML protected BorderPane mainLeiVB;
    @FXML private int currentMenu = 1;
    private boolean blinkFlag;
    private boolean disableEffect = false;
    private String tituloAntigo = "Mercado de Trabalho de TI";

    // navigation panels / painéis de navegação
    @FXML protected VBox mainEducVB, mainTesteVB;
    @FXML public VBox navigationBox, resultTest;
    //@FXML protected BorderPane testPanel;
    @FXML public BorderPane testPanel;
    @FXML private Button startBtn;
    public Stage dError = new Stage();


    //effects / efeitos
    private UIEffects effects;

    //--Singleton-constructor--------------------------------------------------------

    private static volatile Controller instance = null;
    private Controller() { }
    public synchronized static Controller getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    /*public synchronized static void setInst(Controller c) {
        instance = Controllers.getInstance().getController();
    }*/

    //--Initialization/Inicialização--------------------------------------------------

    public void initialize(URL u, ResourceBundle rb) {
        effects = UIEffects.getInstance();
        //instance = this;
        System.out.println(effects.toString()); // @debug
        System.out.println(this.toString()); // @debug

        // manually load FXML into result to properly use Singleton controller
        // Carregar FXML manualmente para fazer uso de Singleton no Controller.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("test/result_test.fxml"));
        loader.setController(TestResultController.getInstance());
        try {
            resultTest = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void closeAction() {
        Main.primaryStage.close();
    }

    //--Mouse Click Events/Eventos de cliques de mouse---------------------------------

    // method called by VBox's Click action event
    // method chamado por Evento de clique nas VBoxes
    private void clickAction(Pane menu, Pane newPanel, String antigo) {
        effects.normalColors();
        menu.setStyle("-fx-background-color: #52596b");
        tituloAntigo = antigo;
        tituloLbl.setText(tituloAntigo);
        effects.fadeTrans(newPanel);
        effects.trans(newPanel);
    }

    public void homeClickAction() {
        if(currentMenu != 1) {
            clickAction(homeVB, mainPanel, "Mercado de Trabalho de TI");
            mouseExitAction();
            currentMenu = 1;
        }
    }

    public void testClickAction() {
        if(currentMenu != 2) {
            clickAction(testVB, mainTesteVB, "Teste de Aptidão");
            mouseExitAction();
            Choices.getInstance().resetTest();
            currentMenu = 2;
        }
    }

    public void educacaoClickAction() {
        if(currentMenu != 3) {
            clickAction(educacaoVB, mainEducVB, "Educação para TI");
            mouseExitAction();
            currentMenu = 3;
        }
    }

    public void leiClickAction() {
        if(currentMenu != 4) {
            clickAction(leiVB, mainLeiVB, "Leis sobre Mercado de TI");
            mouseExitAction();
            currentMenu = 4;
        }
    }

    //--Mouse Enter and Exit Events/Eventos de sobre mouse e saida de mouse------------------

    // mouse exit menu at the left event
    // evento de saida de mouse do eventos da esquerda
    public void mouseExitAction() {
        disableEffect = true;
        tituloLbl.setText(tituloAntigo);
        tituloLbl.setEffect(null);
        effects.addBlur(false);
        effects.fadeAnim();
        effects.opacityFull();
    }

    private void mouseEnterAction() {

    }

    public void leiMouseEnterAction() {
        if(currentMenu != 4) {
            disableEffect = false;
            tituloLbl.setText("Leis sobre Mercado de TI");
            //leiVB.setOpacity(0.8);
            effects.addBlur(true);
            effects.addReflecGlow(tituloLbl);
            effects.trans(tituloLbl);
            effects.fadeAnim(leiVB, tituloLbl);
        }
    }

    public void testeMouseEnterAction() {
        if(currentMenu != 2) {
            disableEffect = false;
            tituloLbl.setText("Teste de Aptidão");
            //anim.start();
            //testVB.setOpacity(0.8);
            effects.addBlur(true);
            effects.addReflecGlow(tituloLbl);
            effects.trans(tituloLbl);
            effects.fadeAnim(testVB, tituloLbl);
        }
    }

    public void homeMouseEnterAction() {
        if(currentMenu != 1) {
            disableEffect = false;
            tituloLbl.setText("Mercado de Trabalho de TI");
            //homeVB.setOpacity(0.8);
            effects.addBlur(true);
            effects.addReflecGlow(tituloLbl);
            effects.trans(tituloLbl);
            effects.fadeAnim(homeVB, tituloLbl);
        }
    }

    public void educacaoMouseEnterAction() {
        if(currentMenu != 3) {
            disableEffect = false;
            tituloLbl.setText("Educação para TI");
            //educacaoVB.setOpacity(0.8);
            effects.addBlur(true);
            effects.addReflecGlow(tituloLbl);
            effects.trans(tituloLbl);
            effects.fadeAnim(educacaoVB, tituloLbl);

        }
    }

    //--Test Controller Methods---------------------------------------------------

    public void startTest() {
        effects.fadeTrans(testPanel);
        effects.trans(testPanel);
    }

    protected Node getMainNode() {
        return navigationBox.getChildren().get(1);
    }

    //--Others methods-------------------------------------------------------------

    public void disable(boolean b) {
        navigationBox.setDisable(b);
        vbox1.setDisable(b);
    }

    /*Event to Click on window when disabled
    * Evento de quando a janela estiver desabilitada e for clicada*/
    public void disabledEvent() {
        if(navigationBox.isDisabled()) {
            dError.requestFocus();
        }
    }
}
