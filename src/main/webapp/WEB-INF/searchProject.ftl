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

            #project h2 {
                border: 2px solid #fff;
                padding: 5px;
            }

            #projects {
                  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                  border-collapse: collapse;
                  width: 100%;
                }

            #projects td, #projects th {
              border: 1px solid #ddd;
              padding: 8px;
            }

            #projects tr:nth-child(even){background-color: #f2f2f2;}

            #projects tr:hover {background-color: #ddd;}

            #projects th {
              padding-top: 12px;
              padding-bottom: 12px;
              text-align: left;
              background-color: #4CAF50;
              color: white;
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

                <div id="search" >
                    <form method="GET" action="/searchProjekt">
                        <input type="text" name="search">
                        <input type="submit" name="search" value="search">
                    </form>
                </div>

                <div id="title">
                   <h2> Suchergebnisse</h2>
                </div>

                <div id="project">
                <h2>Projekte</h2>
                <table id="projects">
                   <thead>
                       <tr>
                          <th>Icon</th>
                          <th>Titel</th>
                          <th>Benutzername</th>
                          <th>Spendensumme</th>
                       </tr>
                   </thead>

                   <tbody>
                      <#list projekt as proj>
                          <tr>
                             <td>${proj.icon}</td>
                             <td><a href="viewProject?kennung=${proj.kennung}">${proj.titel}</a></td>
                             <td><a href="viewProfile?kennung=${proj.kennung}">${proj.benutzername}</a></td>
                             <td>${proj.spendensumme}</td>
                          </tr>
                      </#list>
                   </tbody>
                </table>
            </div>


            </div>
            <div>

            </div>
        </div>
    </body>
</html>
