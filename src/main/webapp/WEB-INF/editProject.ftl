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
                    <form>
                        <input type="text" name="search">
                        <input type="submit" name="search" value="search">
                    </form>
                </div>

                <div id="title">
                   <h2>Projekt Editieren</h2>
                </div>

                <div id="create">
                    <form method="POST" action="viewProject" >
                        <div id="inputs">
                            <label>Titel</label>
                            <input type="text" name="titel" value="${project.titel}" required> <br>
                        </div>

                        <div id="inputs">
                            <label>Finanzierungslimit</label>
                            <input type="number" min="100" name="finanzlimit" value="${project.finanzierungslimit}" required> &euro; <br>
                        </div>

                        <div id="inputs">
                            <label>Kategorie</label>
                            <input type="radio" name="kategorie" value="Health & Wellness" >Health &amp; Wellness <br>
                            <input type="radio" name="kategorie" value="Art & Creative Works">Art &amp; Creative Works <br>
                            <input type="radio" name="kategorie" value="Education">Education <br>
                            <input type="radio" name="kategorie" value="Tech & Innovation">Tech &amp; Innovation <br>
                        </div>

                        <div id="inputs">
                            <label>Vorgänger</label>
                            <#list projPrev as projP>
                                <input type="radio" name="project" value ="${projP.titel}" >${projP.titel}<br>
                            </#list> <br>
                            <input type="radio" name="project">Kein Vorgänger<br>
                        </div>


                        <div id="inputs">
                            <label>Beschreibung</label>
                            <textarea rows="5" cols="50" name="beschreibung" value="${project.beschreibung}" required></textarea><br>
                        </div>

                        <div id="inputs">
                            <input type="submit" value="Aktualisieren">
                        </div>

                    </form>
                </div>


            </div>
            <div>

            </div>
        </div>
    </body>
</html>