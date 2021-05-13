package agenda.interfaz;

import agenda.modelo.AgendaContactos;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuiAgenda extends Application {
	private AgendaContactos agenda;
	private MenuItem itemImportar;
	private MenuItem itemExportarPersonales;
	private MenuItem itemSalir;

	private MenuItem itemBuscar;
	private MenuItem itemFelicitar;

	private MenuItem itemAbout;

	private TextArea areaTexto;

	private RadioButton rbtListarTodo;
	private RadioButton rbtListarSoloNumero;
	private Button btnListar;

	private Button btnPersonalesEnLetra;
	private Button btnPersonalesOrdenadosPorFecha;

	private TextField txtBuscar;

	private Button btnClear;
	private Button btnSalir;

	@Override
	public void start(Stage stage) {
		agenda = new AgendaContactos(); // el modelo

		BorderPane root = crearGui();

		Scene scene = new Scene(root, 1100, 700);
		stage.setScene(scene);
		stage.setTitle("Agenda de contactos");
		scene.getStylesheets().add(getClass().getResource("/application.css")
		                    .toExternalForm());
		stage.show();

	}

	private BorderPane crearGui() {
		BorderPane panel = new BorderPane();
		panel.setTop(crearBarraMenu());
		panel.setCenter(crearPanelPrincipal());
		return panel;
	}

	private BorderPane crearPanelPrincipal() {
		BorderPane panel = new BorderPane();
		panel.setPadding(new Insets(10));
		panel.setTop(crearPanelLetras());

		areaTexto = new TextArea();
		areaTexto.getStyleClass().add("textarea");
		panel.setCenter(areaTexto);

		panel.setLeft(crearPanelBotones());
		return panel;
	}

	private VBox crearPanelBotones() {
		// a completar
		VBox panel = new VBox();

		return panel;
	}

	private GridPane crearPanelLetras() {
		// a completar
		GridPane panel = new GridPane();

		return panel;
	}

	private MenuBar crearBarraMenu() {
		// a completar
		MenuBar barra = new MenuBar();
		
		Menu menu1 = new Menu("Archivo");
		itemImportar = new MenuItem("Importar agenda");
		itemImportar.setAccelerator(KeyCombination.keyCombination("Ctrl+I"));
		
		itemExportarPersonales = new MenuItem("Exportar Personales");
		itemExportarPersonales.setDisable(true);
		itemExportarPersonales.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));
		
		itemSalir = new MenuItem("Salir");
		itemSalir.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
		
		menu1.getItems().addAll(itemImportar, itemExportarPersonales, new SeparatorMenuItem(), itemSalir);
		
		Menu menu2 = new Menu("Operaciones");
		itemBuscar = new MenuItem("Buscar");
		itemBuscar.setAccelerator(KeyCombination.keyCombination("Ctrl+B"));
		
		itemFelicitar = new MenuItem("Felicitar");
		itemFelicitar.setAccelerator(KeyCombination.keyCombination("Ctrl+F"));
		
		menu2.getItems().addAll(itemBuscar, itemFelicitar);
		
		Menu menu3 = new Menu("Help");
		itemAbout = new MenuItem("About");
		itemAbout.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
		
		menu3.getItems().add(itemAbout);
		
		barra.getMenus().addAll(menu1, menu2, menu3);
		
		return barra;
	}

	private void importarAgenda() {
		// a completar

	}

	private void exportarPersonales() {
		// a completar

	}

	/**
	 *  
	 */
	private void listar() {
		clear();
		// a completar

	}

	private void personalesOrdenadosPorFecha() {
		clear();
		// a completar

	}

	private void contactosPersonalesEnLetra() {
		clear();
		// a completar

	}

	private void contactosEnLetra(char letra) {
		clear();
		// a completar
	}

	private void felicitar() {
		clear();
		// a completar

	}

	private void buscar() {
		clear();
		// a completar

		cogerFoco();

	}

	private void about() {
		// a completar

	}

	private void clear() {
		areaTexto.setText("");
	}

	private void salir() {
		Platform.exit();
	}

	private void cogerFoco() {
		txtBuscar.requestFocus();
		txtBuscar.selectAll();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
