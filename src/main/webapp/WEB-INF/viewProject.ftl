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


            #home {
                border: 2px solid #fff;
                padding: 20px;
            }

            #search {
                border: 2px solid #fff;
                padding: 10px;
            }

            #info {
                border: 2px solid #fff;
                padding: 10px;
            }

            #info h2 {
                border: 2px solid #fff;
                padding: 5px;
            }

            #info p {
                border: 2px solid #fff;
                padding: 2px;
            }

            #actions {
                border: 2px solid #fff;
                padding: 10px;
            }

            #actions h2 {
                border: 2px solid #fff;
                padding: 5px;
            }

            #spender {
                border: 2px solid #fff;
                padding: 10px;
            }

            #spender h2 {
                border: 2px solid #fff;
                padding: 5px;
            }

            #spender ul {
              list-style-type: none;
              margin: 0;
              padding: 0;
            }

            #comments {
                border: 2px solid #fff;
                padding: 10px;
            }

            #comments h2 {
                border: 2px solid #fff;
                padding: 5px;
            }

            #comments ul {
              list-style-type: none;
              margin: 0;
              padding: 0;
            }

            #comment {
                border: 2px solid #fff;
                padding: 20px;
            }


        </style>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
            <h1> ProjectFunder Website </h1>
            </div>

            <div>

                <div id="home">
                    <button onclick="window.location.href='home'" >Home</button>
                </div>


                <div id="search">
                    <form method="POST" action="/searchProjekt">
                        <input type="text" name="search">
                        <input type="submit" name="search" value="search">
                    </form>
                </div>


                <div id="info">
                    <h2>Informationen</h2>
                    <p>${projekt.icon}</p>
                    <p>${projekt.titel}</p>
                    <p>von <a href="viewProfile?kennung=${projekt.kennung}">${projekt.benutzername}</a></p>
                    <p>${projekt.beschreibung}</p>
                    <p>Finanzierungslimit: ${projekt.finanzierungslimit}</p>
                    <p>Aktuelle Spendensumme: ${projekt1}</p>
                    <p>Status: ${projekt.status}</p>
                    <p>Vorgänger-Projekt: <a href="viewProject?kennung=${projekt.kennung}">${projekt.vorgaenger1}</a></p>
                </div>

                <div id="actions">
                    <h2>Aktionsleiste</h2>

                        <button id="button" type="submit" name="spenden" onclick="window.location.href='fundProject?kennung=${projekt.kennung}'">Spenden</button>
                        <button  name="loeschen" onclick="#" >Projekt Löschen</button>
                        <button  name="editieren" onclick="window.location.href='editProject?kennung=${projekt.kennung}'" >Projekt Editieren</button>

                </div>

                <div id="spender">
                    <h2>Liste der Spender</h2>
                    <#list spender as spend>
                       <ul>
                          <li>${spend.benutzername} : ${spend.spendenbetrag}</li>
                       </ul>
                    </#list>
                </div>

                <div id="comments">
                    <h2>Kommentare</h2>
                    <#list commentator as comment>
                        <ul>
                            <li>${comment.name} : ${comment.kommentar}</li>
                        </ul>
                    </#list>
                </div>

                <div id="comment">
                    <button onclick="window.location.href='commentProject'">Kommentieren</button>
                </div>
            </div>
        </div>
    </body>
</html>
