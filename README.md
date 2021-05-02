# Hands-on Design Patterns
***The Pizza Connection***

## Kapitel 05 - Decorator
### Szenario
🥳Das Geschäft boomt... Aber unser Einkauf hat immer größere Schwierigkeiten rechtzeitig die benötigten Waren nachzukaufen. 

Deshalb wurde bereits vor einiger Zeit unser `PizzaService` erweitert. Nach dem Ausliefern jeder Bestellung, werden die verbrauchten Zutaten in einem `IngredientLogger` aufgezeichnet und beim Beenden der `PizzaStoreApp` einmal ausgegeben👏

Das kannst Du auch gerne einmal ausprobieren💻

Nun hat der Einkauf angemerkt, dass das zwar hilft, aber für eine schnelle Bearbeitung eine geordnetere List von Nöten wäre. Unglücklicherweise wurde der `IngredientLogger` schnell eingeführt, besitzt kein Interface und lässt sich damit schlecht austauschen🤔

Unser Chef-Entwickler hatte dazu eine Idee💡: "Lasst uns das Decorator-Pattern nutzten. Die Ausgabe erfolgt nur beim Beenden der App. Wir können das so mit wenig Aufwand lösen und den Einkauf glücklich machen..."

### Aufgabe
* Erstelle einen Decorator für `IngredientLogger`
* Verwende den neuen Decorator anstelle des `IngredientLogger` für die Ausgabe der verbrauchten Zutaten (`PizzaStoreApp`: Zeile 50)
* Passe die Ausgabe nach den Wünschen des Einkaufs an

**Anforderungen des Einkaufs:**

Die ausgegebene Einkaufsliste soll in 4 Teile unterteilt sein:
* Dough
* Sauce
* Cheese
* Toppings

Für jeden Teil soll eine verbrauchte Zutat nur einmal in der Liste erscheinen und mit der verbrauchten Menge ergänzt werden💪

_Beispiel:_
```
Dough:
    2x  thin crusty dough
    1x  hand tossed dough
Sauce:
    2x  plain tomato sauce
    1x  premium tomato sauce
Cheese:
    2x  mozzarella
    1x  Monterey Jack
Toppings:
    2x  artichoke hearts
    2x  black Kalamata olives
    2x  freshly sliced tomato
    1x  hot pepperoni
```

**Hinweis:** Es wurde kein weiterer Code vorbereitet😢

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