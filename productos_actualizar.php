<?php
include 'conexion_examen.php';

$pdo = new Conexion();
 
if ($_SERVER['REQUEST_METHOD'] == 'PUT') {
    $sqlVerificar = "SELECT * FROM productos_inventario WHERE id=:id";
    $stmtVerificar = $pdo->prepare($sqlVerificar);
    $stmtVerificar->bindValue(':id',$_GET['id']);
    $stmtVerificar->execute();

    if ($stmtVerificar->fetchColumn() > 0) {
        
		
        $sql="UPDATE Productos_inventario SET cantidad=cantidad + :can WHERE  id=:id";
	$stmt=$pdo->prepare($sql);	
	$stmt->bindValue(':id',$_GET['id']);
	$stmt->bindValue(':can',$_GET['cantidad']);
	$stmt->execute();

        header("HTTP/1.1 200 OK");
        exit;
    } else {
        
        header("HTTP/1.1 404 Not Found");
        exit;
    }
}else{

header("HTTP/1.1 400 Bad Request");}

?>
