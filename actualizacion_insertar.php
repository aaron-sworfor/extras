<?php

include 'conexion_examen.php';

$pdo=new Conexion();

if($_SERVER['REQUEST_METHOD']=='POST')
{
	$sql="INSERT INTO actualizacion_inventario (fechcompra,numfactura,id,cantidad) VALUES (:fec,:idv,:idpro,:can)";
	$stmt=$pdo->prepare($sql);	
	$stmt->bindValue(':fec',$_POST['fechcompra']);
	$stmt->bindValue(':idv',$_POST['numfactura']);
	$stmt->bindValue(':idpro',$_POST['id']);
	$stmt->bindValue(':can',$_POST['cantidad']);
	$stmt->execute();
	$idPost=$pdo->lastInsertId();
	
	if($idPost)
	{
		header ("http/1.1 200 OK");
		echo json_encode($idPost);
		exit;
	}
} else {
	header("HTTP/1.1 400 Bad REQUEST_METHOD");
}
?>