package com.example.game_project;

import com.example.Model.Heroes.Golem;
import com.example.Model.Heroes.Juggernuat;
import com.example.Model.Heroes.Orc;
import com.example.Model.Heroes.WereWolf;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HeroesController implements Initializable {

    @FXML
    private AnchorPane anch_Heroes;

    @FXML
    private ImageView img_Jakiro;

    @FXML
    private ImageView img_Juggernaut;

    @FXML
    private ImageView img_Razor;

    @FXML
    private ImageView img_Wolf;

    @FXML
    private Text txt_AtJag;

    @FXML
    private Text txt_AtJak;

    @FXML
    private Text txt_AtRaz;

    @FXML
    private Text txt_Atwolf;

    @FXML
    private Text txt_CapJag;

    @FXML
    private Text txt_CapJak;

    @FXML
    private Text txt_CapRaz;

    @FXML
    private Text txt_Capwolf;

    @FXML
    private Text txt_DamageJag;

    @FXML
    private Text txt_DamageJak;

    @FXML
    private Text txt_DamageRaz;

    @FXML
    private Text txt_Damagewolf;

    @FXML
    private Text txt_HealthJag;

    @FXML
    private Text txt_HealthJak;

    @FXML
    private Text txt_HealthRaz;

    @FXML
    private Text txt_Healthwolf;

    @FXML
    private Text txt_MsJag;

    @FXML
    private Text txt_MsJak;

    @FXML
    private Text txt_MsRaz;

    @FXML
    private Text txt_Mswolf;

    @FXML
    private Text txt_RangeJag;

    @FXML
    private Text txt_RangeJak;

    @FXML
    private Text txt_RangeRaz;

    @FXML
    private Text txt_Rangewolf;

    @FXML
    private Text txt_boolJag;

    @FXML
    private Text txt_boolJak;

    @FXML
    private Text txt_boolRaz;

    @FXML
    private Text txt_boolwolf;

    @FXML
    private VBox vbox_Jakiro;

    @FXML
    private VBox vbox_Juggernaut;

    @FXML
    private VBox vbox_Razor;

    @FXML
    private VBox vbox_Wolf;

    @FXML
    void back(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
        Main.primaryStage.setTitle("User Page");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        img_Jakiro.setImage(new Image(Main.class.getResource("Images/jak.png").toString()));
        img_Juggernaut.setImage(new Image(Main.class.getResource("Images/jag.png").toString()));
        img_Razor.setImage(new Image(Main.class.getResource("Images/raz.png").toString()));
        img_Wolf.setImage(new Image(Main.class.getResource("Images/wolf.png").toString()));

        Juggernuat juggernuat=new Juggernuat();
        WereWolf wereWolf=new WereWolf();
        Orc orc =new Orc();
        Golem golem =new Golem();

        txt_DamageJag.setText(txt_DamageJag.getText()+juggernuat.getHeroAttackDamage());
        txt_HealthJag.setText(txt_HealthJag.getText()+juggernuat.getHeroHealth());
        txt_RangeJag.setText(txt_RangeJag.getText()+juggernuat.getRange());
        txt_AtJag.setText(txt_AtJag.getText()+juggernuat.getAttackSpeed());
        txt_MsJag.setText(txt_MsJag.getText()+juggernuat.getMovementSpeed());
        txt_boolJag.setText(txt_boolJag.getText()+juggernuat.isRangeAttack());
        txt_CapJag.setText(txt_CapJag.getText()+juggernuat.getCapacity());

        txt_DamageJak.setText(txt_DamageJak.getText()+ golem.getHeroAttackDamage());
        txt_HealthJak.setText(txt_HealthJak.getText()+ golem.getHeroHealth());
        txt_RangeJak.setText(txt_RangeJak.getText()+ golem.getRange());
        txt_AtJak.setText(txt_AtJak.getText()+ golem.getAttackSpeed());
        txt_MsJak.setText(txt_MsJak.getText()+ golem.getMovementSpeed());
        txt_boolJak.setText(txt_boolJak.getText()+ golem.isRangeAttack());
        txt_CapJak.setText(txt_CapJak.getText()+ golem.getCapacity());

        txt_DamageRaz.setText(txt_DamageRaz.getText()+ orc.getHeroAttackDamage());
        txt_HealthRaz.setText(txt_HealthRaz.getText()+ orc.getHeroHealth());
        txt_RangeRaz.setText(txt_RangeRaz.getText()+ orc.getRange());
        txt_AtRaz.setText(txt_AtRaz.getText()+ orc.getAttackSpeed());
        txt_MsRaz.setText(txt_MsRaz.getText()+ orc.getMovementSpeed());
        txt_boolRaz.setText(txt_boolRaz.getText()+ orc.isRangeAttack());
        txt_CapRaz.setText(txt_CapRaz.getText()+ orc.getCapacity());

        txt_Damagewolf.setText(txt_Damagewolf.getText()+wereWolf.getHeroAttackDamage());
        txt_Healthwolf.setText(txt_Healthwolf.getText()+wereWolf.getHeroHealth());
        txt_Rangewolf.setText(txt_Rangewolf.getText()+wereWolf.getRange());
        txt_Atwolf.setText(txt_Atwolf.getText()+wereWolf.getAttackSpeed());
        txt_Mswolf.setText(txt_Mswolf.getText()+wereWolf.getMovementSpeed());
        txt_boolwolf.setText(txt_boolwolf.getText()+wereWolf.isRangeAttack());
        txt_Capwolf.setText(txt_Capwolf.getText()+wereWolf.getCapacity());


    }
}
