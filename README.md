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

### Das Pattern als UML
![UML](pattern-uml.png)

Diese Implementierung nutzt das Prinzip der Objektkomposition: Der Adapter implementiert die Schnittstelle des einen Objekts und wickelt das andere ein. Sie kann in allen gängigen Programmiersprachen implementiert werden.

1. Der Client ist eine Klasse, die die vorhandene Geschäftslogik des Programms enthält.
2. Die Client-Schnittstelle beschreibt ein Protokoll, das andere Klassen folgen müssen, um mit dem Client-Code zusammenarbeiten zu können.
3. Der Service ist eine nützliche Klasse (in der Regel eine Drittanbieter- oder Legacy-Klasse). Der Client kann diese Klasse nicht direkt verwenden, da sie eine inkompatible Schnittstelle hat.
4. Der Adapter ist eine Klasse, die in der Lage ist, sowohl mit dem Client und dem Dienst arbeiten kann: Sie implementiert die Client-Schnittstelle und umhüllt das Dienstobjekt. Der Adapter empfängt Aufrufe vom Client über die Adapterschnittstelle und übersetzt sie an das verpackte Dienstobjekt in einem Format, das er versteht.
5. Der Client-Code wird nicht an die konkrete Adapterklasse gekoppelt, solange er mit dem Adapter über die Client-Schnittstelle arbeitet. Dadurch können neue Typen von Adaptern in das Programm eingeführt werden, ohne den bestehenden Client-Code zu zerstören.  
Dies kann nützlich sein, wenn die Schnittstelle der Serviceklasse geändert oder ersetzt wird.
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