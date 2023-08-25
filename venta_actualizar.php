<?php

include 'conexion_examen.php';

$pdo=new Conexion();

if($_SERVER['REQUEST_METHOD']=='PUT')
{
	$sql="UPDATE ventas_productos  SET  id_producto=:ido, fecha_venta=:fec, cantidad_producto=:can, precio=:prec, nombre_producto=:nomb where id_venta=:ida";
	$stmt=$pdo->prepare($sql);	
	$stmt->bindValue(':ida',$_GET['id_venta']);
	$stmt->bindValue(':ido',$_GET['id_producto']);
	$stmt->bindValue(':fec',$_GET['fecha_venta']);
	$stmt->bindValue(':can',$_GET['cantidad_producto']);
	$stmt->bindValue(':prec',$_GET['precio']);	
	$stmt->bindValue(':nomb',$_GET['nombre_producto']);
	$stmt->execute();
	
        header("HTTP/1.1 200 OK");
        exit;
    } else {
        
        header("HTTP/1.1 404 Not Found");
        exit;
    }


?>
