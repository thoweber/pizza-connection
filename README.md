# Hands-on Design Patterns
***The Pizza Connection***

## Kapitel 09 - Proxy-Pattern

### Szenario
Der Code unseres Pizza-Store ist gewuchert☠ und muss dringend aufgeräumt werden.

Es steht also ein Refactoring an. Der REST-Code zum Zugriff auf den `PizzaController` soll aus der `PizzaStoreApp` entfernt werden. Dazu verwenden wir das Proxy-Pattern.

### Was ist neu?

* der ursprüngliche `PizzaController` wurde in `PizzaControllerImpl` umbenannt
* `PizzaController` ist jetzt ein Interface, welches die Funktion des alten `PizzaController`s beschreibt

### Aufgabe
* erstelle einen `PizzaServiceProxy`, der die Funktionen des `PizzaController`s zur Verfügung stellt. Der Proxy soll mit dem Controller über REST kommunizieren
* verwende den `PizzaServiceProxy` in der `PizzaStoreApp` und entferne sämtlichen REST-basierten Code
* da du mit dem Proxy auch die länge der Warteschlange abfragen kannst, wird nach jeder Bestellung ausgegeben, wie lange die Schlange gerade ist

# Wichtiger Tipp:
ℹ Der Umgang mit einer Liste an Objekten/einem JSON-Array im Spring `RestTemplate` ist nicht sofort selbsterklären. Deshalb habe ich euch noch einen hilfreichen Link bereitgestellt:

**Informationen zum [Arbeiten mit einer Liste von Objekten](https://www.baeldung.com/spring-rest-template-list) im Spring `RestTemplate`.**

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