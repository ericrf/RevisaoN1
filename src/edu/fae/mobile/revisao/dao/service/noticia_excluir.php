<?php
//Incluindo o arquivo que conecta com o MySQL
require_once("conectar.php");


$sql = "delete from noticia where idNoticia = " .  $_GET['idNoticia'];

mysql_query($sql);

?>
{
	"mensagem": "Noticia excluida com sucesso"
}