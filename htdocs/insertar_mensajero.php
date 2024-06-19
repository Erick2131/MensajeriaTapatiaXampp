<?php

if (isset($_POST['empresa']) && isset($_POST['vehiculo']) && isset($_POST['valoraciones'])) {
    
    $idEmpresa = $_POST['idEmpresa'];
    $empresa = $_POST['empresa'];
    $vehiculo = $_POST['vehiculo'];
    $valoraciones = $_POST['valoraciones'];
    
    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "mensajeriatapatia";
    
    $conn = new mysqli($servername, $username, $password, $dbname);
    
    if ($conn->connect_error) {
        die("Conexión fallida: " . $conn->connect_error);
    } 
    
    $empresa = $conn->real_escape_string($empresa);
    $vehiculo = $conn->real_escape_string($vehiculo);
    $valoraciones = $conn->real_escape_string($valoraciones);

    $sql = "INSERT INTO mensajero (empresa, vehiculo, valoraciones) VALUES ('$empresa', '$vehiculo', '$valoraciones')";
    
    if ($conn->query($sql) === TRUE) {
        echo "Mensajero insertado correctamente";
    } else {
        echo "Error al insertar el mensajero: " . $conn->error;
    }
    
    $conn->close();
    
} else {
    echo "Error: Faltan datos del mensajero";
}
?>
