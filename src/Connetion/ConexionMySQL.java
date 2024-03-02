/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connetion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 *
 * @author fabian-lugo
 */
public class ConexionMySQL {
    // Librería de MySQL
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // Nombre de la base de datos
    private static final String DATABASE = "my-first-mysql";

    // Host
    private static final String HOSTNAME = "localhost";

    // Puerto
    private static final String PORT = "3306";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE + "?useSSL=false&allowPublicKeyRetrieval=True";

    // Nombre de usuario
    private static final String USERNAME = "root";

    // Clave de usuario
    private static final String PASSWORD = "clavecita1357";

    /**
     * Este método se encarga de establecer la conexión con la base de datos.
     * @return Connection a la base de datos.
     */
    public static Connection conectarMySQL() {
        Connection conn = null;

        try {
            // Cargamos el driver de MySQL
            Class.forName(DRIVER);
            // Establecemos la conexión con la base de datos
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            // Imprimimos el error en caso de no poder establecer la conexión
            e.printStackTrace();
        }

        // Devolvemos la conexión
        return conn;
    }
}
