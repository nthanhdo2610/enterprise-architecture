<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h1>Error</h1>
        500: Internal Server Error has occurred, see the log for details
        <button id="back">Back</button>
        <script>
            document.getElementById("back").onclick = function() {
                window.history.back();
            };
        </script>
    </body>
</html>
