/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function ()
{
    
    
    $("#ricerca").keyup(function ()
    {
        
        var text = $("#ricerca").val();

        $.ajax(
                {
                    url: "filter.json",
                    data: {
                        cmd: "search",
                        q: text
                    },
                    dataType: 'json',
                    success: function (data, state) {
                        
                        updateTable(data);
                    },
                    error: function (data, state) {
                        
                        
                    }
                });

        
        function updateTable(productList)
        {
            
            $("tr").remove();
            $("#searchFail").remove();
            if (productList.length > 0) {
                var newth=$('<tr></tr>');
                newth.append('<th class="clientTabLarge">Foto</th>');
                newth.append(' <th>Nome</th>');
                newth.append('<th>Quantit&agrave;</th>');
                newth.append('<th>Prezzo</th>');
                newth.append('<th>Aggiungi</th>');
                $("table").append(newth);
                for (var i in productList) {
                    
                    var newtr = $('<tr></tr>');
                    
                    newtr.append('<td class="imgCol">'
                            + '<img src="' + productList[i].linkFoto
                            + '" title="' + productList[i].nome
                            + '" alt="Foto ' + productList[i].nome
                            + '" class="objImg">'
                            + '</td>');
                    newtr.append('<td>' + productList[i].nome + '</td>');
                    newtr.append('<td>' + productList[i].quantita + '</td>');
                    newtr.append('<td>' + productList[i].prezzo + ' </td>');
                    newtr.append('<td><a href=Cliente?idProdotto=' + productList[i].id
                            + '><img title="add" alt="aggiungi al carrello" src="./img/tabIcon.png" width="20" height="20"></a></td>');
                    
                    $("table").append(newtr);
                }
            } else {
                
                $("table").after('<p id="searchFail">Nessun oggetto corrispondente alla ricerca </p>');
            }
        }
    });
});
