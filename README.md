# Hands-on Design Patterns
***The Pizza Connection***

## Kapitel 04 - Builder
### Szenario
Wir haben im vorangegangenen Kapitel unseren `PizzaService` erweitert, so dass er Pizzen in den Stilen `SICILIAN` und `GOURMET` erzeugen kann. Dafür haben wir folgenden Konstruktor erstellt, der zwei Instanzen von `PizzaService` entgegennimmt:
```
public PizzaService(PizzaFactory sicilianPizzaFactory, PizzaFactory gourmetPizzaFactory)
```
Das ist für die Zukunft sehr flexibel, weil wir uns nicht auf konkrete `PizzaFactory`-Implementierungen festlegen, aber leider auch fehleranfällig.

Es kam wie es kommen musste: Unser Praktikant wollte eine kleine Verbesserung an der `PizzaServiceApp` vornehmen und hat die Factories falsch zugewiesen. Die Kunden waren nicht erfreut🤦‍♂️

Um diese Fehler in der Zukunft zu vermeiden, soll das Erzeugen des `PizzaService` auf das Builder-Pattern umgestellt werden.

### Aufgabe
* Erstelle einen Builder für den **`PizzaService`**
* Stelle sicher, dass zum Erzeugen des **`PizzaService`** der Builder verwendet werden muss
* Passe den Code und die Tests entsprechend an

**Hinweis:** Für diese Aufgabe habe ich _nichts_ vorbereitet😋

----

### Maven verwenden

Um die Anwendung mit Maven zu bauen, verwendet ihr:
```
mvn clean compile
```
Zum Ausführen aller Tests:
```
mvn clean test
```
Zum Bauen der Anwendung als ausführbares JAR:
```
mvn clean install
```

#### Code Coverage
Wenn ihr Tests mit Maven ausführt, wird automatisch die Code Coverage mit ermittelt. Die Berichte findet ihr nach dem Build in `target/site/jacoco`. Öffnet `index.html` im Browser für eine Übersicht.