<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>курс валют</title>
</head>
<body>
<h1>Курс валют
</h1>
<br/>
Загрузите актуальную информацию в бд, период добавления с 01.01.2020 по 10.01.2020
<form action="/indexServlet" method="get">
    <p><input type="submit" value="загрузить в бд"></p>
</form>
Очистка бд
<form action="/DelServlet" method="get">
    <p><input type="submit" value="очистить базу"></p>
</form>

<p>Результат добавления: ${result}</p>
<p>Максимальный курс за период: ${max}</p>
<p>Средний курс за период: ${avg}</p>
<p>Валюта: ${a} </p>

Выберите валюту:
<form action="/infoExchangeServlet" method="post" name="form1">
    <p><select name="list">
        <option value="USD">Доллар</option>
        <option value="EUR">Євро</option>
        <option value="RUB">Російський рубль</option>
        <option value="AUD">Австралійський долар</option>
        <option>CAD</option>
        <option>CNY</option>
        <option>HRK</option>
        <option>CZK</option>
        <option>DKK</option>
        <option>HKD</option>
        <option>HUF</option>
        <option>INR</option>
        <option></option>

    </select></p>
    <p><input type="submit" value="Отправить"></p>
</form>

</body>
</html>