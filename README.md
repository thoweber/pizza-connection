# Hands-on Design Patterns
***The Pizza Connection***

## Kapitel 03 - Abstract Factory
### Szenario
Unser Pizza-Business läuft besser als gedacht🎉. Statt unserer normalen Pizza bieten wir jetzt eine Gourmet Pizza und eine Sicilian Pizza an, die es jeweils in den Sorten Cheese, Peperoni und Veggie gibt.

Sie unterscheiden sich im Teig und der verwendeten Tomatensoße.

* Sicilian
    * `ThinCrustyDough`
    * `PlainTomatoSauce`
* Gourmet
    * `HandTossedDough`
    * `PremiumTomatoSauce`

### Aufgabe
* Wende das Abstract-Factory-Pattern an, um unseren `PizzaService` in die Lage zu versetzen, das neue Angebot produzieren zu können.

* Ergänze alle noch fehlenden Testfälle.

**Wichtig:**
Einiges an Code habe ich bereits für dich angepasst und erstellt.
* neue Interfaces für `Ingredients`:
    * `Dough`, `Sauce`, `Cheese`, `Topping`
    * Die entsprechenden Klassen implementieren jetzt diese Interfaces
* die neuen Zutaten sind bereits vorhanden
* eine neue Enum `PizzaStyle`, die zwischen Sicilian und Gourmet unterscheidet
    * Im Bestellprozess kann der Kunde den Stil auswählen
* Es gibt bereits `SicilianPizzaFactory` und `GourmetPizzaFactory` als leere Rumpf-Implementierungen
* `PizzaService` enthält bereits die beiden neune Factories
* `PizzaService.order()` nimmt jetzt neben `MenuItem` auch `PizzaStyle` entgegen

***Hinweis:*** diese Aufgabe ist ein größerer Umbau, nehme dir Zeit.

### Das Pattern als UML
![UML](pattern-uml.png)

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