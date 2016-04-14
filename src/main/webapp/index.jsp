<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>It's something</title>
    </head>
    <body>
        <form method="POST" action="Servlet" enctype="multipart/form-data">
			<input type="file" name="file" />
            <input type="submit" value="upload" />
        </form>
    </body>
</html>
