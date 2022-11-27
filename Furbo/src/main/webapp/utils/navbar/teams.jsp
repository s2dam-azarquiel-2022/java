<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<form action="." method="post">
  <select class="form-control" name="order" id="order" onchange="this.form.submit()">
    <option value="" selected>Ordenar por</option>
  </select>
</form>
