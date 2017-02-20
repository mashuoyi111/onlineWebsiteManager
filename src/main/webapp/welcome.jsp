<%@ page import ="com.domain.User" %>
<%@ page import ="com.domain.Tag" %>
<%@ page import ="com.domain.Website" %>
<%@ page import ="java.util.List;" %>
<% User user=(User) request.getAttribute("user");
   List<Tag> tags=(List<Tag>) request.getAttribute("tags");
   List<Website> websites=(List<Website>) request.getAttribute("websites");
   Tag tag=(Tag) request.getAttribute("currentTag");
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Hello, ${message}!</title>

<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/welcome.css" rel="stylesheet">


</head>
<body>

<div class="sidebar">

<h1>
<div class="welcomebar">
    Hi <%=user.getNickname()%>


    <div class="logout">
    <a href="./userlogout.do">
    <button class="btn btn-lg btn-danger btn-block" type="submit">log out</button>
    </a>
    </div>
</div>
</h1>



<h2>
<table class="sidebar">

<tr class="websitesHeading">
    <th>tag</th>
</tr>

<%
for(int i=0;i<tags.size();i++){
%>

<tr class="sidebar">
    <td align="center">
    <a href="./index.do?tagNum=<%=i+1%>">
    <%=tags.get(i).getTag_name()%>
    </a>
    </td>
</tr>

<%
}
%>

</table>
</div>
</h2>


<h2>
<div class="mainContent">
<table class="websites">

<tr class="websitesHeading">
    <th>website Name</th>
    <th class="websiteUrl">website Link</th>
    <th class="websiteComment">website info</th>
  </tr>

<%
for(int i=0;i<websites.size();i++){
    if(websites.get(i).getTag_id()==tag.getTag_id()){
%>

<tr class="websites">
    <td align="center"><%=websites.get(i).getWeb_name()%></td>
    <td align="center"><a href="<%=websites.get(i).getWeb_url()%>" target="_blank" >
    Go!
    </a></td>
    <td align="center">
    <% if(websites.get(i).getWeb_comment()!=null && websites.get(i).getWeb_comment().length()>30) {%>
        <%=websites.get(i).getWeb_comment().substring(0,30)+"..."%>
        <%}else{%>
        <%=websites.get(i).getWeb_comment()%>
        <%}%>
    </td>
</tr>

<%
}}
%>

<tr class="newWebsite"> <td align="center" colspan="3">add new website</td> </tr>

</table>
</h2>
</div>

</body>
</html>