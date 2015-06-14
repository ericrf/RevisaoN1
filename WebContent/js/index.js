var host = location.origin + location.pathname;

var livro = {};

$.ajaxSetup({
	dataType: "json",
	beforeSend: function(request){ $.mobile.loading('show'); },
	error: function(){ toast("erro ao realizar o operacao") },
	complete: function(){ $.mobile.loading('hide'); },
	crossDomain: true
});

$(document).on("pagecontainerbeforeshow", function(){
	$("form").unbind();
	$("input[type!='submit'], textarea").each(function(){$(this).val("")});
});

$(document).on("pageshow", "#principal", function(){
	$.ajax({
		type: "GET",
		url: host + "livro",
		success: function(response){
			sessionStorage.setItem("livros", JSON.stringify(response.list)) ;
			$("#livros-list").html("");
			$("#livro-list-template").tmpl(response.list).appendTo("#livros-list");
			$("#livros-list").listview("refresh", true);	
		}
	});
});

$(document).on("pageshow", "#livros-reservados", function(){
	$.ajax({
		type: "GET",
		url: host + "livro/reservados",
		success: function(response){
			$("#livros-reservados-list").html("");
			$("#livro-list-template").tmpl(response.list).appendTo("#livros-reservados-list");
			$("#livros-reservados-list").listview("refresh", true);	
		}
	});
});

function salvarLivro(){
	$.ajax({
		type: "POST",
		url: host + "livro/",
		data: $("#livro-inserir form").serialize(),
		success: function(){ $.mobile.changePage("#principal");}
	});
}

$(document).on("pageshow", "#livro-inserir", function(){
	$.ajax({
		type: "GET",
		url: host + "biblioteca/",
		success: function(response){
			var list = response.list;
			$("#livro-inserir select").html("");
			for(i in list){
				var option = document.createElement("option");
				option.setAttribute("value", list[i].id);
				option.innerHTML = list[i].nome;
				$("#livro-inserir select").append(option);
			}
		}
	});
	
	$("form").submit(salvarLivro);
});


$(document).on("pageshow", "#livro-detalhe", function(){
	$("#reservar").removeClass("hidden");
	$('#map_canvas').gmap('clear', 'markers');
	if(sessionStorage.idLivro){
		$.ajax({
			type: "GET",
			url: host + "livro/"+sessionStorage.idLivro,
			success: function(response){
				if(response.livro.reservado) $("#reservar").addClass("hidden");
				$("#livro-detalhe-wrapper").html("");
				$("#livro-detalhe-template").tmpl(response.livro).appendTo("#livro-detalhe-wrapper");
				
				$('#map_canvas').gmap('addMarker', { 'position': response.livro.biblioteca.localizacao, 'bounds': true }).click(function(){
					$('#map_canvas').gmap('openInfoWindow', {'content': response.livro.biblioteca.nome}, this);
				});
			}
		});
		
		$("#reservar").click(function(){
			$.ajax({
				type: "PUT",
				url: host + "livro/"+sessionStorage.idLivro+"/reservar",
				success: function(response){ toast("Reservado"); }
			});
		})
		
		return;
	}
	throw "erro inesperado";
});