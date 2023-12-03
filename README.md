# mada-hs23-huffman-tamira-daniel
Mathematik fur die Datenkommunikation | HS23 | Klasse 3Ib (3iCbb)

- Tamira Leber: [tamira.leber@students.fhnw.ch](mailto:tamira.leber@students.fhnw.ch)
- Daniel Barber: [daniel.barber@students.fhnw.ch](mailto:daniel.barber@students.fhnw.ch)

## Beschreibung
Dieses Projekt besteht aus zwei ausführbare Java Klassen und zwei Hilfsklassen für das File Handling und das Erstellen von Huffman Nodes. Die Ausführung der Java Klassen überschreibt allfällige Dateien im `/target` Ordner, wenn diese bereits bestehen.

## HuffmanEncoder

`HuffmanEncoder` nimmt das Textfile `/resources/input.txt` welches ASCII-Zeichen enthält  und generiert eine Frequency Table der ASCII-Zeichen. Daraus werden Huffman Nodes erstellt, die dann zu einem Baum zusammengefügt werden. Die gebauten Huffman Codes werden dann im File `dec_tab.txt` ausgegeben. Zuletzt wird der `input.txt` mit den Huffman Codes kodiert, in ein Byte-Array umgewandelt und in `output.dat` ausgegeben. Beides wird im Ordner [`/target`](target) gespeichert.


## HuffmanDecoder

`HuffmanDecoder` liest die Huffman Codes aus `dec_tab.txt` und das Byte-Array aus `output.dat` ein. Dann wird das Byte-Array wieder in ein BitString umgewandelt und dann mit den Huffman Codes dekodiert. Die daraus resultierenden ASCII-Zeichen werden dann im File `decompress.txt` im Ordner [`/target`](target) gespeichert.

## Lösung
Die gegebene Datei `output-mada.dat` dekodiert mit den Huffman Codes aus `dec_tab-mada.txt` ergibt den folgenden Text:

**Das Verfahren lohnt sich in der vorgestellten Form nur fuer Texte mit einer gewissen Laenge. Ansonsten ist der Overhead fuer die Erstellung der Tabelle zu gross.**

**Man koennte sich aber auch ueberlegen, die Tabelle geschickter abzuspeichern. Ansonsten: Gut gemacht!**