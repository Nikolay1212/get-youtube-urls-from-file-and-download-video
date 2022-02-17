<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Urls</title>
</head>
<body>
<h1>Your file contains next youtube-urls</h1>
<table id="urlTable">
    <thead>
    <tr>
        <th>Id</th>
        <th>Url</th>
    </tr>
    </thead>
    <tbody>
    <#list urls as url>
        <a href="/urls/download/all/${fileId}">DownloadAll</a>
        <tr>
            <td>${url.id}</td>
            <td>${url.url}</td>
            <td><a href="/urls/download/${url.id}">Download</a></td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>