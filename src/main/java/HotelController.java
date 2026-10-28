import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class HotelController {

    @FXML
    private Label lblInfo;

    @FXML
    private ListView<String> listaHabitaciones;

    @FXML
    private TextField txtNumero;

    @FXML
    private Label lblResultado;

    // numero -> "estado|tipo|capacidad|precio"
    private Map<String, String[]> habitaciones = new HashMap<>();

    @FXML
    public void initialize() {
        cargarXML();
        mostrarDatos();
    }

    private void cargarXML() {
        try {
            InputStream is = getClass().getResourceAsStream("reservas.xml");
            // Permite ejecutar también desde el IDE si el XML está en resources
            if (is == null) {
                is = getClass().getResourceAsStream("/reservas.xml");
            }
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().parse(is);
            doc.getDocumentElement().normalize();

            String nombre = doc.getElementsByTagName("nombre").item(0).getTextContent();
            String ciudad = doc.getElementsByTagName("ciudad").item(0).getTextContent();
            lblInfo.setText("Hotel " + nombre + " (" + ciudad + ") - datos leídos desde reservas.xml");

            NodeList listaHab = doc.getElementsByTagName("habitacion");
            for (int i = 0; i < listaHab.getLength(); i++) {
                Element h = (Element) listaHab.item(i);
                String numero = h.getAttribute("numero");
                String estado = h.getAttribute("estado");
                String tipo = h.getElementsByTagName("tipo").item(0).getTextContent();
                String capacidad = h.getElementsByTagName("capacidad").item(0).getTextContent();
                String precio = h.getElementsByTagName("precioNoche").item(0).getTextContent();
                habitaciones.put(numero.trim(), new String[]{estado, tipo, capacidad, precio});
            }
        } catch (Exception e) {
            lblInfo.setText("Error al leer reservas.xml: " + e.getMessage());
        }
    }

    private void mostrarDatos() {
        listaHabitaciones.getItems().clear();
        for (Map.Entry<String, String[]> e : habitaciones.entrySet()) {
            String num = e.getKey();
            String[] d = e.getValue();
            listaHabitaciones.getItems().add(
                "Hab. " + num + " - " + d[1] + " (" + d[2] + " pers, " + d[3] + " EUR) - " + d[0]);
        }
    }

    @FXML
    private void buscar() {
        String numero = txtNumero.getText().trim();

        if (numero.isEmpty()) {
            lblResultado.setText("Escribe un número de habitación.");
            lblResultado.setStyle("-fx-text-fill: orange;");
            return;
        }

        if (!habitaciones.containsKey(numero)) {
            lblResultado.setText("La habitación " + numero + " no existe.");
            lblResultado.setStyle("-fx-text-fill: purple;");
            return;
        }

        String[] d = habitaciones.get(numero);
        String estado = d[0];
        String texto = "Hab. " + numero + " (" + d[1] + ", " + d[2]
                + " pers, " + d[3] + " EUR/noche): ";

        if (estado.equalsIgnoreCase("libre")
                || estado.equalsIgnoreCase("disponible")) {
            lblResultado.setText(texto + "DISPONIBLE.");
            lblResultado.setStyle("-fx-text-fill: green;");
        } else {
            lblResultado.setText(texto + "OCUPADA.");
            lblResultado.setStyle("-fx-text-fill: red;");
        }
    }
}
