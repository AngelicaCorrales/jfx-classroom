package ui;

import model.Classroom;
import model.UserAccount;

import java.io.File;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
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
		browser.setValue("Choose an option");
		browser.setItems(options);
	}
	
	@FXML
    public void logIn(ActionEvent event) throws IOException {
		if(txtUserName.getText().equals("") && passwordField.getText().equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Validation error");
    		alert.setHeaderText(null);
    		alert.setContentText("You must fill each field in the form ");

    		alert.showAndWait();
		}
		else if(classroom.findAccount(txtUserName.getText(), passwordField.getText())){
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));

			fxmlLoader.setController(this);
	    	Parent accList= fxmlLoader.load();
			mainPane.getChildren().clear();
			mainPane.setCenter(accList);
			labUserName.setText(txtUserName.getText());
			File file= new File(classroom.profilePhotoResource(txtUserName.getText(), passwordField.getText()));
			Image image = new Image(file.toURI().toString());
			ImageView imageView=new ImageView(image);
			imageView.setFitHeight(50);
			imageView.setFitWidth(50);
			labUserName.setGraphic(imageView);
			labUserName.setContentDisplay(ContentDisplay.RIGHT);
			initializeTableView();
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Log in incorrect");
    		alert.setHeaderText(null);
    		alert.setContentText("The username and password given are incorrect ");

    		alert.showAndWait();
		}
		
		txtUserName.clear();
    	passwordField.clear();
		
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

	private void initializeTableView() {
    	ObservableList<UserAccount> list= FXCollections.observableArrayList(classroom.getAccounts());
    	
    	tableAccList.setItems(list);
    	colUserName.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("userName"));
    	colGender.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("gender"));
    	colCareer.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("career"));
    	colBirthday.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("birthday"));
    	colBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("browser"));
	
    	
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
			career+="Software engineering\n";
		}
		if(telematic.isSelected()) {
			career+="Telematic engineering\n";
		}
		if(industrial.isSelected()) {
			career+="Industrial engineering\n";
		}
		
    	
    	if(!txtUserName.getText().equals("") && !passwordField.getText().equals("") && !txtProfilePhoto.getText().equals("") &&
    			birthday.getValue()!=null && !browser.getSelectionModel().getSelectedItem().equals("Choose an option")
    			&& !gend.equals("") && !career.equals("") ){
	    	
    		
    		
    		classroom.createAccount(txtUserName.getText(), passwordField.getText(),txtProfilePhoto.getText(),gend, career,birthday.getValue(), browser.getSelectionModel().getSelectedItem());
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Account created");
    		alert.setHeaderText(null);
    		alert.setContentText("The new account has been created! :D");

    		alert.showAndWait();
    		
    		txtUserName.clear();
        	passwordField.clear();
        	txtProfilePhoto.clear();
        	birthday.setValue(null);
        	browser.setValue("Choose an option");
        	
        	software.setSelected(false);
        	telematic.setSelected(false);
        	industrial.setSelected(false);
        	if(!gend.equals("")) {
        		gender.getSelectedToggle().setSelected(false);
        	}
    	}
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Validation error");
    		alert.setHeaderText(null);
    		alert.setContentText("You must fill each field in the form ");

    		alert.showAndWait();
    	}
    	
    	
    	

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
    	FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
    	FileChooser fileChooser= new FileChooser();
    	fileChooser.getExtensionFilters().add(imageFilter);
    	fileChooser.setTitle("Select profile photo");
    	File file= fileChooser.showOpenDialog(null);
    	
    	if(file != null) {
    		txtProfilePhoto.setText(file.getAbsolutePath());
    	}
    }
    
    

}
