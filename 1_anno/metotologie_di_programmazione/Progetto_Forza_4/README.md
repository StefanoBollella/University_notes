# FORZA 4
Progetto individuale per l'esame di Metotologie di Prograzione anno accademico 2021/2022.
Simulazione del gioco Forza 4, pensato per l’esecuzione su console.

[![](https://github.com/StefanoBollella/Appunti_Universitari/blob/main/1_anno/metotologie_di_programmazione/Progetto_Forza_4/IMG_0879.PNG)]

# Come compilare ed eseguire il progetto su console

Dato l'elevato numero di file sorgente da compilare viene utilizzata l'opzione 
@filename sulla riga di comando. 
È stato creato un file apposito che elenca tutti i nomi dei file sorgente da compilare, chiamato argumentFiles.txt presente nella directory [Forza4](Forza4/) del progetto.
Questo file viene passato nell'opzione di input per javac.

1) Occorre posizionarsi nella directory del progetto Forza4 e eseguire il comando: 
  
    javac -d build @argumentFiles.txt

2) successivamente bisogna spostarsi nella directory [build](Forza4/build/) del progetto.

Dopo aver compilato tutti i file necessari occorre creare un file jar.
Al momento della sua creazione bisogna indicare qual è il punto di ingresso dell'applicazione. Per questo motivo è stato creato un file Manifest.txt
con all'interno l'intestazione Main-Class: seguita dal nome della classe.
Il file è stato salvato all'interno della cartella build.

3) È possibile creare il file jar eseguendo il comando : 
    
    jar cfm forza4.jar Manifest.txt  *

4) successivamente è possibile lanciare l'applicazione.
  Passando come argomento sulla linea di comando  il percorso 
  assoluto del file di testo saveGames.txt presente all'interno della     
  cartella build, ecco un esempio : 
 
    java -jar forza4.jar  /User/Desktop/Forza_4/build/saveGames.txt 
 
 


