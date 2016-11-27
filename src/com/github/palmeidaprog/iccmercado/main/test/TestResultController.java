/*
* Mercado de TI
* @author Paulo R. Almeida Filho
* pauloalmeidaf@gmail.com
* */

package com.github.palmeidaprog.iccmercado.main.test;


import com.github.palmeidaprog.iccmercado.main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sun.security.krb5.internal.crypto.Des;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TestResultController {
    @FXML private Label hard, lider, criati, ensino, relaci, pesq, matem,
            logic, resol, desig;
    private Parent root;


    //--Singleton pattern--------------------------------------------------
    private static volatile TestResultController instance = null;

    // constructor
    private TestResultController() { }
    public synchronized static TestResultController getInstance() {
        if(instance == null) {
            instance = new TestResultController();
        }
        return instance;
    }

    // atualiza informações das Labels
    // update info from the labels
    public void update() {
        hard.setText("- Hardware: " + Hardware.getInstance().getStars());
        lider.setText("- Liderança: " + Lideranca.getInstance().getStars());
        criati.setText("- Criatividade: " + Criatividade.getInstance().getStars());
        ensino.setText("- Ensino: " + Ensino.getInstance().getStars());
        relaci.setText("- Relacionamento Interpessoal: " + Relacionamento.getInstance().getStars());
        pesq.setText("- Pesquisa: " + Pesquisa.getInstance().getStars());
        matem.setText("- Matemática: " + Matematica.getInstance().getStars());
        logic.setText("- Lógica e Abstração: " + Logica.getInstance().getStars());
        resol.setText("- Resolução de Problemas: " + Problemas.getInstance().getStars());
        desig.setText("- Design e Artes: " + Design.getInstance().getStars());
    }

    public void showResult() {
        Stage resultWindow = new Stage();

        FXMLLoader loaderResult = new FXMLLoader(getClass().getResource("result_window.fxml"));
        loaderResult.setController(ResultWindowController.getInstance());
        try {
            root = loaderResult.load();
        } catch(IOException e) {
            e.printStackTrace();
        }
        resultWindow.setScene(new Scene(root, 750, 750));

        try {
            Image f = new Image(Main.class.getResourceAsStream("favicon.png"));
            resultWindow.getIcons().add(f);
        } catch(Exception e) { }

        resultWindow.setTitle("Resultado do Teste de Aptidão");
        resultWindow.show();

    }
}
