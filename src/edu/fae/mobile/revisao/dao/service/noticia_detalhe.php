<?php
//Incluindo o arquivo que conecta com o MySQL
require_once("conectar.php");


$sql = "select * from noticia where idNoticia = " .  $_GET['idNoticia'];

//Executando uma consulta
$noticias = mysql_query($sql);
$noticia = mysql_fetch_assoc($noticias);

echo json_encode($noticia);

?>
