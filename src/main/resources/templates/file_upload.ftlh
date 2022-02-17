<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Upload File</title>
</head>
<body>
<form action="/file_upload" method="post" enctype="multipart/form-data">
    Select File to Upload:
    <input type="file" name="file">
    <br>
    <input type="submit" value="Upload">
</form>
</body>
</html>