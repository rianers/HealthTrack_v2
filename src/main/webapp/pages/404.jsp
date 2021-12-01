<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Error 404</title>
    <style>
        html, body{
            height: 100%;
            width: 100%;
            margin: 0;
        }
        .img-404{
            background-color: #141a37;
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            color: white;
        }
    </style>
</head>
<body >
    <div class="img-404" >
    	<h2>Parece que não tem nada por aqui</h2>
        <img src="<%=request.getContextPath()%>/assets/404.svg" alt="Error 404" >
    </div>
</body>
</html>