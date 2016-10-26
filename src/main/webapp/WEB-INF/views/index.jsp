<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<form:form method="GET" name="redirect" action="/ood/staticPage">
<table>
    <tr>
    <td>
    <input type="submit" value="Get HTML Page" style="display:none;"/>
    </td>
    </tr>
</table>
</form:form>

<script>
document.forms['redirect'].submit();
</script>>
</body>
</html>
