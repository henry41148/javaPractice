<%@ page import ="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Personal information</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #f8f8f8">

<div class="container-fluid p-5">

    <div class="row rounded border p-3">

        <div class="col-md-8">
            <h4 class="text-success">Personal Information</h4>
            <table class="table table-striped">
                <tbody>
                <tr>
                    <td>Name</td>
                    <td><%=request.getAttribute("name")%></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><%=request.getAttribute("email")%></td>
                </tr>
                <tr>
                    <td>Birthday</td>
                    <td><%=request.getAttribute("birthday")%></td>
                </tr>
                <tr>
                    <td>Birthtime</td>
                    <td><%=request.getAttribute("birthtime")%></td>
                </tr>
                <tr>
                    <td>Gender</td>
                    <td><%=request.getAttribute("gender")%></td>
                </tr>
                <tr>
                    <td>Nationality</td>
                    <td><%=request.getAttribute("country")%></td>
                </tr>
                <tr>
                    <td>Favorite IDE</td>
                    <%
                        List<String> ides = (List)request.getAttribute("ides");

                    %>
                    <td>
                        <%
                            for (String ide : ides) {
                        %>
                            <%=ide%> &emsp;
                        <%
                            }
                        %>
                    </td>
                </tr>
                <tr>
                    <td>TOEIC Score</td>

                    <td><%=request.getAttribute("toeic")%></td>
                </tr>
                <tr>
                    <td>Description</td>
                    <td><%=request.getAttribute("description")%></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
