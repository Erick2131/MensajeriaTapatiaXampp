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

$sql = "SELECT idDestinatario, nombre FROM destinatario";
$result = $conn->query($sql);

$destinatarios = array();
if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $destinatarios[] = $row;
    }
}

echo json_encode($destinatarios);
$conn->close();

?>
