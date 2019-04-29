<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map,
				 java.util.List,
				 com.slimechan.journal.server.models.schedule.ScheduleSchema,
				 com.slimechan.journal.server.models.schedule.Week,
				 com.slimechan.journal.server.models.schedule.WeekDay" %>
<!DOCTYPE html>
<html>
	<head>
		<!----------- Styles Block -------------->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
		<title>Изменение расписания</title>
	</head>
	<body>
		<div class="schedule-maker" align="center">
		<h1>Изменение расписания для группы ${group}</h1>
		<h2><%	
				Object wT = request.getAttribute("weekType");
				out.println(wT.toString().equals("Even")?"Четная":"Нечетная");
			%> неделя</h2>
		<form action="/admin/schedule/${group}/${weekType}" method="post">
		<table>
			
			<tr>
				<td>Sunday</td>
				<td>Monday</td>
				<td>Tuesday</td>
				<td>Thrusday</td>
				<td>Chetverg</td>
				<td>Friday</td>
				<td>Saturday</td>
			</tr>
			<%
				Map sch = (Map)request.getAttribute("schema");
				if(sch!=null){
				
					for(int id = 1; id<=7; id++){
						out.println("<tr>");
						for(WeekDay day : WeekDay.values()){
						
						List<Integer> list = (List<Integer>)sch.get(day);
							out.println("<td><input type=\"text\" name=\" "+day.toString()+":"+id+" \" value=\""+( 
																														(list!=null?  
																																( list.get(id-1)==-1?"":list.get(id-1) )
																														     :""))+"\"></td>");
						}
						out.println("</tr>");
					}
					
				}else{
					for(int id = 1; id<=7; id++){
						out.println("<tr>");
						for(WeekDay day : WeekDay.values()){
						
							out.println("<td><input type=\"text\" name=\" "+day.toString()+":"+id+" \"></td>");
						}
						out.println("</tr>");
					}
				}
			%>

		</table>
		<input type="submit" value="BAke!">
		</form>
		</div>

	</body>
</html>