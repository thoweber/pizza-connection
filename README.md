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
* erstelle einen `PizzaControllerProxy`, der die Funktionen des `PizzaController`s zur Verfügung stellt. Der Proxy soll mit dem Controller über REST kommunizieren.  
  **Wichtig:** die Methoden ````
* verwende den `PizzaControllerProxy` in der `PizzaStoreApp` und entferne sämtlichen REST-basierten Code in `PizzaStoreApp`
* da du mit dem Proxy auch die länge der Warteschlange abfragen kannst, wird nach jeder Bestellung ausgegeben, wie lange die Schlange gerade ist

# Wichtige Tipps:
* ℹ Der Umgang mit einer Liste an Objekten/einem JSON-Array im Spring `RestTemplate` ist nicht sofort selbsterklären. Deshalb habe ich euch noch einen hilfreichen Link bereitgestellt:  
  **Informationen zum [Arbeiten mit einer Liste von Objekten](https://www.baeldung.com/spring-rest-template-list) im Spring `RestTemplate`.**
* Der `PizzaControllerProxy` benötigt die `@Component`-Annotation, damit er für die Dependency Injection von Spring verfügbar ist.
* Den Aufruf des Endpunkts `/close-kitchen` kannst du wie folgt umsetzen:
  ```java
  restTemplate.postForLocation(uri, null);
  ```

### Das Pattern als UML
![UML](pattern-uml.png)

1. Der ServiceInterface beschreibt den Interface der Service-Klasse. Der Proxy muss den Interface implementieren, um sich selbst als Service-Objekt auszugeben.
2. Der Service ist eine Klasse, der einige Business-Logik bereitstellt.
3. Die Proxy Klasse hat ein Referenzfeld, das auf ein Serviceobjekt zeigt. Nachdem der Proxy seien Verarbeitung abgeschlossen hat (z.B. lazy initialization, logging, access control, caching etc.) übergibt er die Anfrage an das Service-Objekt. Normalerweise verwalten Proxys den gesamten Lebenszyklus ihrer Service-Objekte.
4. Der Client sollte sowohl mit Services als auch mit Proxys über dieselbe Schnittstelle arbeiten. Auf diese Weise können Sie einen Proxy in jeden Code übergeben, der ein Serviceobjekt erwartet.

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