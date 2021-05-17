package agenda.interfaz;

import java.io.File;

import agenda.io.AgendaIO;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
		panel.setCenter(crearPanelBotones());
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
		VBox panel = new VBox();
		panel.setPadding(new Insets(10));
		panel.setSpacing(10);


		txtBuscar = new TextField();
		txtBuscar.setMinHeight(40);
		txtBuscar.setPromptText("Buscar");
		VBox.setMargin(txtBuscar, new Insets(0,0,40,0));


		rbtListarTodo = new RadioButton("Listar toda la agenda");
		rbtListarTodo.setSelected(true);
		

		rbtListarSoloNumero = new RadioButton("Listar nº contactos");
		
		ToggleGroup grupo = new ToggleGroup();
		rbtListarTodo.setToggleGroup(grupo);
		rbtListarSoloNumero.setToggleGroup(grupo);
		
		btnListar = new Button("Listar");
		btnListar.setPrefWidth(250);
		btnListar.getStyleClass().add("botones");
		VBox.setMargin(btnListar, new Insets(0,0,40,0));

		btnPersonalesEnLetra = new Button("Contactos personales en letra");
		btnPersonalesEnLetra.setPrefWidth(250);
		btnPersonalesEnLetra.getStyleClass().add("botones");

		btnPersonalesOrdenadosPorFecha = new Button("Contactos Personales\nordenaos por fecha");
		btnPersonalesOrdenadosPorFecha.setPrefWidth(250);
		btnPersonalesOrdenadosPorFecha.getStyleClass().add("botones");

		btnClear = new Button("Clear");
		btnClear.setPrefWidth(250);
		btnClear.getStyleClass().add("botones");
		VBox.setMargin(btnClear, new Insets(40,0,0,0));

		btnSalir= new Button("Salir");
		btnSalir.setPrefWidth(250);  
		btnSalir.getStyleClass().add("botones");

		panel.getChildren().addAll(txtBuscar, rbtListarTodo, rbtListarSoloNumero, btnListar, btnPersonalesEnLetra, btnPersonalesOrdenadosPorFecha, btnClear, btnSalir);

		return panel;
	}

	private GridPane crearPanelLetras() {
		// a completar
		GridPane panel = new GridPane();
		panel.setMaxSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
		panel.setPadding(new Insets(10));
		panel.setHgap(5);
		panel.setVgap(5);
		int posicion = 0;
		String[] letras = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		for (int i=0;i<letras.length;i++) {
			Button letra = new Button(letras[i]);
			letra.setId("botonletra");
			letra.setMaxSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
			GridPane.setHgrow(letra, Priority.ALWAYS);
			GridPane.setVgrow(letra, Priority.ALWAYS);
			if(i < 14) {
				panel.add(letra, i, 0);
			}
			else{
				panel.add(letra, posicion, 1);
				posicion++;
			}
			
		}
		return panel;
	}

	private MenuBar crearBarraMenu() {
		// a completar
		MenuBar barra = new MenuBar();
		
		Menu menu1 = new Menu("Archivo");
		itemImportar = new MenuItem("Importar agenda");
		itemImportar.setAccelerator(KeyCombination.keyCombination("Ctrl+I"));
		itemImportar.setOnAction(e -> importarAgenda());
		
		itemExportarPersonales = new MenuItem("Exportar Personales");
		itemExportarPersonales.setDisable(true);
		itemExportarPersonales.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));
		
		itemSalir = new MenuItem("Salir");
		itemSalir.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
		itemSalir.setOnAction(e -> salir());
		
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
		int errores = 0;
		FileChooser selector = new FileChooser();
		selector.setTitle("Abrir archivo csv");
		selector.setInitialDirectory(new File("."));
		selector.getExtensionFilters().addAll(new ExtensionFilter("csv", "*.csv"));
		File f = selector.showOpenDialog(null);
		if (f != null) {
			String nombre = f.getName();
			errores = AgendaIO.importar(agenda, nombre);
			areaTexto.setText("Importada agenda\n\nLíneas Erroneas: " + errores);
			
			itemExportarPersonales.setDisable(false);
			itemImportar.setDisable(true);
		}
		
		
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
