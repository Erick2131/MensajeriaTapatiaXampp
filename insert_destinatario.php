<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "mensajeriatapatia";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

$nombre = $_POST['nombre'];
$direccion = $_POST['direccion'];

$sql = "INSERT INTO destinatario (nombre, direccion) VALUES ('$nombre', '$direccion')";

if ($conn->query($sql) === TRUE) {
    echo json_encode(array("mensaje" => "Destinatario insertado correctamente"));
} else {
    echo json_encode(array("mensaje" => "Error: " . $sql . "<br>" . $conn->error));
}

$conn->close();
?>
