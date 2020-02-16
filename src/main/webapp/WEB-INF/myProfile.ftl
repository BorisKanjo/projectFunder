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
                    <h1>Profil von ${userInfo.email}</h1>
                    <p>Benutzername: ${userInfo.name}</p>
                    <p>Anzahl erstelller Projekt: ${anz}</p>
                    <p>Anzahl unterstützter Projekte: ${unter}</p>
                </div>

                <div id="project">
                    <h2>Erstellte Projekte</h2>
                      <table id="projects">
                          <thead>
                              <tr>
                                  <th>Icon</th>
                                  <th>Titel</th>
                                  <th>Aktuell</th>
                                  <th>Status</th>
                              </tr>
                          </thead>

                          <tbody>
                              <#list erstProj as erstP>
                                   <tr>
                                       <td>${erstP.icon}</td>
                                       <td><a href="viewProject?kennung=${erstP.kennung}">${erstP.titel}</a></td>
                                       <td>${erstP.spendensumme}</td>
                                       <td>${erstP.status}</td>
                                   </tr>
                              </#list>
                          </tbody>
                      </table>
                </div>

                <div id="project">
                    <h2>Unterstützte Projekte</h2>
                      <table id="projects">
                          <thead>
                              <tr>
                                  <th>Icon</th>
                                  <th>Titel</th>
                                  <th>Finanzierungslimit</th>
                                  <th>Status</th>
                                  <th>Gespendet</th>
                              </tr>
                          </thead>

                          <tbody>
                              <#list untProj as untP>
                                   <tr>
                                       <td>${untP.icon}</td>
                                       <td><a href="viewProject?kennung=${untP.kennung}">${untP.titel}</a></td>
                                       <td>${untP.finanzierungslimit}</td>
                                       <td>${untP.status}</td>
                                       <td>${untP.gespendet}</td>
                                   </tr>
                              </#list>
                          </tbody>
                      </table>
                </div>
            </div>
        </div>
    </body>
</html>
