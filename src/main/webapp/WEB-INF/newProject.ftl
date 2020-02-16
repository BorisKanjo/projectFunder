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

            #title h2 {

                border: 2px solid #fff;
                padding: 5px;
            }

            #create {
                border: 2px solid #fff;
                padding: 10px;
            }

            #create #inputs {
                border: 2px solid #fff;
                padding: 10px;
            }

            #create li {
                 list-style-type: none;
                 margin: 0;
                 padding: 0;
            }

            #create textarea {
                width: 30%;
                height: 100px;
                padding: 6px 10px;
                box-sizing: border-box;
                border: 2px solid #ccc;
                border-radius: 4px;
                background-color: #f8f8f8;
                resize: none;
            }

        </style>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
            <h1> ProjectFunder Website </h1>
            </div>

            <div id="main">

                <div id="home">
                    <button onclick="window.location.href='home'" >Home</button>
                </div>

                <div id="search">
                    <form method="POST" action="/searchProjekt">
                        <input type="text" name="search">
                        <input type="submit" name="search" value="search">
                    </form>
                </div>

                <div id="title">
                   <h2>Projekt Erstellen</h2>
                </div>

                <div id="create">
                    <form method="POST" action="newProject" >
                        <div id="inputs">
                            <label>Titel</label>
                            <input type="text" name="titel" required> <br>
                        </div>

                        <div id="inputs">
                            <label>Finanzierungslimit</label>
                            <input type="number" min="100" name="finanzlimit" required> &euro; <br>
                        </div>

                        <div id="inputs">
                            <label>Kategorie</label><br>
                            <#list projekt1 as proj>
                            		 <li><input type="radio" name="kategorie" value="${proj.id}"> ${proj.name}</li><br>
                            </#list>
                        </div>

                        <div id="inputs">
                            <label>Vorgänger</label><br>
                            <#list projPrev as projP>
                                <input type="radio" name="vorgaenger" value ="${projP.kennung}" required>${projP.titel}<br>
                            </#list> <br>
                            <input type="radio" name="vorgaenger" value = "0">Kein Vorgänger<br>
                        </div>


                        <div id="inputs">
                            <label>Beschreibung</label>
                            <textarea rows="5" cols="50" name="beschreibung" required></textarea><br>
                        </div>

                        <div id="inputs">
                            <input type="submit" value="Erstellen">
                        </div>

                    </form>
                </div>


            </div>
            <div>

            </div>
        </div>
    </body>
</html>
