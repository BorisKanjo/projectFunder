<html>
    <head>
        <meta charset="UTF-8">
        <title>ProjectFunder</title>
        <style type="text/css">
            * {
               margin:0;
               padding:0;
            }

            body{
               text-align:center;
               background: #efe4bf none repeat scroll 0 0;
            }

            #wrapper{
               width:960px;
               margin:0 auto;
               text-align:left;
               background-color: #fff;
               border-radius: 0 0 10px 10px;
               padding: 20px;
               box-shadow: 1px -2px 14px rgba(0, 0, 0, 0.4);
            }

            #header{
             color: #fff;
             background-color: #2c5b9c;
             height: 3.5em;
             padding: 1em 0em 1em 1em;

            }

            #site{
                background-color: #fff;
                padding: 20px 0px 0px 0px;
            }
            .centerBlock{
                margin:0 auto;
            }
        </style>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
            <h1> ProjectFunder Website </h1>
            </div>

            <div>
                <div>
                    <h2>${projekt.titel}</h2>

                    <form method="POST" action="commentProject" >
                        <textarea rows="5" cols="50" placeholder="Schreibe Kommentar" value="comment" required></textarea><br>

                        <input type="radio" name="anonymity" value="privat" >Anonym Kommentieren?<br>

                        <input type="submit" value="Kommentar hinzufügen">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
