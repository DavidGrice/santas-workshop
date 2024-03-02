# santas-workshop
Postgres, Springboot, Angular/React, with Python, 3JS and other frameworks as needed


https://corretto.aws/downloads/latest/amazon-corretto-11-x64-windows-jdk.msi

https://start.spring.io/

springboot dev tools, postgres driver, lombok and spring web

create pgadmin folder in users/grice_d/*
add environment variable %PostgresSQL% then in path do %PostgresSQL%
save
add maven_home and maven_home_bin to the environment and user variables. Also do JAVA_HOME

open powershell
postgres
initdb -D C:\Users\GRICE_D\pgdata -U postgres
pg_ctl -D C:\Users\GRICE_D\pgdata start
pg_ctl -D C:\Users\GRICE_D\pgdata stop

netstat -ano | findstr :5432
tasklist /FI "PID eq 16032"

Press the Windows key to open the Start menu.
Type "cmd" into the search bar.
Right-click on "Command Prompt" in the search results.
Select "Run as administrator".
taskkill /F /PID 16032
net stop postgresql

angular
npm install -g @angular/cli