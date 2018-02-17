package ${package}.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ${package}.entity.Contact;
import ${package}.service.ContactService;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Date: 27.08.15
 * Time: 11:10
 *
 * @author Ruslan Molchanov (ruslanys@gmail.com)
 * @author http://mruslan.com
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
public class MainController {

    @SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired 
    private ContactService contactService;

    @FXML private TableView<Contact> table;
    @FXML private TextField txtName;
    @FXML private TextField txtPhone;
    @FXML private TextField txtEmail;

    // Variables
    private ObservableList<Contact> data;

    @FXML
    public void initialize() {
    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        List<Contact> contacts = contactService.findAll();
        data = FXCollections.observableArrayList(contacts);

        TableColumn<Contact, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Contact, String> nameColumn = new TableColumn<>("name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Contact, String> phoneColumn = new TableColumn<>("phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Contact, String> emailColumn = new TableColumn<>("E-mail");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.getColumns().setAll(idColumn, nameColumn, phoneColumn, emailColumn);

        table.setItems(data);
    }

    @FXML
    public void addContact() {
        Contact contact = new Contact(txtName.getText(), txtPhone.getText(), txtEmail.getText());
        contactService.save(contact);
        data.add(contact);

        txtName.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
    }
}
