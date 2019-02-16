# Einarmiger Bandit / One-armed Bandit

Der Einarmiger Bandit ist ein Geldspielautomat. Üblicherweise wurden die Geräte früher mechanisch, später elektromechanisch und heut  
elektronisch betrieben. Nach dem Einwurf von Geld wird an dem Hebel gezogen um das Spiel zu beginnen. Durch Zufall werden die Abbildung 
oder Zahlen der 3 Rollen in einen Zustand gebracht. Ergab die Spielrunde dreimal die gleich Ergebnisse auf den Rollen, hat der Spiele die 
Runde gewonnen. 

## Ziel 

Das Ziel des Projektes ist, dass Spiel am Einarmigen Bandit nachzuempfinden allerdings ohne den Verlust oder Gewinn des eigene Geldes. Über eine 
http REST Schnittstelle wird mit dem in Java implementierten einarmige Banditen interagiert. Durch die Ausführung des Maven Build Prozesses 
soll ein self-contained System erstellt werden. Das self-contained System stellt alle notwendig Komponenten und Dienste bereit. 

## Spielregeln 
Ein Spiel kostet 3 Kredits. Die Maschine hat 3 Räder auf denen jeweils ein Apfel, eine Banane oder eine Clementine erscheinen. Wenn 
zufällig alle drei Räder übereinstimmen gewinnt der Spieler. In diesem Fall werden je nach Obstsorte folgende Gewinnsummen ausgezahlt
 
 - Apfel 10 Kredits
 - Banane 15 Kredits
 - Clementine 20 Kredits
 
Der Spiel kann seinen Einsatz für ein Spiel erhöhen. Für seine Risikofreude wird er im Gewinnfall mit entsprechen mehr Kredits belohnt. 

**Beispiel**: Der Spieler besitzt 10 Kredits im Spiel und setzt 6 Kredits zusätzlich. Wenn der Spiel nun durch 3 Äpfel gewinnt, erhält er durch
den dreifachen Einsatz zum normal Spiel einen Gewinn von 30 Kredits. Insgesamt liegt der Spieler damit bei 37 Kredits. 3 Kredits wurden 
als Spielkosten abgezogen. 

Verliert der Spieler die Runde, werden 9 Kredits für die Runde fällig.  

## Umsetzung 

Die Klasse OneArmedBandit stellt den Kern des Domänen Modells dar. Mit Hilfe von ValueObjects stellt der Bandit seine 
einfache aber bestimmende Schnittstelle bereit. Beispielsweise werden die notwendigen Kredits zum starten übergeben und 
stets auf positive Werte validiert.
Damit der einarmige Bandit über http Methoden angesprochen werden kann wurde der RestController OneArmedBanditController 
erstellt. Die Nutzdaten werden im JSON Format ausgetauscht. 

Um einen parallelen zugriff für verschiedene Benutzer zu ermöglichen, sorgt der OneArmedBanditService dafür das der 
Zustand in der http session des Spieler gespeichert wird.

Der self-contained System Ansatz konnte durch den Einsatz von Spring Boot erreicht werden. In diesem Fall, stellt 
Spring Boot einen kompletten Tomcat Servlet Container bereit um die Zugriff über http zu ermöglichen. 

## Voraussetzung 

Um das one-armed.bandit lokal zu betreiben, ist eine Java 8 Installation notwendig sowie Apache Maven um den Build Prozess ausführen zu können. 
Das ausführbare jar File erzeugt Maven über den Aufruf mvn clean install. Der Port 8080 muss auf dem System frei sein ggf. kann davon 
abgewichen werden durch setzen des CommandLine Parameters -Dserver.port=8181 

## Starten 

Der one-armed.bandit lässt sich über die Kommandozeile über folgenden Befehl starten:

    java -jar one-armed.bandit-1.0.0-SNAPSHOT.jar

## Spielen

Postman collection ! 

### Anmelden 

Der Spieler kann sich am Spielautomaten anmelden mit seinem Namen und den Kredits die er einsetzen möchte.   

#### URL
 
    POST http://localhost:8080/oneArmedBandit/checkin
    
#### Json Request Body 
    
    {
        "player" : "Player One",
    	"credit":{ 
    	        "credits":120
    	    }
    }

#### Response Code
  
- 200 OK - check in erfolgreich 
- 400 Bad Request - Bei ungültigen Eingaben wie negative Kredits 

### Das Glück herausfordern 

Weist der Kontostand des Spielers genügend Kredits auf, kann der Hebel gezogen werden um eine Runde zu spielen 

####  URL 
  
    GET http://localhost:8080/oneArmedBandit/pullingHandle

####  Response Body 
  
     {
         "credit": {
             "credits": 30
         },
         "wheels": [
             "BANANA",
             "BANANA",
             "BANANA"
         ],
         "won": true
     }
  
#### Response Code

 - 200 OK - check in erfolgreich 
 - 400 Bad Request - Wenn kein CheckIn erfolgte oder Kredits nicht ausreichen


### Das Glück herausfordern mit Risiko Einsatz

Weist der Kontostand des Spielers genügend Kredits auf, kann der Spieler unter angaben des zusätzlichen Einsatzes
als URL Parameter ein Risiko Spiel starten. 

#### URL 
  
    GET http://localhost:8080/oneArmedBandit/pullingHandle/6

#### Response Body 
  
     {
         "credit": {
             "credits": 76
         },
         "wheels": [
             "BANANA",
             "BANANA",
             "BANANA"
         ],
         "won": true,
         "riskGame": true
     }
  
#### Response Code

 - 200 OK - check in erfolgreich 
 - 400 Bad Request - Wenn kein CheckIn erfolgte oder Kredits nicht ausreichen
  
### Auszahlen 
 
Möchte der Spieler das Spiel beenden, kann er auschecken und bekommt die verbleibenden Kredits 
ausgezahlt. 
 
#### URL 
 
    GET http://localhost:8080/oneArmedBandit/checkout
    
#### Response Body 
 
    {
        "credits": 42
    }
    
    
#### Response Code
    
- 200 OK - check in erfolgreich 