<?php

// Conectarse a la base de datos (ajusta estos valores según tu configuración)
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "mensajeriatapatia";

// Crear conexión
$conn = new mysqli($servername, $username, $password, $dbname);

// Verificar la conexión
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
} 

// Consulta SQL para obtener todos los mensajes
$sql = "SELECT idMensaje, emisor, destinatario, mensajero, tipo, contenido, fecha FROM mensaje";

$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // Crear un array vacío para almacenar los mensajes
    $mensajes = array();

    // Iterar sobre los resultados y almacenar cada mensaje en el array
    while($row = $result->fetch_assoc()) {
        $mensaje = array(
            'idMensaje' => $row['idMensaje'],
            'emisor' => $row['emisor'],
            'destinatario' => $row['destinatario'],
            'mensajero' => $row['mensajero'],
            'tipo' => $row['tipo'],
            'contenido' => $row['contenido'],
            'fecha' => $row['fecha']
        );
        // Agregar el mensaje al array de mensajes
        array_push($mensajes, $mensaje);
    }

    // Codificar el array de mensajes a formato JSON y enviarlo
    echo json_encode($mensajes);

} else {
    echo "0 resultados";
}

// Cerrar la conexión
$conn->close();

?>
