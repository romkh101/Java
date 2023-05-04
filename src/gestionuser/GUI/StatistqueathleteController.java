/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * FXML Controller class
 *
 * @author DELL
 */
public class StatistqueathleteController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private Label home_totalEnrolled;
     @FXML
private PieChart  pie;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         homeDisplayTotalEnrolledStudents();
         
    }    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    public void homeDisplayTotalEnrolledStudents() {

    String sql = "SELECT  COUNT(CASE WHEN age < 30 THEN 1 END) AS athletes_under_30, COUNT(CASE WHEN age > 30 THEN 1 END) AS athletes_over_30 FROM athlete";

    connect = database.connectDb();

    try {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();

        while (result.next()) {
            String label = "Athletes over 30";
            int value = result.getInt("athletes_over_30");
            pieChartData.add(new PieChart.Data(label, value));
            label = "athlete under 30";
            value = result.getInt("athletes_under_30") - value;
            pieChartData.add(new PieChart.Data(label, value));
        }

        pie.setData(pieChartData);

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
