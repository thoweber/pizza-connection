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