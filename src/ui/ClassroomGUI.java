package ui;

import model.Classroom;
import model.UserAccount;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ClassroomGUI {
	
	@FXML
	private BorderPane mainPane;
	 
	@FXML
	private TextField txtUserName;

	@FXML
    private PasswordField passwordField;

    @FXML
    private TextField txtProfilePhoto;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton rbMale;

    @FXML
    private RadioButton rbOther;

    @FXML
    private CheckBox software;

    @FXML
    private CheckBox telematic;

    @FXML
    private CheckBox industrial;

    @FXML
    private DatePicker birthday;

    @FXML
    private ComboBox<String> browser;
    
    @FXML
    private TableView<UserAccount> tableAccList;

    @FXML
    private TableColumn<UserAccount, String> colUserName;

    @FXML
    private TableColumn<UserAccount, String> colGender;

    @FXML
    private TableColumn<UserAccount, String> colCareer;

    @FXML
    private TableColumn<UserAccount, String> colBirthday;

    @FXML
    private TableColumn<UserAccount, String> colBrowser;

    @FXML
    private Label labUserName;
	
	private Classroom classroom;
	

	

	public ClassroomGUI(Classroom cr) {
		this.classroom=cr;
		
	}
	
	public void initializeComboBox() {
		ObservableList<String> options = 
			    FXCollections.observableArrayList("Google Chrome","Mozilla Firefox","Opera", "Safari");
		browser.setValue("Browser");
		browser.setItems(options);
	}
	
	@FXML
    public void logIn(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));

		fxmlLoader.setController(this);
    	Parent accList= fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(accList);
    }

    @FXML
    public void loadRegister(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));

		fxmlLoader.setController(this);
    	Parent register= fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(register);
		
		initializeComboBox();
    }
    
    @FXML
    public void createAccount(ActionEvent event) {
    	
    	String gend="";
		if(rbFemale.isSelected()) {
			gend="Female";
		}
		else if(rbMale.isSelected()) {
			gend="Male";
		}
		else if(rbOther.isSelected()) {
			gend="Other";
		}
		
		String career="";
		if(software.isSelected()) {
			career+="Software engineering";
		}
		if(telematic.isSelected()) {
			career+="Telematic engineering";
		}
		if(industrial.isSelected()) {
			career+="Industrial engineering";
		}
    	
    	if(!txtUserName.getText().equals("") && !passwordField.getText().equals("") && !txtProfilePhoto.getText().equals("") &&
    			!birthday.getValue().equals(null) && browser.getSelectionModel().getSelectedItem().equals("")
    			&& !gend.equals("") && !career.equals("") ){
	    	
    		
    		
    		UserAccount userAcc= new UserAccount(txtUserName.getText(), passwordField.getText(),txtProfilePhoto.getText(),gend, career,birthday.getValue(), browser.getSelectionModel().getSelectedItem());
    	}
    	
    	
    	txtUserName.clear();
    	passwordField.clear();

    }

    @FXML
    public void loadLogIn(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));

		fxmlLoader.setController(this);
    	Parent login= fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(login);
    }
    
    @FXML
    public void selectBrowse(ActionEvent event) {

    }
    
    

}
