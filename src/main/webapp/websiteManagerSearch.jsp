<%@ page import ="com.domain.User" %>
<%@ page import ="com.domain.Tag" %>
<%@ page import ="com.domain.Website" %>
<%@ page import ="java.util.List;" %>
<% User user=(User) request.getAttribute("user");
   List<Tag> tags=(List<Tag>) request.getAttribute("tags");
   List<Website> websites=(List<Website>) request.getAttribute("websites");
   String search_name=(String) request.getAttribute("web_name");
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Hello, <%=user.getNickname()%>!</title>

<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/welcome.css" rel="stylesheet">


</head>
<body>


<h1>

<div class="logout">
    <a href="./userlogout.do">
    <button class="btn btn-lg btn-danger btn-block" type="submit">log out</button>
    </a>
</div>

<div class="welcomebar">
    ${message}

    <form role="form" name="search" type="submit" action="./search.do" method="get" class="search" >
            <div class="col-lg-8">
            <input type="text" class="form-control" name="web_name" id="web_name" maxlength="15" size="15" placeholder="search website name (global)" required="required"/>
            </div>
            <div class="searchButton">
            <button class="btn btn-primary btn-block" type="submit">search</button>
            </div>
    <form>



</div>
</h1>



<h3>
<table class="sidebar">

<tr class="websitesHeading">
    <th colspan="3">tag</th>
</tr>

<tr class="websitesFav">
    <td align="center" colspan="3">
        <a href="./index.do">
        my favorite
        </a>
        </td>
</tr>



<%
for(int i=0;i<tags.size();i++){
%>

<tr class="sidebar" id="modTag<%=i%>" style="display:none;">
    <form role="form" name="updateTag<%=i%>" action="./updateTag.do" method="post">
    <td><input type="text" class="form-control" name="tagName" value="<%=tags.get(i).getTag_name()%>" id="tagName<%=i%>" maxlength="12" /></td>
    <input type="text" class="form-control" name="tagId" id="tagId" value="<%=tags.get(i).getTag_id()%>"  style="display:none;"/>
    <td align="center"><a href="#" onclick="showOrHideTag('<%=i%>')"><img width="22px" height="22px" src="../pic/cross.ico"></a> </td>
    <td align="center"><a href="javascript:updateTag<%=i%>.submit();"> <img width="30px" height="30px" src="../pic/tick.ico"></a></td>

    </form>
</tr>


<tr class="sidebar" id="shownTag<%=i%>">
    <td align="center">
    <a href="./index.do?tagNum=<%=i+1%>">
    <%=tags.get(i).getTag_name()%>
    </a>
    </td>
    <td align="center"><a href="#" onclick="showOrHideTag('<%=i%>','<%=tags.size()%>')"><img width="25px" height="25px" src="../pic/modify.ico"></a> </td>
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
<td><input type="text" class="form-control" name="tagName" id="tagName" maxlength="12" /></td>
<input type="text" class="form-control" name="userName" id="userName" value="<%=user.getUser_name()%>"  style="display:none;"/>
<td align="center" colspan="2">  <a href="javascript:addTag.submit();"> <img width="40px" height="40px" src="../pic/add.ico"></a></td>
</form>
</tr>

<tr class="newWebsite">
<td align="center" colspan="3"><a id='createTagUrl' href="#" onclick="showOrHide('createTag','createTagUrl')">add new</a></td>
</tr>


</table>
</h3>


<h3>
<div class="mainContent">


<table class="websites">

<tr class="websitesHeading">
    <th>favorite</th>
    <th class="websiteName">website name</th>
    <th class="websiteUrl">Link</th>
    <th class="websiteComment">website info</th>
    <th class="websiteFav">from tag</th>
  </tr>

<%
for(int i=0;i<websites.size();i++){
        String currentTagName="";
        for(int j=0;j<tags.size();j++){
            if(tags.get(j).getTag_id()==websites.get(i).getTag_id()){
            currentTagName=tags.get(j).getTag_name();
            }
        }
%>

<tr class="websites" id="modWeb<%=i%>" style="display:none;">
</tr>



<tr class="websites" id="shownWeb<%=i%>">

    <form role="form" name="favWeb<%=i%>" id="favWeb<%=i%>" action="./favWebsite.do" method="post">
    <input type="text" class="form-control" name="searchPage" id="searchPage" value="<%=search_name%>"  style="display:none;"/>
    <input type="text" class="form-control" name="webId" id="webId" value="<%=websites.get(i).getWeb_id()%>"  style="display:none;"/>
    <% if(websites.get(i).getFav()==1) {%>
        <td align="center"><a href="javascript:favWeb<%=i%>.submit();"><img width="32px" height="32px" src="../pic/fav.ico"></a> </td>
    <%}else{%>
        <td align="center"><a href="javascript:favWeb<%=i%>.submit();"><img width="32px" height="32px" src="../pic/unfav.ico"></a> </td>
    <%}%>
    </form>

    <td align="center"><%=websites.get(i).getWeb_name()%></td>
    <td align="center"><a href="<%=websites.get(i).getWeb_url()%>" target="_blank" >
    <img width="60px" height="40px" src="../pic/go.ico">
    </a></td>
    <td align="center" width="35%">
    <% if(websites.get(i).getWeb_comment()!=null && websites.get(i).getWeb_comment().length()>30) {%>
        <%=websites.get(i).getWeb_comment().substring(0,30)+"..."%>
        <%}else{%>
        <%=websites.get(i).getWeb_comment()%>
        <%}%>
    </td>
    <td align="center"><a href="./index.do?tagId=<%=websites.get(i).getTag_id()%>"><%=currentTagName%></a></td>

</tr>

<%
}
%>


</table>
</h3>



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



function showOrHideTag(id,MaxTag){
    for(var i=0;i<MaxTag;i++){
                if(document.getElementById("shownTag"+i)!=null)
                    if(document.getElementById("shownTag"+i).style.display=='none'){
                    showTag(i);
                    }
        }
    var div=document.getElementById("shownTag"+id);
    var div2=document.getElementById("modTag"+id);
    if(div.style.display=='none'){
    div.style.display='table-row';
    div2.style.display='none';
    }else{
    div.style.display='none';
    div2.style.display='table-row';
    }

}

function showTag(id){
    var div=document.getElementById("shownTag"+id);
    var div2=document.getElementById("modTag"+id);
    div.style.display='table-row';
    div2.style.display='none';
}


</script>

</body>
</html>