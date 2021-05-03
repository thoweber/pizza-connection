# Hands-on Design Patterns
***The Pizza Connection***

## Kapitel 07 - Die Zutaten-Schnittstelle
### Szenario
Der  Einkauf🛒 beginnt zu drängeln: "Leute, wir brauchen unbedingt die Zutaten-Schnittstelle! Wir kommen nicht mehr nach😖"

Wie können wir vorgehen🤔?

### Aufgabe

Wir schaffen zusammen die dringend benötigte Schnittstelle und lernen dabei ein neues Pattern👯‍♂️🥳

Bei dieser Gelegenheit können wir auch eine verloren gegangene Funtkionalität wiederherstellen, und beim Beenden des Bestellterminals ebenfalls wieder die verbrauchten Zutaten ausgeben. REST sei Dank🙏

Los geht's🏎

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