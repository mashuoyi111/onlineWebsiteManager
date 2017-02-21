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
    Hello, <%=user.getNickname()%>


    <div class="logout">
    <a href="./userlogout.do">
    <button class="btn btn-lg btn-danger btn-block" type="submit">log out</button>
    </a>
    </div>
</div>
</h1>



<h3>
<table class="sidebar">

<tr class="websitesHeading">
    <th>tag</th>
    <th></th>
    <th></th>
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
    <td align="center"><img width="25px" height="25px" src="../pic/modify.ico"> </td>
    <form role="form" name="deleteTag<%=i%>" id="deleteTag<%=i%>" action="./deleteTag.do" method="post">
    <input type="text" class="form-control" name="tagId" id="tagId" value="<%=tags.get(i).getTag_id()%>"  style="display:none;"/>
    <td align="center"><a href="#" onclick="if (confirm('really want to delete this tag? All websites in the tag will be removed!')) document.getElementById('deleteTag<%=i%>').submit(); else return false;"><img width="32px" height="32px" src="../pic/delete.ico"></a> </td>
    </form>
</tr>

<%
}
%>

<tr id="createTag" class="newWebsite" style="display:none;">
<form role="form" name="addTag" action="./insertTag.do" method="post">
<td><input type="text" class="form-control" name="tagName" id="tagName" /></td>
<input type="text" class="form-control" name="userName" id="userName" value="<%=user.getUser_name()%>"  style="display:none;"/>
<td align="center" colspan="2">  <a href="javascript:addTag.submit();"> <img width="40px" height="40px" src="../pic/add.ico"></a></td>
</form>
</tr>

<tr class="newWebsite">
<td align="center" colspan="3"><a id='createTagUrl' href="#" onclick="showOrHide('createTag','createTagUrl')">add new</a></td>
</tr>

</table>
</div>
</h3>


<h2>
<div class="mainContent">
<table class="websites">

<tr class="websitesHeading">
    <th>website name</th>
    <th class="websiteUrl">Link</th>
    <th class="websiteComment">website info</th>
    <th></th>
    <th></th>
  </tr>

<%
for(int i=0;i<websites.size();i++){
    if(websites.get(i).getTag_id()==tag.getTag_id()){
%>

<tr class="websites">
    <td align="center"><%=websites.get(i).getWeb_name()%></td>
    <td align="center"><a href="<%=websites.get(i).getWeb_url()%>" target="_blank" >
    <img width="60px" height="40px" src="../pic/go.ico">
    </a></td>
    <td align="center">
    <% if(websites.get(i).getWeb_comment()!=null && websites.get(i).getWeb_comment().length()>30) {%>
        <%=websites.get(i).getWeb_comment().substring(0,30)+"..."%>
        <%}else{%>
        <%=websites.get(i).getWeb_comment()%>
        <%}%>
    </td>
    <td align="center"><img width="25px" height="25px" src="../pic/modify.ico"> </td>
    <form role="form" name="deleteWeb<%=i%>" id="deleteWeb<%=i%>" action="./deleteWebsite.do" method="post">
    <input type="text" class="form-control" name="webId" id="webId" value="<%=websites.get(i).getWeb_id()%>"  style="display:none;"/>
    <td align="center"><a href="#" onclick="if (confirm('really want to delete this website?')) document.getElementById('deleteWeb<%=i%>').submit(); else return false;"><img width="32px" height="32px" src="../pic/delete.ico"></a> </td>
    </form>
</tr>

<%
}}
%>

<tr class="newWebsite" id="createWebsite" style="display:none;">
<form role="form" name="addWebsite" action="./insertWebsite.do" method="post">
<td><input type="text" class="form-control" name="webName" id="webName" /></td>
<td><input type="text" class="form-control" name="webUrl" value="http://" id="webUrl" /></td>
<td><input type="text" class="form-control" name="webComment" id="webComment" /></td>
<input type="text" class="form-control" name="userName" id="userName" value="<%=user.getUser_name()%>"  style="display:none;"/>
<input type="number" class="form-control" name="tagId" id="tagId" value="<%=tag.getTag_id()%>" style="display:none;"/>
<td align="center" colspan="2">  <a href="javascript:addWebsite.submit();"> <img width="40px" height="40px" src="../pic/add.ico"></a></td>
</form>
</tr>

<tr class="newWebsite">
<td align="center" colspan="5"><a id='createWebsiteUrl' href="#" onclick="showOrHide('createWebsite','createWebsiteUrl')">add new</a></td>
</tr>

</table>
</h2>
</div>


<script type="text/javascript">

function showOrHide(id,urlId){
    var div=document.getElementById(id);
    var div2=document.getElementById(urlId);
    if(div.style.display=='none'){
    div.style.display='table-row';
    div2.innerHTML="cancel";
    }else{
    div.style.display='none';
    div2.innerHTML="add new";
    }
}




</script>

</body>
</html>