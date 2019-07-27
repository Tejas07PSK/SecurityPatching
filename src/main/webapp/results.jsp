<%--
  Created by IntelliJ IDEA.
  User: palashsarkar
  Date: 27/07/19
  Time: 1:51 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="org.json.simple.parser.ParseException" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Results Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <style>
        #resDiv {
            margin-top: 5%;
            display: block;
            text-align: justify-all;
        }
    </style>
</head>
<body>

<div style="margin-top: 2%">
    <span class="col-sm-4"></span>
    <a href="/login.html" class="col-sm-4 btn btn-primary">Logout</a>
    <span class="col-sm-4"></span>
</div>

<% int flag = 0; JSONObject jo = null; JSONArray jarr = null;
    try {
        jo = ((JSONObject) ((new JSONParser()).parse((request.getAttribute("response_body")).toString())));
    } catch (ParseException pe) { flag = 1; out.println(pe); }
    if (flag == 0) {
%>

        <div id="resDiv" class="container">
            <br/>
            <br/>
            <% if (((String)(jo.get("STATUS_CODE"))).equals("-1")) { %>
                <h2 style="color: orangered"><% out.print(((String)(jo.get("RESPONSE")))); %></h2>
                <hr>
            <% } else {
                jo = (JSONObject)(jo.get("RESPONSE"));
                jarr = (JSONArray) (new JSONParser()).parse((String)(jo.get("PEND_LIST")));
                if (((String)(jo.get("PEND_STAT"))).equals("1")) {
            %>
                    <h2 style="color: midnightblue">You registration is yet to be approved.</h2>
                    <hr>
            <% } else { %>
                    <h4 style="color: darkgreen"><span style="color: midnightblue">Serial Number : </span><% out.print(((String)(jo.get("USER_SLNO")))); %></h4>
                    <h4 style="color: darkgreen"><span style="color: midnightblue">Id : </span><% out.print(((String)(jo.get("USER_ID")))); %></h4>
                    <h4 style="color: darkgreen"><span style="color: midnightblue">Role : </span><% out.print(((String)(jo.get("USER_ROLE")))); %></h4>
                    <h4 style="color: darkgreen"><span style="color: midnightblue">Firstname : </span><% out.print(((String)(jo.get("FIRST_NAME")))); %></h4>
                    <h4 style="color: darkgreen"><span style="color: midnightblue">Lastname : </span><% out.print(((String)(jo.get("LAST_NAME")))); %></h4>
                    <h4 style="color: darkgreen"><span style="color: midnightblue">Email-Id : </span><% out.print(((String)(jo.get("EMAIL_ID")))); %></h4>
                    <h4 style="color: darkgreen"><span style="color: midnightblue">Mobile Number : </span><% out.print(((String)(jo.get("MOB_NO")))); %></h4>
                    <h4 style="color: darkgreen"><span style="color: midnightblue">Password : </span><% out.print(((String)(jo.get("PASSWORD")))); %></h4>
                    <br/>
                    <br/>
                    <hr>
                    <% if (jarr.size() > 0) { %>
                            <h2 style="text-align: left">Pending User Registration Approvals</h2>
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Sl. No.</th>
                                        <th>User Id</th>
                                        <th>Firstname</th>
                                        <th>Lastname</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for (int i = 0 ; i < jarr.size() ; i++) { %>
                                        <tr>
                                            <td><% out.print((String)(((JSONObject)(jarr.get(i))).get("PUSER_SLNO"))); %></td>
                                            <td><% out.print((String)(((JSONObject)(jarr.get(i))).get("PUSER_ID"))); %></td>
                                            <td><% out.print((String)(((JSONObject)(jarr.get(i))).get("PUSER_FNAME"))); %></td>
                                            <td><% out.print((String)(((JSONObject)(jarr.get(i))).get("PUSER_LNAME"))); %></td>
                                            <td><button type="submit" class="btn btn-danger apv">Approve</button></td>
                                        </tr>
                                    <% } %>
                                </tbody>
                            </table>
                    <% } %>
               <% } %>
            <% } %>
            <br/>
            <br/>
        </div>
<%}%>
<script type="text/javascript" src="scripts/res.js"></script>

</body>
</html>
