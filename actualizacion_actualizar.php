<?php

include 'conexion_examen.php';

$pdo=new Conexion();

if($_SERVER['REQUEST_METHOD']=='PUT')
{
	$sql="UPDATE actualizacion_inventario SET fechcompra=:fec, id=:idpro ,cantidad=:can WHERE numfactura= :idv";
	$stmt=$pdo->prepare($sql);	
	$stmt->bindValue(':fec',$_GET['fechcompra']);
	$stmt->bindValue(':idv',$_GET['numfactura']);
	$stmt->bindValue(':idpro',$_GET['id']);
	$stmt->bindValue(':can',$_GET['cantidad']);
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