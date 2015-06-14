<?php

//Nome ou IP da máquina onde está rodando o MySQL
$hostname = "localhost";
//Usuário de conexão com o banco de dados
$user = "root";
//Senha do usuário de conexáo com o banco de dados
$password = "";

//Conecta com um banco de dados MySQL
$conexao = mysql_connect($hostname, $user, $password);

//Selecionando o banco de dados aula_db
$database = "mobile4";
mysql_select_db($database);

mysql_set_charset('utf8', $conexao); 


?>