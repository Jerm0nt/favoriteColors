# FavoriteColor
Meine Lösung für die Assecor-Coding-Challenge. Ich habe Spring Boot benutzt. Die Farben sind als `Colors.java` Enum repräsentiert. Die Personen können aus dem vorgegebenen .csv-File eingelesen werden oder von einer H2-Datenbank.


## Akzeptanzkriterien

- [x] **1. Die CSV Datei wurde eingelesen, und wird programmintern durch eine dem Schema entsprechende Modellklasse repräsentiert.** 
- Die CSV-Datei wird in `PersonServiceCSV.java` in der init()-Methode zu Programmstart eingelesen. Von da an arbeitet das System mit einer ArrayList als Datengrundlage.
- Die Personen werden durch die `Personen.java`-Klasse repräsentiert.

- [x] **2. Der Zugriff auf die Datensätze so abstrahiert, dass eine andere Datenquelle angebunden werden kann, ohne den Aufruf anpassen zu müssen.**
- Eine andere Datenquelle kann im `PersonConfiguration.java` angepasst werden. Dafür muss der return-Wert der Person-Service-Bean angepasst werden.

- [x] **3. Die oben beschriebene REST-Schnittstelle wurde implementiert und liefert die korrekten Antworten.** 
- Der `PersonController.java` dient als REST-Schnittstelle für die geforderten GET-Anfragen.

- [x] **4. Der Zugriff auf die Datensätze, bzw. auf die zugreifende Klasse wird über Dependency Injection gehandhabt.**
- Dies passiert über das `PersonConfiguration.java`-File in der Person-Service-Bean. der PersonController initialisiert ein Service vom Typ `PersonServie.java` welcher als Interface realisiert ist und nimmt im Konstruktor die implementierende Klasse entgegen. Standartmäßig ist das im Moment `PersonServiceCSV.java`. Es steht aber auch `PersonServiceH2.java` zur Verfügung.

- [x] **5.  Die REST-Schnittstelle ist mit Unit-Tests getestet.**
- Der Controller ist auf alle 3 geforderten GET-Requests mit Unit-Tests getestet. Diese befinden sich in `PersonController.java`. Es handelt sich dabei um insgesam 12 Test (4 pro Request).

- [x] **6.  Die `sample-input.csv` wurde nicht verändert**
- Check

## Bonuspunkte
- [x] * Implementierung als MSBuild Projekt für kontinuierliche Integration auf TFS (C#/.NET) oder als Maven/Gradle Projekt (Java)
- Das System ist als Maven-Projekt implementiert.

- [ ] * Implementieren Sie eine zusätzliche Methode POST/ Personen, die eine zusätzliche Aufzeichnung zur Datenquelle hinzufügen


- [x] * Anbindung einer zweiten Datenquelle (z.B. Datenbank via Entity Framework)
- Ich habe als zweite Datenquelle eine lokale H2-Datenbank etabliert. Diese wird zu Programmstart erschaffen durch die `schema.sql`-Datei und den entsprechenden Einstellungen in der `application.properties`. Befüllt wird sie mit den Daten aus der Ursprungs .csv durch `data.sql`.
- Um die H2-Datenbank zu nutzen, muss im `PersonConfiguration.java` in der Person-Service-Bean der Rückgabe-Wert auf die entsprechende Klasse geändert werden.
