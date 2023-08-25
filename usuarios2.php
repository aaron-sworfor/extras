<?php

include 'conexion_examen.php';

$pdo = new Conexion();

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    if (isset($_GET['usuario']) && isset($_GET['clave'])) {
        $usuario = $_GET['usuario'];
        $clave = $_GET['clave'];

        $sql = $pdo->prepare("SELECT * FROM tabla WHERE usuario=:usu AND clave=:clv");
        $sql->bindParam(':usu', $usuario);
        $sql->bindParam(':clv', $clave);
        $sql->execute();
        $users = $sql->fetchAll(PDO::FETCH_ASSOC);

        if ($users) {
            header("HTTP/1.1 200 OK");
            echo json_encode($users);
        } else {
            header("HTTP/1.1 401 Unauthorized");
            echo json_encode(["message" => "usuario o clave incorrectos"]);
        }
    } else {
        $sql = $pdo->prepare("SELECT * FROM tabla");
        $sql->execute();
        $users = $sql->fetchAll(PDO::FETCH_ASSOC);

        if ($users) {
            header("HTTP/1.1 200 OK");
            echo json_encode($users);
        } else {
            header("HTTP/1.1 500 Internal Server Error");
            echo json_encode(["message" => "Error en el servidor"]);
        }
    }
} else {
    header("HTTP/1.1 400 Bad Request");
}
?>