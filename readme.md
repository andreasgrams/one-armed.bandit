# Einarmiger Bandit / One-armed Bandit

Der Einarmige Bandit ist ein Geldspielautomat, mit drei unabhänging voneinander laufenden Walzen, die verschiedene Symbole zeigen. 
Üblicherweise wurden die Geräte früher mechanisch, später elektromechanisch und werden inzwischen elektronisch betrieben. 
Nach Eingabe des Spieleinsatzes wird an einem seitlichen Hebel gezogen um das Spiel zu beginnen. 
Per Zufall werden die drei rotierenden Walzen zum Stillstand gebracht. 
Ergibt die Stellung der Walzen dreimal das gleiche Symbol ist das Spiel gewonnen und die entsprechende Gewinnsumme wird ausgezahlt.

## Ziel 

Das Ziel des Projektes ist es, das Spiel des Einarmigen Banditen nachzuempfinden, allerdings ohne den Verlust oder Gewinn des eigenen Geldes. 
Über eine http REST Schnittstelle wird mit dem in Java implementierten einarmigen Banditen interagiert. Durch die Ausführung des Maven Build Prozesses 
soll ein self-contained System erstellt werden. Das self-contained System stellt alle notwendigen Komponenten und Dienste bereit. 

## Spielregeln 
Der Einsatz für ein Spiel kostet 3 Kredits. Der Spielautomat hat 3 rotierende Walzen auf denen jeweils ein Apfel, eine Banane oder eine Clementine erscheinen. Wenn durch
Zufall alle drei Symbole übereinstimmen ist das Spiel gewonnen. In diesem Fall werden je nach Obstsorte folgende Gewinnsummen ausgezahlt:
 
 - Apfel 10 Kredits
 - Banane 15 Kredits
 - Clementine 20 Kredits

Der Spieler kann seinen Einsatz für ein Spiel erhöhen. Für seine Risikofreude wird er im Gewinnfall mit entsprechend mehr Kredits belohnt. 

**Beispiel**: Der Spieler besitzt 10 Kredits und setzt im folgenden Spiel 6 Kredits zusätzlich ein. Gewinnt er nun mit drei übereinstimmenden Apfel-Symbolen, 
erhält er durch den dreifachen Einsatz zum normalen Spiel einen Gewinn von 30 Kredits. Insgesamt liegt der Spieler damit bei 31 Kredits. 9 Kredits wurden 
als Spieleinsatz abgezogen. 

Ergeben die Symbole keine Übereinstimmung, sind die 9 Kredits verloren.  

## Umsetzung 

Die Klasse OneArmedBandit stellt den Kern des Domänen Modells dar. Mit Hilfe von ValueObjects stellt der Bandit seine 
einfache aber bestimmende Schnittstelle bereit. Beispielsweise werden die notwendigen Kredits zum Starten übergeben und 
stets auf positive Werte validiert.
Damit der einarmige Bandit über http Methoden angesprochen werden kann, wurde der RestController OneArmedBanditController 
erstellt. Die Nutzdaten werden im JSON Format ausgetauscht. 

Um einen parallelen Zugriff für verschiedene Benutzer zu ermöglichen, sorgt der OneArmedBanditService dafür, dass der 
Zustand in der http session des Spieler gespeichert wird.

Der self-contained System Ansatz konnte durch den Einsatz von Spring Boot erreicht werden. In diesem Fall stellt 
Spring Boot einen kompletten Tomcat Servlet Container bereit um den Zugriff über http zu ermöglichen. 

## Voraussetzung 

Um one-armed.bandit lokal zu betreiben, ist eine Java 8 Installation notwendig, sowie Apache Maven um den Build Prozess ausführen zu können. 
Das ausführbare jar File erzeugt Maven über den Aufruf `mvn clean install`. Der Port 8080 muss auf dem System frei sein ggf. kann davon 
abgewichen werden, durch setzen des CommandLine Parameters mit z.B. Port 8181 `-Dserver.port=8181 `

## Starten 

Der one-armed.bandit lässt sich über die Kommandozeile durch folgenden Befehl starten:

    java -jar one-armed.bandit-1.0.0-SNAPSHOT.jar

## Spielen

### Anmelden 

Der Spieler kann sich am Spielautomaten mit seinem Namen anmelden und die Kredits bestimmen, die er einsetzen möchte.   

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
  
- 200 OK - Check-in erfolgreich 
- 400 Bad Request - Bei ungültigen Eingaben wie negative Kredits 

### Das Glück herausfordern 

Weist der Kontostand des Spielers genügend Kredits auf, kann der Hebel gezogen werden um eine Runde zu starten 

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

 - 200 OK - Check-in erfolgreich 
 - 400 Bad Request - Wenn kein Check-in erfolgte oder Kredits nicht ausreichen


### Das Glück herausfordern mit Risiko Einsatz

Weist der Kontostand des Spielers genügend Kredits auf, kann der Spieler nach Eingabe des zusätzlichen Einsatzes
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

 - 200 OK - Check-in erfolgreich 
 - 400 Bad Request - Wenn kein Check-in erfolgte oder Kredits nicht ausreichen
  
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
    
- 200 OK - Check-in erfolgreich 


## Test mit Postman

[Postman](https://www.getpostman.com) ist ein REST Client der durch seine einfache Bedienung überzeugt und gut im Team benutzt werden kann. Alle 
Requests sind in der Postman Collection `one.armed.bandit.postman_collection.json` abgelegt und können einfach 
importiert und angewendet werden. 