<%@ page import="java.io.*,java.util.*" %>
<%
   // Get session creation time.
   Date createTime = new Date(session.getCreationTime());
   // Get last access time of this web page.
   Date lastAccessTime = new Date(session.getLastAccessedTime());
   
   String title = "Welcome Back to my website";
   Integer visitCount = new Integer(0);
   String visitCountKey = new String("visitCount");
   String userIDKey = new String("userID");
   
   Random rand = new Random();
   int number = rand.nextInt((40 - 1) + 1) + 1;
   String userID = new String(Integer.toString(number));
   
   String userEmail = new String("example@gmail.com");

   // Check if this is new comer on your web page.
   if (session.isNew()){
      title = "Welcome to my website";
      session.setAttribute(userIDKey, userID);
      session.setAttribute(visitCountKey,  visitCount);
      session.setAttribute(userEmail, "email"+userID+"@gmail.com");
   } 
   else{
   		visitCount = (Integer)session.getAttribute(visitCountKey);
   		visitCount = visitCount + 1;
   		session.setAttribute(visitCountKey,  visitCount);
   		userID = (String)session.getAttribute(userIDKey);   
   	   	userEmail = (String)session.getAttribute(userEmail);
   	   	
   		if(visitCount == 5)
   			session.invalidate();
   }   
%>
<html>
<head>
<title>Session Tracking</title>
</head>
<body>
<center>
<h1>Session Tracking</h1>
</center>
<table border="1" align="center"> 
<tr bgcolor="#949494">
   <th>Session info</th>
   <th>Value</th>
</tr> 
<tr>
   <td>id</td>
   <td><% out.print( session.getId()); %></td>
</tr> 
<tr>
   <td>Creation Time</td>
   <td><% out.print(createTime); %></td>
</tr> 
<tr>
   <td>Time of Last Access</td>
   <td><% out.print(lastAccessTime); %></td>
</tr> 
<tr>
   <td>User ID</td>
   <td><% out.print(userID); %></td>
</tr> 
<tr>
   <td>Number of visits</td>
   <td><% out.print(visitCount); %></td>
</tr> 
<tr>
	<td>User Email</td>
	<td><% out.print(userEmail); %></td>
</tr>
</table> 
</body>
</html>