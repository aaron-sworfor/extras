<?php

include 'conexion_examen.php';

$pdo=new Conexion();

if ($_SERVER['REQUEST_METHOD']=='DELETE'){
		$sql="DELETE FROM actualizacion_inventario WHERE numfactura=:id";
		$stmt=$pdo->prepare($sql);
	$stmt->bindValue(':id',$_GET['numfactura']);
	$stmt->execute();
	header("HTTP/1.1 200 OK");
	exit;
	}
	header("HTTP/1.1 400 Bad REQUEST_METHOD");
?>