<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>Hello World!</title>
</head>
<body>
  <s:form beanclass="helloworld.HelloActionBean" focus="">
    <table>
      <tr>
        <td>Enter your name:</td>
        <td><s:text name="name" /></td>
      </tr>
      <tr>
        <td colspan="2"><s:submit name="helloname" value="Click" /></td>
      </tr>
      <tr>
        <td>Hello ${actionBean.name}!</td>
      </tr>
    </table>
  </s:form>
</body>
</html>