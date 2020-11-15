<%--
  Created by IntelliJ IDEA.
  User: alemh
  Date: 15.11.2020
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<%@ include file = "components/header.jsp" %>

<center>
    <div class="container mt-5">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <select id="searchCAT">
                    <option value="cc">Choose Category</option>
                    <option value="Show_All">Show All</option>
                    <option value="Show_Certain">Show Certain</option>
                    <option value="By_Group">By Group</option>
                    <option value="By_Major">By Major</option>
                    <option value="By_Year">By Year</option>
                </select>
            </div>
            <input id="searchBar" class="form-control" disabled type="text">
            <input type="button" id="submit" value="Search">
        </div>
    </div>
    <div id="output">
    </div>
</center>
</body>
<script>
    $("#searchCAT").change(function () {
        if ($("#searchCAT").val() === 'Show_All') {
            $("#searchBar").attr('disabled', 'disabled').val('');
            $("#submit").attr('disabled', 'disabled')
            $('#output').empty();
            $.ajax({
                url: "/rest/search/Show_All",
                type: 'GET',
                dataType: "json",
                success: function (data) {
                    var table = $('<table class="table table-bordered">').appendTo($('#output'));
                    $('<tr/>').appendTo(table)
                        .append($('<th/>').text("Student ID"))
                        .append($('<th/>').text("Student Name"))
                        .append($('<th/>').text("Student Surname"))
                        .append($('<th/>').text("Student Email"))
                        .append($('<th/>').text("Student Major"))
                        .append($('<th/>').text("Student Group"))
                        .append($('<th/>').text("Student Year"))
                    data.forEach(function (x,i) {
                        var stat = data[i];
                        $('<tr/>').appendTo(table)
                            .append($('<td/>').text(stat.id))
                            .append($('<td/>').text(stat.first_name))
                            .append($('<td/>').text(stat.last_name))
                            .append($('<td/>').text(stat.email))
                            .append($('<td/>').text(stat.major.major_name))
                            .append($('<td/>').text(stat.group_name))
                            .append($('<td/>').text(stat.year))
                    });
                    console.log(data);
                    $("output").html(data);
                }
            });
        } else if ($("#searchCAT").val() === 'cc') {
            $("#searchBar").attr('disabled', 'disabled').val('');
            $("#submit").attr('disabled', 'disabled')
            $('#output').empty();
        } else {
            $("#searchBar").removeAttr('disabled');
            $("#submit").removeAttr('disabled');
            $('#output').empty();
        }
    });

    $("#submit").click(function (){
        event.preventDefault();
        var search_cat = $("#searchCAT").val();
        var search_val = $("#searchBar").val();
        console.log("/rest/search/"+search_cat)
        $('#output').empty();
        $.ajax({
            url: "/rest/search/"+search_cat,
            type: 'GET',
            dataType: "json",
            data: {
                search_val : search_val
            },
            success: function (data) {
                console.log(data);
                var table = $('<table>').appendTo($('#output'));
                $('<tr/>').appendTo(table)
                    .append($('<th/>').text("Student ID"))
                    .append($('<th/>').text("Student Name"))
                    .append($('<th/>').text("Student Surname"))
                    .append($('<th/>').text("Student Email"))
                    .append($('<th/>').text("Student Major"))
                    .append($('<th/>').text("Student Group"))
                    .append($('<th/>').text("Student Year"))
                data.forEach(function (x,i) {
                    var stat = data[i];
                    $('<tr/>').appendTo(table)
                        .append($('<td/>').text(stat.id))
                        .append($('<td/>').text(stat.first_name))
                        .append($('<td/>').text(stat.last_name))
                        .append($('<td/>').text(stat.email))
                        .append($('<td/>').text(stat.major.major_name))
                        .append($('<td/>').text(stat.group_name))
                        .append($('<td/>').text(stat.year))
                });
            }
        });
    });
</script>
</html>
<%--Fixed--%>