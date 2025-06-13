# Hands-on Design Patterns
***The Pizza Connection***

## Kapitel 02 - Factory Method
### Szenario
Das Geschäft lauft immer besser🤑 und wir müssen unseren Kunden mehr Abwechslung bieten. Wir wollen zukünftig drei Sorten Pizza anbieten: Pepperoni, Cheese und Veggie.

Unsere "Default Pizza" wird dabei zur Cheese Pizza.

### Aufgabe
Erstelle eine Pizza Factory🏭, die als Vorlage für unterschiedliche Pizzen in deinem Pizza Imbiss dient. Die Factory-Methode soll als **Interface** definiert werden, deren Methoden in den konkreten Implementierungen umgesetzt werden müssen. Da du mit deinem Pizza Geschäft noch ganz am Anfang stehst, fängst du mit drei unterschiedlichen Pizzen an, **PeperoniPizza**, **CheesePizza** und **VeggiePizza**.

Das Interface soll folgende Factory-Methode deklarieren:
```
Pizza createPizza(MenuItem selectedItem);
```

**Wichtig:**
Einiges an Code habe ich bereits für dich angepasst und erstellt.
* Die neuen Pizzen sind in `de.infoteam.course.dp.pizzastore.model.dishes`
* Alle neu benötigten Zutaten sind erstellt
* `PizzaService`, `PizzaFactory`, `ConcretePizzaFactory` wurden angepasst und/oder als Rumpf erzeugt
* Die Tests wurden erweitert, so dass die Test-Suite grün wird, wenn alles richtig implementiert wurde

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