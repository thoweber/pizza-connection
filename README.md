# Hands-on Design Patterns
***The Pizza Connection***

## Kapitel 06 - eine neue Herausforderung
### Szenario
Der Rubel rollt💰, aber mit mehr Verkäufen steigen auch die Anforderungen.

Das Management hat beschlossen, dass es Zeit wird zu skalieren📈 und hat den Auftrag gegeben, unseren Pizza-Store in eine Client-Server-Anwendung umzubauen👷‍♂️ Die Wahl viel dabei auf _Spring Boot_ ❤

Außerdem sind neue Anforderungen vom Einkauf🛒 hinzugekommen: Weil wir an manchen Tagen so viel 🍕 verkaufen, muss der Einkauf jederzeit einen Überblick über die verbrauchten Zutaten erhalten können, damit schnell reagiert werden kann.

Und es kam wie es kommen musste: einiges hat beim Umbau nicht geklappt🤦‍♀️, und deshalb müssen jetzt Experten ran😁

Deshalb verschafft euch erst einmal einen 👓Überblick...

### Was ist neu?

* Logausgaben landen in der Datei `pizza-store.log`

* PizzaStoreApp:
    * Läuft jetzt als Spring `CommandLineRunner`
    * Erstellt eine Bestellung via REST und nutzt nicht mehr den PizzaService
    * Empfangene Bestellung wird mit ID ausgegeben
    * Konsolen IO nach `Console` ausgelagert

* REST-Schnittstelle `de.infoteam.course.dp.pizzastore.controller.PizzaController`
    * REST-Mapping zur Bestellungsannahme
    * Stub einer Abfrage-Schnittstelle der verbrauchten Zutaten

* Pizza:
    * jede Pizza hat jetzt ein zusätzliches Feld `id: long` mit der Bestellungs-ID

Ihr kennt euch nicht mit Spring Boot aus? Keine Panik, ich habe bewusst soweit wie möglich auf Spring spezifische Konstruktionen verzichtet.

### Aufgabe

Schaut euch die umgebaute Anwendung an und erstellt eine Liste mit den noch zu lösenden Problemen.

----

### Maven verwenden

#### Anwendung mit Maven kompilieren
* CMD (Windows): `mvnw clean compile`
* Shell (Linux/Git Bash/macOS): `./mvnw clean compile`

#### Ausführen aller Tests
* CMD (Windows): `mvnw clean test`
* Shell (Linux/Git Bash/macOS): `./mvnw clean test`

Wenn ihr Tests mit Maven ausführt, wird automatisch die Code Coverage mit ermittelt. Die Berichte findet ihr nach dem Build in `target/site/jacoco`. Öffnet `index.html` im Browser für eine Übersicht.

#### Bauen der Anwendung als ausführbares JAR
* CMD (Windows): `mvnw clean install`
* Shell (Linux/Git Bash/macOS): `./mvnw clean install`

Wenn die Anwendung als ausführbares JAR gebaut wurde, kann sie mit dem Launch-Script gestartet werden. Dazu gebt ihr im Basisverzeichnis des Projekts folgendes ein:
* CMD (Windows): `launch`
* Shell (Linux/Git Bash/macOS): `./launch`