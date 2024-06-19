<?php
// Conectar a la base de datos
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "mensajeriatapatia";

// Crear conexión
$conn = new mysqli($servername, $username, $password, $dbname);

// Verificar conexión
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

// Verificar si se recibe el parámetro idMensaje
if (isset($_GET['idMensaje'])) {
    $idMensaje = $_GET['idMensaje'];

    // Preparar la consulta SQL para buscar el mensaje por ID
    $sql = "SELECT * FROM mensaje WHERE idMensaje = $idMensaje";

    // Ejecutar la consulta
    $result = $conn->query($sql);

    // Verificar si se encontraron resultados
    if ($result->num_rows > 0) {
        // Crear un array para almacenar los mensajes encontrados
        $messages = array();

        // Iterar sobre los resultados y agregarlos al array
        while ($row = $result->fetch_assoc()) {
            $messages[] = $row;
        }

        // Devolver los mensajes como JSON
        echo json_encode($messages);
    } else {
        // No se encontraron mensajes con el ID especificado
        echo "[]"; // Devolver un array vacío en JSON
    }
} else {
    echo "Error: Falta el parámetro idMensaje";
}

// Cerrar la conexión
$conn->close();
?>
