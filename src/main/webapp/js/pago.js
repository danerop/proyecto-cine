$(document).ready (function(){
    $("#fecha-horario").collapse('show');
    cargarfechas();
});
function cargarfechas(){
    date1 = new Date();
    
    $("label[for='fecha1']").html(pasarDiaAsTRING(date1));
    $("#fecha1").val(pasarDiaAsTRING(date1));
    $("label[for='fecha2']").html(pasarDiaAsTRING(addDaysToDate(date1, 1)));
    $("#fecha2").val(pasarDiaAsTRING(addDaysToDate(date1, 1)));
    $("label[for='fecha3']").html(pasarDiaAsTRING(addDaysToDate(date1, 2)));
    $("#fecha3").val(pasarDiaAsTRING(addDaysToDate(date1, 2)));
    $("label[for='fecha4']").html(pasarDiaAsTRING(addDaysToDate(date1, 3)));
    $("#fecha4").val(pasarDiaAsTRING(addDaysToDate(date1, 3)));
    $("label[for='fecha5']").html(pasarDiaAsTRING(addDaysToDate(date1, 4)));
    $("#fecha5").val(pasarDiaAsTRING(addDaysToDate(date1, 4)));
    $("label[for='fecha6']").html(pasarDiaAsTRING(addDaysToDate(date1, 5)));
    $("#fecha6").val(pasarDiaAsTRING(addDaysToDate(date1, 5)));
}
function addDaysToDate(date, days){
    var res = new Date(date);
    res.setDate(res.getDate() + days);
    return res;
}
function pasarDiaAsTRING(date){
    var dia= date.getDate();
    var mes= date.getMonth()+1;
    var ano= date.getFullYear();
    date=ano +"-"+mes+"-"+dia;
    return date;
}
