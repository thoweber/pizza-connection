# Hands-on Design Patterns
***The Pizza Connection***

## Kapitel 02 - Factory Method
### Szenario
Das Geschäft lauft immer besser🤑 und wir müssen unseren Kunden mehr Abwechslung bieten. Wir wollen zukünftig drei Sorten Pizza anbieten: Pepperoni, Cheese und Veggie. 

Unsere "Default Pizza" wird dabei zur Cheese Pizza.

### Aufgabe
Erstelle eine Pizza Factory🏭, die als Vorlage für unterschiedliche Pizzen in deinem Pizza Imbiss dient. Die Klasse soll als **Interface** realisiert werden, deren Methoden in den konkreten Klassen implementiert werden müssen. Da du mit deinem Pizza Geschäft noch ganz am Anfang stehst, fängst du mit drei unterschiedlichen Pizzen an, **PepperoniPizza**, **CheesePizza** und **VeggiePizza**.

Das Interface soll folgende Factory-Methode deklarieren:
```
Pizza createPizza(MenuItem selectedItem);
```

**Wichtig:**
Einiges an Code habe ich bereits für dich angepasst und erstellt.
* Die neuen Pizzen sind in `de.infoteam.course.dp.pizzastore.model.dishes`
* Alle neu benötigten Zutaten sind erstellt
* Die `PizzaService`, `PizzaFactory`, `ConcretePizzaFactory` wurden angepasst und/oder als Rumpf erzeugt
* Die Tests wurden erweitert, so dass die Test-Suite grün wird, wenn alles richtig implementiert wurde


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